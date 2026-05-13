package com.athletelab.treino;

import com.athletelab.configBD.ConnectionDataBase;
import com.athletelab.exercicio.ExercicioDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class TreinoDAO {

    // CREATE
    public static void inserir(TreinoModel t) {

        String sql = "INSERT INTO treino (id_usuario, nome, categoria, status) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionDataBase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, t.getIdUsuario());
            stmt.setString(2, t.getNome());
            stmt.setString(3, t.getCategoria());
            stmt.setString(4, t.getStatus());

            stmt.execute();

            System.out.println("Treino criado com sucesso.");

        } catch (SQLException e) {
            System.out.println("Erro ao inserir treino: " + e.getMessage());
        }
    }

    public List<TreinoModel> listarPorUsuario(int idUsuario) {
        String sql = "SELECT * FROM treino WHERE id_usuario = ?";

        List<TreinoModel> lista = new ArrayList<>();

        try (Connection conn = ConnectionDataBase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                TreinoModel t = new TreinoModel();
                t.setIdTreino(rs.getInt("id_treino"));
                t.setNome(rs.getString("nome"));
                t.setCategoria(rs.getString("categoria"));
                t.setStatus(rs.getString("status"));
                t.setDataCriacao(rs.getString("data_criacao"));

                // 🔥 carregar exercícios
                ExercicioDAO exDao = new ExercicioDAO();
                t.setExercicios(exDao.listarPorTreino(t.getIdTreino()));

                lista.add(t);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}