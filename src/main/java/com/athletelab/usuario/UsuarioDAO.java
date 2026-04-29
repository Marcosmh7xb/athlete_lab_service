package com.athletelab.usuario;

import com.athletelab.configBD.ConnectionDataBase;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

        try (Connection conn = ConnectionDataBase.getConnection()){

            String sql = "SELECT * FROM usuario WHERE email = ? AND senha = ?  AND tipo_usuario = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, email);
            stmt.setString(2, senha);
            stmt.setString(3,tipo_usuario);


            ResultSet rs =  stmt.executeQuery();

            if (rs.next()) {
                usuario = new UsuarioModel();
                usuario.setIdUsuario( rs.getInt("id_usuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setTipoUsuario(rs.getString("tipo_usuario"));

                return usuario;
            }

            return null;

        } catch (Exception erro) {
            erro.printStackTrace();
            return null;
        }
    }
}