package com.athletelab.treino;

import com.athletelab.configBD.ConnectionDataBase;
import com.athletelab.exercicio.ExercicioDAO;
import com.athletelab.exercicio.ExercicioModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class TreinoDAO {

    // CREATE
    public static int inserir(TreinoModel t) {

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
        return 0;
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

    public TreinoModel buscarPorId(int id) {

        String sql = "SELECT * FROM treino WHERE id_treino = ?";

        try (Connection conn = ConnectionDataBase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                TreinoModel t = new TreinoModel();

                t.setIdTreino(rs.getInt("id_treino"));
                t.setIdUsuario(rs.getInt("id_usuario"));
                t.setNome(rs.getString("nome"));
                t.setCategoria(rs.getString("categoria"));
                t.setStatus(rs.getString("status"));
                t.setDataCriacao(rs.getString("data_criacao"));

                ExercicioDAO exDao = new ExercicioDAO();
                t.setExercicios(exDao.listarPorTreino(id));

                return t;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<ExercicioModel> listarPorTreino(int idTreino) {
        List<ExercicioModel> lista = new ArrayList<>();

        String sql = "SELECT * FROM exercicio WHERE id_treino = ?";

        try (Connection conn = ConnectionDataBase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idTreino);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ExercicioModel ex = new ExercicioModel();

                ex.setIdExercicio(rs.getInt("id_exercicio"));
                ex.setNome(rs.getString("nome"));
                ex.setSeries(rs.getInt("series"));
                ex.setRepeticoes(rs.getInt("repeticoes"));
                ex.setTempoMin(rs.getInt("tempo_min"));
                ex.setObservacao(rs.getString("observacao"));

                lista.add(ex);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}