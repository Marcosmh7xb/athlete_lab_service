package com.athletelab.usuario;

import com.athletelab.Treinador.PerfilTreinadorModel;
import com.athletelab.atleta.PerfilAtletaModel;
import com.athletelab.configBD.ConnectionDataBase;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

public class UsuarioDAO {

    // CREATE
    public static void inserir(UsuarioModel u) { /// Pasasando o argumento pelo construto de Usuario e recebendo o objeto.

        String sql = "INSERT INTO usuario(nome, email, telefone, cidade_uf, senha, data_nascimento, data_criacao, tipo_usuario, ativo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ";

        try (Connection connection = ConnectionDataBase.getConnection(); /// Abre a conexão com o Banco de Dados.
             PreparedStatement stmt = connection.prepareStatement(sql)) { /// Criar um objeto que executa os comandos SQL

            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getEmail());
            stmt.setString(3, u.getTelefone());
            stmt.setString(4, u.getCidadeUF());
            stmt.setString(5, u.getSenha());
            stmt.setDate(6, java.sql.Date.valueOf(u.getDataNascimento()));
            stmt.setDate(7, java.sql.Date.valueOf(u.getDataCriacao()));
            stmt.setString(8, u.getTipoUsuario());
            stmt.setBoolean(9, u.isAtivo());
            stmt.execute(); /// Executa o INSERT dentro do banco de dados; genérico (funciona para todos) INSERT, UPDATE, DELETE, CREATE TABLE, ALTER TABLE; Podendo usa o executeUpdate() usado para INSERT, UPDATE, DELETE.


            System.out.println("Usuário salvo no banco.");

        } catch (SQLException erro) {
            System.out.println("Erro ao inserir: " + erro.getMessage());
        }
    }

    // READ
    public List<UsuarioModel> listar() {

        List<UsuarioModel> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuario";

        try (Connection connection = ConnectionDataBase.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) { /// Execuatar o comando sql e quarda em rs; E o stmt.executeQuery(sql) é usado somente com SELECT; E depois fecha tudo automaticamente (por causa do try-with-resources) não precisando do connection.close().

            while (rs.next()) { /// Emqunto existir proxima linha ele continuar execuntando;

                UsuarioModel u = new UsuarioModel(); // Criação da lista do tipo Usuario;

                u.setIdUsuario(rs.getInt("id_usuario"));
                u.setNome(rs.getString("nome"));
                u.setEmail(rs.getString("email"));
                u.setTelefone(rs.getString("telefone"));
                u.setCidadeUF(rs.getString("cidade_uf"));
                u.setSenha(rs.getString("senha"));
                u.setAtivo(rs.getBoolean("ativo"));

                lista.add(u); /// Pegar o objeto que foi criado e ardicionar na lista criada assima;
            }

        } catch (SQLException erro) {
            System.out.println("Erro ao listar: " + erro.getMessage());
        }

        return lista; /// Devolver a lista de Usuarios para o metodo que chamou.
    }

    // UPDATE
    public void atualizar(UsuarioModel u) {

        String sql = "UPDATE usuario SET nome=?, email=?, telefone=?, cidade_uf=?, senha=? WHERE id_usuario=?";

        try (Connection conn = ConnectionDataBase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) { /// Prepara o comando sql para execução, mas não executa.

            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getEmail());
            stmt.setString(3, u.getTelefone());
            stmt.setString(4, u.getCidadeUF());
            stmt.setString(5, u.getSenha());
            stmt.setInt(6, u.getIdUsuario());

            stmt.executeUpdate();

            System.out.println("Usuário atualizado no banco.");

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar: " + e.getMessage());
        }
    }

    // DELETE
    public void deletar(int idUsuario) {

        String sql = "DELETE FROM usuario WHERE id_usuario=?";

        try (Connection conn = ConnectionDataBase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario); /// Pega o id do usuario pra usar de referencia pra deleta o usuario.
            stmt.executeUpdate();

            System.out.println("Usuário removido do banco.");

        } catch (SQLException e) {
            System.out.println("Erro ao deletar: " + e.getMessage());
        }
    }

    public static UsuarioModel login(String email, String senha, String tipo_usuario) {

        UsuarioModel usuario = null;

        try (Connection conn = ConnectionDataBase.getConnection()) {

            String sql = "SELECT * FROM usuario WHERE email = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                String senhaHashBanco = rs.getString("senha");
                String tipoBanco = rs.getString("tipo_usuario");

                if (BCrypt.checkpw(senha, senhaHashBanco)) {

                    // ADMIN entra sempre
                    if ("ADMIN".equals(tipoBanco)) {

                        usuario = new UsuarioModel();

                        usuario.setIdUsuario(rs.getInt("id_usuario"));
                        usuario.setNome(rs.getString("nome"));
                        usuario.setTipoUsuario(tipoBanco);

                        return usuario;
                    }

                    // atleta/treinador precisam coincidir
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

    public UsuarioModel buscarPorId(int id) {

        UsuarioModel u = null;

        try (Connection conn = ConnectionDataBase.getConnection()) {

            // ================= USUARIO =================

            String sqlUsuario =
                    "SELECT * FROM usuario WHERE id_usuario = ?";

            try (PreparedStatement stmtUsuario =
                         conn.prepareStatement(sqlUsuario)) {

                stmtUsuario.setInt(1, id);

                try (ResultSet rsUsuario =
                             stmtUsuario.executeQuery()) {

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

                        // ================= TREINADOR =================

                        if ("TREINADOR".equalsIgnoreCase(tipoUsuario)) {

                            String sqlTreinador =
                                    "SELECT * FROM perfil_treinador WHERE id_usuario = ?";

                            try (PreparedStatement stmtTreinador =
                                         conn.prepareStatement(sqlTreinador)) {

                                stmtTreinador.setInt(1, id);

                                try (ResultSet rsTreinador =
                                             stmtTreinador.executeQuery()) {

                                    if (rsTreinador.next()) {

                                        PerfilTreinadorModel pt =
                                                new PerfilTreinadorModel();

                                        pt.setIdPerfilTreinador(
                                                rsTreinador.getInt("id_perfil_treinador")
                                        );

                                        pt.setIdUsuario(id);

                                        pt.setModalidade(
                                                rsTreinador.getString("modalidade")
                                        );

                                        pt.setNivelExperiencia(
                                                rsTreinador.getString("nivel_experiencia")
                                        );

                                        pt.setObjetivo(
                                                rsTreinador.getString("objetivo")
                                        );

                                        pt.setAmbiente(
                                                rsTreinador.getString("ambiente")
                                        );

                                        pt.setSexo(
                                                rsTreinador.getString("sexo")
                                        );

                                        pt.setRestricaoFisica(
                                                rsTreinador.getString("restricao_fisica")
                                        );

                                        u.setPerfilTreinador(pt);
                                    }
                                }
                            }
                        }

                        // ================= ATLETA =================

                        else if ("ATLETA".equalsIgnoreCase(tipoUsuario)) {

                            String sqlAtleta =
                                    "SELECT * FROM perfil_atleta WHERE id_usuario = ?";

                            try (PreparedStatement stmtAtleta =
                                         conn.prepareStatement(sqlAtleta)) {

                                stmtAtleta.setInt(1, id);

                                try (ResultSet rsAtleta =
                                             stmtAtleta.executeQuery()) {

                                    if (rsAtleta.next()) {

                                        PerfilAtletaModel pa =
                                                new PerfilAtletaModel();

                                        pa.setIdPerfilAtleta(
                                                rsAtleta.getInt("id_perfil_atleta")
                                        );

                                        pa.setIdUsuario(id);

                                        pa.setModalidade(
                                                rsAtleta.getString("modalidade")
                                        );

                                        pa.setNivelExperiencia(
                                                rsAtleta.getString("nivel_experiencia")
                                        );

                                        pa.setObjetivo(
                                                rsAtleta.getString("objetivo")
                                        );

                                        pa.setAltura(
                                                rsAtleta.getFloat("altura")
                                        );

                                        pa.setPeso(
                                                rsAtleta.getFloat("peso")
                                        );

                                        pa.setDiasSemana(
                                                rsAtleta.getString("dias_semana")
                                        );

                                        pa.setAmbiente(
                                                rsAtleta.getString("ambiente")
                                        );

                                        pa.setSexo(
                                                rsAtleta.getString("sexo")
                                        );

                                        pa.setRestricaoFisica(
                                                rsAtleta.getString("restricao_fisica")
                                        );

                                        u.setPerfilAtleta(pa);
                                    }
                                }
                            }
                        }
                    }
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "Erro ao buscar usuário: " + e.getMessage()
            );

            e.printStackTrace();
        }

        return u;
    }

    //ATUALIZAR o PERFIL
    public void atualizarPerfil(UsuarioModel u) {

        String sql = "UPDATE usuario SET nome=?, email=?, telefone=?, cidade_uf=?, data_nascimento=?, foto=? WHERE id_usuario=?";

        try (Connection conn = ConnectionDataBase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getEmail());
            stmt.setString(3, u.getTelefone());
            stmt.setString(4, u.getCidadeUF());
            if (u.getDataNascimento() != null && !u.getDataNascimento().trim().isEmpty()) {
                stmt.setDate(5, java.sql.Date.valueOf(u.getDataNascimento()));
            } else {
                stmt.setNull(5, java.sql.Types.DATE); // Se estiver vazio, salva como NULL no banco
            }
            stmt.setString(6, u.getFoto()); // <--- Agora o campo 6 é a foto
            stmt.setInt(7, u.getIdUsuario()); // <--- E o campo 7 é o ID

            stmt.executeUpdate();
            System.out.println("DEBUG DAO: Perfil e foto atualizados com sucesso.");

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar perfil: " + e.getMessage());
        }
    }
}