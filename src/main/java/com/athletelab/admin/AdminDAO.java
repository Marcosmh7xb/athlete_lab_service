package com.athletelab.admin;

import com.athletelab.configBD.ConnectionDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO {

    // =========================
    // INSERIR
    // =========================
    public int inserir(AdminModel treino) {

        String sql =
                "INSERT INTO admin_treino " +
                        "(nome, categoria, status) " +
                        "VALUES (?, ?, ?)";

        try (Connection conn = ConnectionDataBase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     sql,
                     Statement.RETURN_GENERATED_KEYS
             )) {

            stmt.setString(1, treino.getNome());
            stmt.setString(2, treino.getCategoria());
            stmt.setString(3, treino.getStatus());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            System.out.println(
                    "Treino Criado"
            );
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    // =========================
    // BUSCAR POR ID
    // =========================
    public AdminModel buscarPorId(int id) {

        String sql =
                "SELECT * FROM admin_treino WHERE id_treino = ?";

        try (Connection conn = ConnectionDataBase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                AdminModel treino = new AdminModel();

                treino.setIdTreino(rs.getInt("id_treino"));
                treino.setNome(rs.getString("nome"));
                treino.setCategoria(rs.getString("categoria"));
                treino.setStatus(rs.getString("status"));
                treino.setDataCriacao(rs.getString("data_criacao"));

                return treino;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // =========================
    // LISTAR TODOS (ADMIN)
    // =========================
    public List<AdminModel> listarTodos() {

        String sql = "SELECT * FROM admin_treino ORDER BY id_treino DESC";

        return listarGenerico(sql);
    }

    // =========================
    // LISTAR PÚBLICOS
    // =========================
    public List<AdminModel> listarPublicos() {

        String sql =
                "SELECT * FROM admin_treino " +
                        "WHERE status = 'Ativo' " +
                        "ORDER BY id_treino DESC";

        return listarGenerico(sql);
    }

    // =========================
    // ATUALIZAR
    // =========================
    public void atualizar(AdminModel treino) {

        String sql =
                "UPDATE admin_treino " +
                        "SET nome = ?, categoria = ?, status = ? " +
                        "WHERE id_treino = ?";

        try (Connection conn = ConnectionDataBase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, treino.getNome());
            stmt.setString(2, treino.getCategoria());
            stmt.setString(3, treino.getStatus());
            stmt.setInt(4, treino.getIdTreino());

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // =========================
    // DELETAR
    // =========================
    public void deletar(int idTreino) {

        String sql =
                "DELETE FROM admin_treino WHERE id_treino = ?";

        try (Connection conn = ConnectionDataBase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idTreino);

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // =========================
    // LISTAGEM GENÉRICA
    // =========================
    private List<AdminModel> listarGenerico(String sql) {

        List<AdminModel> lista = new ArrayList<>();

        try (Connection conn = ConnectionDataBase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                AdminModel treino = new AdminModel();

                treino.setIdTreino(rs.getInt("id_treino"));
                treino.setNome(rs.getString("nome"));
                treino.setCategoria(rs.getString("categoria"));
                treino.setStatus(rs.getString("status"));
                treino.setDataCriacao(rs.getString("data_criacao"));

                lista.add(treino);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}