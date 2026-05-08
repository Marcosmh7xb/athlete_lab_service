package com.athletelab.usuario;

import com.athletelab.Treinador.PerfilTreinadorModel;
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

    // DELETE
    public void deletar(int idUsuario) throws SQLException{

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
        // SQL robusto: ignora espaços extras e diferença entre maiúsculas/minúsculas no tipo
        String sql = "SELECT * FROM usuario WHERE email = ? AND senha = ? AND UPPER(TRIM(tipo_usuario)) = UPPER(TRIM(?))";

        try (Connection conn = ConnectionDataBase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, senha);
            stmt.setString(3, tipo_usuario);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuario = new UsuarioModel();
                    usuario.setIdUsuario(rs.getInt("id_usuario"));
                    usuario.setNome(rs.getString("nome"));
                    usuario.setTipoUsuario(rs.getString("tipo_usuario"));
                    usuario.setEmail(rs.getString("email"));
                    return usuario;
                }
            }
        } catch (Exception erro) {
            System.out.println("Erro no DAO Login: " + erro.getMessage());
            erro.printStackTrace();
        }
        return null;
    }
    // Método para validar o login
    public UsuarioModel validarLogin(String email, String senha) {
        // 1. Imprime o que veio do formulário (vê se não veio nulo ou vazio)
        System.out.println("DEBUG DAO -> Email digitado: [" + email + "]");
        System.out.println("DEBUG DAO -> Senha digitada: [" + senha + "]");

        String sql = "SELECT * FROM usuario WHERE email = ?"; // busca só pelo email primeiro

        try (Connection conn = ConnectionDataBase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String senhaBanco = rs.getString("senha");
                int ativoBanco = rs.getInt("ativo");

                System.out.println("DEBUG DAO -> Usuário achado! Senha no Banco: [" + senhaBanco + "]");
                System.out.println("DEBUG DAO -> Status Ativo no Banco: " + ativoBanco);

                // Comparação manual para ver onde falha
                if (senhaBanco.equals(senha) && ativoBanco == 1) {
                    UsuarioModel u = new UsuarioModel();
                    u.setIdUsuario(rs.getInt("id_usuario"));
                    u.setNome(rs.getString("nome"));
                    u.setTipoUsuario(rs.getString("tipo_usuario"));
                    return u;
                } else {
                    System.out.println("DEBUG DAO -> Senha ou Ativo não batem!");
                }
            } else {
                System.out.println("DEBUG DAO -> E-mail não encontrado no banco!");
            }
        } catch (SQLException e) {
            System.out.println("DEBUG DAO -> ERRO DE SQL: " + e.getMessage());
        }
        return null;
    }
    public UsuarioModel buscarPorId(int id) {
        // Usamos o LEFT JOIN para trazer dados da usuario E da perfil_treinador em uma única consulta
        String sql = "SELECT u.*, t.modalidade, t.nivel_experiencia, t.objetivo as t_obj, t.ambiente, t.sexo as t_sexo, t.restricao_fisica " +
                "FROM usuario u " +
                "LEFT JOIN perfil_treinador t ON u.id_usuario = t.id_usuario " +
                "WHERE u.id_usuario = ?";

        UsuarioModel u = null;

        try (Connection conn = ConnectionDataBase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                u = new UsuarioModel();
                u.setIdUsuario(rs.getInt("id_usuario"));
                u.setNome(rs.getString("nome"));
                u.setEmail(rs.getString("email"));
                u.setTelefone(rs.getString("telefone"));
                u.setCidadeUF(rs.getString("cidade_uf"));
                u.setDataNascimento(rs.getString("data_nascimento"));
                u.setTipoUsuario(rs.getString("tipo_usuario"));
                u.setAtivo(rs.getBoolean("ativo"));
                u.setFoto(rs.getString("foto"));

                // Se a modalidade não for nula, significa que este usuário é um treinador
                // que já preencheu seus dados técnicos.
                if (rs.getString("modalidade") != null) {
                    PerfilTreinadorModel pt = new PerfilTreinadorModel();
                    pt.setIdUsuario(u.getIdUsuario());
                    pt.setModalidade(rs.getString("modalidade"));
                    pt.setNivelExperiencia(rs.getString("nivel_experiencia"));
                    pt.setObjetivo(rs.getString("t_obj"));
                    pt.setAmbiente(rs.getString("ambiente"));
                    pt.setSexo(rs.getString("t_sexo"));
                    pt.setRestricaoFisica(rs.getString("restricao_fisica"));

                    u.setPerfilTreinador(pt); // Seta o perfil técnico dentro do usuário
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar perfil completo: " + e.getMessage());
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