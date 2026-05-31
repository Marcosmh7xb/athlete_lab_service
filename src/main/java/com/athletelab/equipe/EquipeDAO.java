package com.athletelab.equipe;

import com.athletelab.configBD.ConnectionDataBase;
import com.athletelab.usuario.BaseDAO;
import com.athletelab.usuario.UsuarioModel;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EquipeDAO extends BaseDAO {

    // CRIAR EQUIPE
    public void criar(EquipeModel e) {
        String sql = "INSERT INTO equipe (nome, descricao, esporte, data_criacao, id_treinador) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, e.getNome());
            stmt.setString(2, e.getDescricao());
            stmt.setString(3, e.getEsporte());
            stmt.setDate(4, Date.valueOf(LocalDate.now()));
            stmt.setInt(5, e.getIdTreinador());
            stmt.executeUpdate();
        } catch (Exception ex) { ex.printStackTrace(); }
    }

    // ATUALIZAR EQUIPE
    public void atualizar(EquipeModel e) {
        String sql = "UPDATE equipe SET nome=?, descricao=?, esporte=? WHERE id_equipe=?";
        try (Connection conn = obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, e.getNome());
            stmt.setString(2, e.getDescricao());
            stmt.setString(3, e.getEsporte());
            stmt.setInt(4, e.getIdEquipe());
            stmt.executeUpdate();
        } catch (Exception ex) { ex.printStackTrace(); }
    }

    // BUSCAR EQUIPE POR ID (COM OS SEUS ATLETAS)
    public EquipeModel buscarPorId(int idEquipe) {
        EquipeModel eq = null;
        // JOIN para pegar os dados do treinador responsável
        String sql = "SELECT e.*, u.nome as nome_treinador, u.email as email_treinador, u.foto as foto_treinador " +
                "FROM equipe e " +
                "JOIN usuario u ON e.id_treinador = u.id_usuario " +
                "WHERE e.id_equipe = ?";

        try (Connection conn = obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idEquipe);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                eq = new EquipeModel();
                eq.setIdEquipe(rs.getInt("id_equipe"));
                eq.setNome(rs.getString("nome"));
                eq.setDescricao(rs.getString("descricao"));
                eq.setEsporte(rs.getString("esporte"));
                eq.setDataCriacao(rs.getString("data_criacao"));
                eq.setIdTreinador(rs.getInt("id_treinador"));


                UsuarioModel treinador = new UsuarioModel();
                treinador.setNome(rs.getString("nome_treinador"));
                treinador.setEmail(rs.getString("email_treinador"));
                treinador.setFoto(rs.getString("foto_treinador"));
                eq.setTreinador(treinador);


                eq.setAtletas(listarAtletasDaEquipe(idEquipe));

                // Busca os membros (Atletas)
                eq.setAtletas(listarAtletasDaEquipe(idEquipe));
            }
        } catch (Exception ex) { ex.printStackTrace(); }
        return eq;
    }

    // LISTAR EQUIPES DE UM TREINADOR
    public List<EquipeModel> buscarEquipesDoTreinador(int idTreinador) {
        List<EquipeModel> lista = new ArrayList<>();

        String sql = "SELECT id_equipe, nome, descricao, esporte, data_criacao, id_treinador FROM equipe WHERE id_treinador = ?";

        try (Connection conn = obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idTreinador);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                EquipeModel eq = new EquipeModel();
                eq.setIdEquipe(rs.getInt("id_equipe"));
                eq.setNome(rs.getString("nome"));
                eq.setDescricao(rs.getString("descricao"));
                eq.setEsporte(rs.getString("esporte"));
                eq.setDataCriacao(rs.getString("data_criacao"));
                eq.setIdTreinador(rs.getInt("id_treinador"));
                lista.add(eq);
            }
        } catch (Exception ex) {

            throw new RuntimeException("Erro ao buscar equipes do treinador no banco", ex);
        }
        return lista;
    }

    // LISTAR EQUIPES QUE UM ATLETA PARTICIPA
    public List<EquipeModel> buscarEquipesDoAtleta(int idAtleta) {
        List<EquipeModel> lista = new ArrayList<>();
        String sql = "SELECT e.* FROM equipe e JOIN equipe_atleta ea ON e.id_equipe = ea.id_equipe WHERE ea.id_atleta = ?";
        try (Connection conn = obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idAtleta);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                EquipeModel eq = new EquipeModel();
                eq.setIdEquipe(rs.getInt("id_equipe"));
                eq.setNome(rs.getString("nome"));
                eq.setEsporte(rs.getString("esporte"));
                lista.add(eq);
            }
        } catch (Exception ex) { ex.printStackTrace(); }
        return lista;
    }

    // ADICIONAR ATLETAS POR E-MAIL
    public boolean convidarAtleta(int idEquipe, String email) {
        String sqlBusca = "SELECT id_usuario FROM usuario WHERE email = ? AND tipo_usuario = 'ATLETA'";
        String sqlInsere = "INSERT INTO equipe_atleta (id_equipe, id_atleta) VALUES (?, ?)";

        try (Connection conn = obterConexao()) {
            PreparedStatement stmtBusca = conn.prepareStatement(sqlBusca);
            stmtBusca.setString(1, email);
            ResultSet rs = stmtBusca.executeQuery();

            if (rs.next()) {
                int idAtleta = rs.getInt("id_usuario");
                try {
                    PreparedStatement stmtIns = conn.prepareStatement(sqlInsere);
                    stmtIns.setInt(1, idEquipe);
                    stmtIns.setInt(2, idAtleta);
                    stmtIns.executeUpdate();
                    return true;
                } catch (SQLIntegrityConstraintViolationException e) {
                    return false; // Já está na equipe
                }
            }
        } catch (Exception ex) { ex.printStackTrace(); }
        return false; // E-mail não encontrado ou não é atleta
    }

    // REMOVER ATLETA
    public void removerAtleta(int idEquipe, int idAtleta) {
        String sql = "DELETE FROM equipe_atleta WHERE id_equipe = ? AND id_atleta = ?";
        try (Connection conn = obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idEquipe);
            stmt.setInt(2, idAtleta);
            stmt.executeUpdate();
        } catch (Exception ex) { ex.printStackTrace(); }
    }

    // AUXILIAR: LISTAR ATLETAS DE UMA EQUIPE
    private List<UsuarioModel> listarAtletasDaEquipe(int idEquipe) {
        List<UsuarioModel> atletas = new ArrayList<>();
        String sql = "SELECT u.id_usuario, u.nome, u.email, u.foto FROM usuario u JOIN equipe_atleta ea ON u.id_usuario = ea.id_atleta WHERE ea.id_equipe = ?";
        try (Connection conn = obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idEquipe);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                UsuarioModel u = new UsuarioModel();
                u.setIdUsuario(rs.getInt("id_usuario"));
                u.setNome(rs.getString("nome"));
                u.setEmail(rs.getString("email"));
                u.setFoto(rs.getString("foto"));
                atletas.add(u);
            }
        } catch (Exception ex) { ex.printStackTrace(); }
        return atletas;
    }
}