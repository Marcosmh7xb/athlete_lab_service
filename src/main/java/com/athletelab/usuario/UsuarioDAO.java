package com.athletelab.usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;
import com.athletelab.treinador.PerfilTreinadorModel;
import com.athletelab.atleta.PerfilAtletaModel;


public class UsuarioDAO extends BaseDAO implements IUsuarioDAO{

    @Override
    public  void inserir(UsuarioModel u) {

        String sql = "INSERT INTO usuario(nome, email, telefone, cidade_uf, senha, data_nascimento, data_criacao, tipo_usuario, ativo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ";

        try (Connection connection = obterConexao(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getEmail());
            stmt.setString(3, u.getTelefone());
            stmt.setString(4, u.getCidadeUF());
            stmt.setString(5, u.getSenha());

            if (u.getDataNascimento() != null && !u.getDataNascimento().trim().isEmpty()) {
                stmt.setDate(6, java.sql.Date.valueOf(u.getDataNascimento()));
            } else {
                stmt.setNull(6, java.sql.Types.DATE);
            }

            stmt.setDate(7, java.sql.Date.valueOf(u.getDataCriacao()));
            stmt.setString(8, u.getTipoUsuario());
            stmt.setBoolean(9, u.isAtivo());

            stmt.execute();

            System.out.println("Usuário salvo no banco.");

        } catch (Exception erro) {
            System.out.println("Erro ao inserir: " + erro.getMessage());
            erro.printStackTrace();
        }
    }

    @Override
    public List<UsuarioModel> listar() {

        List<UsuarioModel> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuario";

        try (Connection connection = obterConexao(); Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                UsuarioModel u = new UsuarioModel();

                u.setIdUsuario(rs.getInt("id_usuario"));
                u.setNome(rs.getString("nome"));
                u.setEmail(rs.getString("email"));
                u.setTelefone(rs.getString("telefone"));
                u.setCidadeUF(rs.getString("cidade_uf"));
                u.setSenha(rs.getString("senha"));
                u.setAtivo(rs.getBoolean("ativo"));


                u.setTipoUsuario(rs.getString("tipo_usuario"));
                u.setDataNascimento(rs.getString("data_nascimento"));

                lista.add(u);
            }

        } catch (Exception erro) {
            System.out.println("Erro ao listar: " + erro.getMessage());
        }

        return lista;
    }

    @Override
    public void atualizar(UsuarioModel u) {

        String sql = "UPDATE usuario SET nome=?, email=?, telefone=?, cidade_uf=?, senha=? WHERE id_usuario=?";

        try (Connection conn = obterConexao(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getEmail());
            stmt.setString(3, u.getTelefone());
            stmt.setString(4, u.getCidadeUF());
            stmt.setString(5, u.getSenha());
            stmt.setInt(6, u.getIdUsuario());

            stmt.executeUpdate();

            System.out.println("Usuário atualizado no banco.");

        } catch (Exception e) {
            System.out.println("Erro ao atualizar: " + e.getMessage());
        }
    }

    @Override
    public void deletar(int idUsuario) {

        String sql = "DELETE FROM usuario WHERE id_usuario=?";

        try (Connection conn = obterConexao(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);

            System.out.println("Usuário removido do banco.");

        } catch (Exception e) {
            System.out.println("Erro ao deletar: " + e.getMessage());
        }
    }

    @Override
    public  UsuarioModel login(String email, String senha, String tipo_usuario) {

        UsuarioModel usuario = null;

        try (Connection conn = obterConexao()) {
            String sql = "SELECT * FROM usuario WHERE email = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                String senhaBanco = rs.getString("senha");
                String tipoBanco = rs.getString("tipo_usuario");

                boolean senhaValida = false;

                if (senhaBanco != null) {

                    if (senhaBanco.startsWith("$2a$") || senhaBanco.startsWith("$2b$") || senhaBanco.startsWith("$2y$"))
                        {senhaValida = BCrypt.checkpw(senha, senhaBanco);
                    } else {
                        senhaValida = senha.equals(senhaBanco);
                    }
                }

                if (senhaValida) {

                    if ("ADMIN".equals(tipoBanco)) {

                        usuario = new UsuarioModel();
                        usuario.setIdUsuario(rs.getInt("id_usuario"));
                        usuario.setNome(rs.getString("nome"));
                        usuario.setTipoUsuario(tipoBanco);

                        return usuario;
                    }

                    if (tipoBanco.equals(tipo_usuario)) {

                        usuario = new UsuarioModel();

                        usuario.setIdUsuario(rs.getInt("id_usuario"));
                        usuario.setNome(rs.getString("nome"));
                        usuario.setTipoUsuario(tipoBanco);

                        return usuario;
                    }
                }
            }
        } catch (Exception erro) {
            erro.printStackTrace();
        }

        return null;
    }
    @Override
    public UsuarioModel buscarPorId(int id) {

        UsuarioModel u = null;

        try (Connection conn = obterConexao()) {

            String sqlUsuario =
                    "SELECT * FROM usuario WHERE id_usuario = ?";

            try (PreparedStatement stmtUsuario = conn.prepareStatement(sqlUsuario)) {

                stmtUsuario.setInt(1, id);

                try (ResultSet rsUsuario = stmtUsuario.executeQuery()) {
                    if (rsUsuario.next()) {

                        u = new UsuarioModel();

                        u.setIdUsuario(rsUsuario.getInt("id_usuario"));
                        u.setNome(rsUsuario.getString("nome"));
                        u.setEmail(rsUsuario.getString("email"));
                        u.setTelefone(rsUsuario.getString("telefone"));
                        u.setCidadeUF(rsUsuario.getString("cidade_uf"));
                        u.setDataNascimento(rsUsuario.getString("data_nascimento"));
                        u.setTipoUsuario(rsUsuario.getString("tipo_usuario"));
                        u.setAtivo(rsUsuario.getBoolean("ativo"));
                        u.setFoto(rsUsuario.getString("foto"));

                        String tipoUsuario =
                                rsUsuario.getString("tipo_usuario");


                        if ("TREINADOR".equalsIgnoreCase(tipoUsuario)) {

                            String sqlTreinador = "SELECT * FROM perfil_treinador WHERE id_usuario = ?";

                            try (PreparedStatement stmtTreinador = conn.prepareStatement(sqlTreinador)) {

                                stmtTreinador.setInt(1, id);

                                try (ResultSet rsTreinador = stmtTreinador.executeQuery()) {

                                    if (rsTreinador.next()) {

                                        PerfilTreinadorModel pt = new PerfilTreinadorModel();

                                        pt.setIdPerfilTreinador(rsTreinador.getInt("id_perfil_treinador"));
                                        pt.setIdUsuario(id);
                                        pt.setModalidade(rsTreinador.getString("modalidade"));
                                        pt.setNivelExperiencia(rsTreinador.getString("nivel_experiencia"));
                                        pt.setObjetivo(rsTreinador.getString("objetivo"));
                                        pt.setAmbiente(rsTreinador.getString("ambiente"));
                                        pt.setSexo(rsTreinador.getString("sexo"));
                                        pt.setRestricaoFisica(rsTreinador.getString("restricao_fisica"));
                                        u.setPerfilTreinador(pt);
                                    }
                                }
                            }
                        }

                        else if ("ATLETA".equalsIgnoreCase(tipoUsuario)) {

                            String sqlAtleta = "SELECT * FROM perfil_atleta WHERE id_usuario = ?";

                            try (PreparedStatement stmtAtleta = conn.prepareStatement(sqlAtleta)) {

                                stmtAtleta.setInt(1, id);

                                try (ResultSet rsAtleta = stmtAtleta.executeQuery()) {

                                    if (rsAtleta.next()) {

                                        PerfilAtletaModel pa = new PerfilAtletaModel();

                                        pa.setIdPerfilAtleta(rsAtleta.getInt("id_perfil_atleta"));
                                        pa.setIdUsuario(id);
                                        pa.setModalidade(rsAtleta.getString("modalidade"));
                                        pa.setNivelExperiencia(rsAtleta.getString("nivel_experiencia"));
                                        pa.setObjetivo(rsAtleta.getString("objetivo"));
                                        pa.setAltura(rsAtleta.getFloat("altura"));
                                        pa.setPeso(rsAtleta.getFloat("peso"));
                                        pa.setDiasSemana(rsAtleta.getString("dias_semana"));
                                        pa.setAmbiente(rsAtleta.getString("ambiente"));
                                        pa.setSexo(rsAtleta.getString("sexo"));
                                        pa.setRestricaoFisica(rsAtleta.getString("restricao_fisica"));
                                        u.setPerfilAtleta(pa);
                                    }
                                }
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {

            System.out.println("Erro ao buscar usuário: " + e.getMessage());
            e.printStackTrace();
        }
        return u;
    }

    //ATUALIZAR o PERFIL
    @Override
    public void atualizarPerfil(UsuarioModel u) {

        String sql = "UPDATE usuario SET nome=?, email=?, telefone=?, cidade_uf=?, data_nascimento=?, foto=? WHERE id_usuario=?";

        try (Connection conn = obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getEmail());
            stmt.setString(3, u.getTelefone());
            stmt.setString(4, u.getCidadeUF());

            if (u.getDataNascimento() != null && !u.getDataNascimento().trim().isEmpty()) {
                stmt.setDate(5, java.sql.Date.valueOf(u.getDataNascimento()));
            } else {
                stmt.setNull(5, java.sql.Types.DATE);
            }
            stmt.setString(6, u.getFoto());
            stmt.setInt(7, u.getIdUsuario());

            stmt.executeUpdate();
            System.out.println("DEBUG DAO: Perfil e foto atualizados com sucesso.");

        } catch (Exception e) {
            System.out.println("Erro ao atualizar perfil: " + e.getMessage());
        }
    }
    @Override
    public UsuarioModel buscarPorEmail(String email) {

        String sql = "SELECT * FROM usuario WHERE email = ?";

        try (Connection conn = obterConexao(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                UsuarioModel u = new UsuarioModel();

                u.setIdUsuario(rs.getInt("id_usuario"));
                u.setNome(rs.getString("nome"));
                u.setEmail(rs.getString("email"));
                u.setTipoUsuario(rs.getString("tipo_usuario"));

                return u;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public UsuarioModel buscarFichaCompletaAtleta(int idAtleta) {
        UsuarioModel usuario = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = obterConexao(); // Ajuste para a sua classe de conexão
            String sql = "SELECT u.nome, u.email, u.telefone, u.cidade_uf, u.foto, " +
                    "a.peso, a.altura, a.objetivo, a.modalidade, a.nivel_experiencia, a.dias_semana, a.restricao_fisica " +
                    "FROM usuario u " +
                    "INNER JOIN perfil_atleta a ON u.id_usuario = a.id_usuario " +
                    "WHERE u.id_usuario = ?";

            ps = conn.prepareStatement(sql);
            ps.setInt(1, idAtleta);
            rs = ps.executeQuery();

            if (rs.next()) {
                usuario = new UsuarioModel();
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setTelefone(rs.getString("telefone"));
                usuario.setCidadeUF(rs.getString("cidade_uf"));
                usuario.setFoto(rs.getString("foto"));

                // Instancia o objeto interno do perfil atleta
                PerfilAtletaModel atleta = new PerfilAtletaModel();
                atleta.setPeso(rs.getFloat("peso"));
                atleta.setAltura(rs.getFloat("altura"));
                atleta.setObjetivo(rs.getString("objetivo"));
                atleta.setModalidade(rs.getString("modalidade"));
                atleta.setNivelExperiencia(rs.getString("nivel_experiencia"));
                atleta.setDiasSemana(rs.getString("dias_semana"));
                atleta.setRestricaoFisica(rs.getString("restricao_fisica"));

                usuario.setPerfilAtleta(atleta);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Feche as conexões aqui (rs, ps, conn)
        }
        return usuario;
    }

    @Override
    public boolean atualizarSenha(int idUsuario, String novaSenha) {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "UPDATE usuario SET senha = ? WHERE id_usuario = ?";

        try {
            conn = obterConexao();
            ps = conn.prepareStatement(sql);
            ps.setString(1, novaSenha);
            ps.setInt(2, idUsuario);

            int linhasAfetadas = ps.executeUpdate();
            return linhasAfetadas > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {

            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
    }
}