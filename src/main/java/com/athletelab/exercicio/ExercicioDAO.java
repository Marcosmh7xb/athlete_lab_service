package com.athletelab.exercicio;

import com.athletelab.configBD.ConnectionDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExercicioDAO {

    // =========================
    // INSERT
    // =========================
    public static void inserir(ExercicioModel e) {

        String sql = "INSERT INTO exercicio (id_treino, nome, series, repeticoes, tempo_min, observacao) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionDataBase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (e.getIdTreino() == 0) {
                throw new IllegalArgumentException("idTreino não pode ser 0 ou vazio");
            }

            stmt.setInt(1, e.getIdTreino());
            stmt.setString(2, e.getNome());
            stmt.setInt(3, e.getSeries());
            stmt.setInt(4, e.getRepeticoes());
            stmt.setInt(5, e.getTempoMin());
            stmt.setString(6, e.getObservacao());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Erro ao inserir exercício: " + ex.getMessage());
        }
    }

    // =========================
    // LISTAR POR TREINO
    // =========================
    public static List<ExercicioModel> listarPorTreino(int idTreino) {

        List<ExercicioModel> lista = new ArrayList<>();

        String sql = "SELECT * FROM exercicio WHERE id_treino = ?";

        try (Connection conn = ConnectionDataBase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idTreino);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                ExercicioModel e = new ExercicioModel();

                e.setIdExercicio(rs.getInt("id_exercicio"));
                e.setIdTreino(rs.getInt("id_treino"));
                e.setNome(rs.getString("nome"));
                e.setSeries(rs.getInt("series"));
                e.setRepeticoes(rs.getInt("repeticoes"));
                e.setTempoMin(rs.getInt("tempo_min"));
                e.setObservacao(rs.getString("observacao"));

                lista.add(e);
            }


        } catch (SQLException ex) {
            System.out.println("Erro ao listar exercícios: " + ex.getMessage());
        }

        return lista;
    }

    // =========================
    // DELETE
    // =========================
    public static void deletar(int idExercicio) {

        String sql = "DELETE FROM exercicio WHERE id_exercicio = ?";

        try (Connection conn = ConnectionDataBase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idExercicio);
            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Erro ao deletar exercício: " + ex.getMessage());
        }
    }

    public static void atualizar(ExercicioModel e) {

        String sql = """
        UPDATE exercicio
        SET nome = ?,
            series = ?,
            repeticoes = ?,
            tempo_min = ?,
            observacao = ?
        WHERE id_exercicio = ?
    """;

        try (Connection conn = ConnectionDataBase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, e.getNome());
            stmt.setInt(2, e.getSeries());
            stmt.setInt(3, e.getRepeticoes());
            stmt.setInt(4, e.getTempoMin());
            stmt.setString(5, e.getObservacao());
            stmt.setInt(6, e.getIdExercicio());

            stmt.executeUpdate();

        } catch (SQLException ex) {

            System.out.println(
                    "Erro ao atualizar exercício: "
                            + ex.getMessage()
            );
        }
    }
}