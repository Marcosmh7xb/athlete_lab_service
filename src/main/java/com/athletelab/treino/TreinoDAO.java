package com.athletelab.treino;

import com.athletelab.configBD.ConnectionDataBase;
import com.athletelab.exercicio.ExercicioModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TreinoDAO {


    // CREATE
    public static int inserir(TreinoModel t) {

        String sql =
                "INSERT INTO treino " +
                        "(id_usuario, nome, categoria, status) " +
                        "VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionDataBase.getConnection();

             PreparedStatement stmt =
                     conn.prepareStatement(
                             sql,
                             Statement.RETURN_GENERATED_KEYS
                     )) {


            // TREINO COM OU SEM DONO

            if (t.getIdUsuario() > 0) {

                stmt.setInt(1, t.getIdUsuario());

            } else {

                stmt.setNull(1, java.sql.Types.INTEGER);
            }

            stmt.setString(2, t.getNome());
            stmt.setString(3, t.getCategoria());
            stmt.setString(4, t.getStatus());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {

                return rs.getInt(1);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Erro ao inserir treino: "
                            + e.getMessage()
            );
        }

        return 0;
    }

    // =========================
    // BUSCAR POR ID
    // =========================
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

                t.setExercicios(listarPorTreino(id));

                return t;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // =========================
    // ATLETA → TREINOS ATRIBUÍDOS
    // =========================
    public List<TreinoModel> listarPorAtleta(int idUsuario) {

        String sql =
                "SELECT t.* FROM treino t " +
                        "JOIN treino_atribuido ta ON ta.id_treino = t.id_treino " +
                        "WHERE ta.id_usuario = ?";

        return listarGenerico(sql, idUsuario);
    }

    // =========================
    // TREINADOR → ATIVOS + ATRIBUÍDOS POR ELE
    // =========================
    public List<TreinoModel> listarParaTreinador(int idUsuario) {

        String sql =
                "SELECT DISTINCT t.* FROM treino t " +
                        "LEFT JOIN treino_atribuido ta ON ta.id_treino = t.id_treino " +
                        "WHERE t.status = 'ATIVO' OR ta.id_treinador = ?";

        return listarGenerico(sql, idUsuario);
    }

    // =========================
    // ADMIN → TODOS
    // =========================
    public List<TreinoModel> listarTodos() {

        String sql = "SELECT * FROM treino";

        return listarGenerico(sql, null);
    }

    // =========================
    // LISTAGEM GENÉRICA
    // =========================
    private List<TreinoModel> listarGenerico(String sql, Integer idUsuario) {

        List<TreinoModel> lista = new ArrayList<>();

        try (Connection conn = ConnectionDataBase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (idUsuario != null) {
                stmt.setInt(1, idUsuario);
            }

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                TreinoModel t = new TreinoModel();

                t.setIdTreino(rs.getInt("id_treino"));
                t.setIdUsuario(rs.getInt("id_usuario"));
                t.setNome(rs.getString("nome"));
                t.setCategoria(rs.getString("categoria"));
                t.setStatus(rs.getString("status"));
                t.setDataCriacao(rs.getString("data_criacao"));

                lista.add(t);
            }

            rs.close();

            // carrega exercícios
            for (TreinoModel t : lista) {
                t.setExercicios(listarPorTreino(t.getIdTreino()));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    // =========================
    // EXERCÍCIOS DO TREINO
    // =========================
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

    // =========================
    // 🔥 ATRIBUIR TREINO (NOVA FUNÇÃO)
    // =========================
    public void atribuirTreino(int idTreino, int idUsuario, int idTreinador) {

        String sql =
                "INSERT INTO treino_atribuido (id_treino, id_usuario, id_treinador) " +
                        "VALUES (?, ?, ?)";

        try (Connection conn = ConnectionDataBase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idTreino);
            stmt.setInt(2, idUsuario);
            stmt.setInt(3, idTreinador);

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void atualizar(TreinoModel treino) {

        String sql =
                "UPDATE treino " +
                        "SET nome=?, categoria=?, status=? " +
                        "WHERE id_treino=?";

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


}