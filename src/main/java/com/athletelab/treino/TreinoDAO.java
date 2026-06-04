package com.athletelab.treino;

import com.athletelab.utilitarios.ConnectionDataBase;
import com.athletelab.exercicio.ExercicioModel;
import com.athletelab.usuario.BaseDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TreinoDAO extends BaseDAO {

    public int inserir(TreinoModel t) {

        String sql = "INSERT INTO treino " + "(id_usuario, nome, categoria, status) " + "VALUES (?, ?, ?, ?)";

        try (Connection conn = obterConexao();

             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

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

        } catch (Exception erro) {
            System.out.println("Erro ao inserir treino: " + erro.getMessage());
        }

        return 0;
    }


    public TreinoModel buscarPorId(int id) {

        String sql = "SELECT * FROM treino WHERE id_treino = ?";

        try (Connection conn = obterConexao();
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

        } catch (Exception erro) {
            erro.printStackTrace();
        }

        return null;
    }


    public List<TreinoModel> listarPorAtleta(int idUsuario) {

        String sql =
                "SELECT t.* FROM treino t " +
                        "JOIN treino_atribuido ta ON ta.id_treino = t.id_treino " +
                        "WHERE ta.id_usuario = ? " +
                        "AND t.status = 'ATIVO'";

        return listarGenerico(sql, idUsuario);
    }


    public List<TreinoModel> listarParaTreinador(int idUsuario) {

        String sql =
                "SELECT DISTINCT t.* FROM treino t " +
                        "LEFT JOIN treino_atribuido ta ON ta.id_treino = t.id_treino " +
                        "WHERE t.status = 'ATIVO' OR ta.id_treinador = ?";

        return listarGenerico(sql, idUsuario);
    }

    public List<TreinoModel> listarTodos() {

        String sql = "SELECT * FROM treino";

        return listarGenerico(sql, null);
    }


    private List<TreinoModel> listarGenerico(String sql, Integer idUsuario) {

        List<TreinoModel> lista = new ArrayList<>();

        try (Connection conn = obterConexao();
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

            for (TreinoModel t : lista) {
                t.setExercicios(listarPorTreino(t.getIdTreino()));
            }

        } catch (Exception erro) {
            erro.printStackTrace();
        }

        return lista;
    }

    public List<ExercicioModel> listarPorTreino(int idTreino) {

        List<ExercicioModel> lista = new ArrayList<>();

        String sql = "SELECT * FROM exercicio WHERE id_treino = ?";

        try (Connection conn = obterConexao();
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

        } catch (Exception erro) {
            erro.printStackTrace();
        }

        return lista;
    }

    public void atribuirTreino(int idTreino, int idUsuario, int idTreinador) {

        String sql =
                "INSERT INTO treino_atribuido (id_treino, id_usuario, id_treinador) " +
                        "VALUES (?, ?, ?)";

        try (Connection conn = obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idTreino);
            stmt.setInt(2, idUsuario);
            stmt.setInt(3, idTreinador);

            stmt.executeUpdate();

        } catch (Exception erro) {
            erro.printStackTrace();
        }
    }

    public void atualizar(TreinoModel treino) {

        String sql = "UPDATE treino " + "SET nome=?, categoria=?, status=? " + "WHERE id_treino=?";

        try (Connection conn = obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, treino.getNome());
            stmt.setString(2, treino.getCategoria());
            stmt.setString(3, treino.getStatus());
            stmt.setInt(4, treino.getIdTreino());

            System.out.println("Atualizando treino ID: " + treino.getIdTreino());
            System.out.println("Novo status: " + treino.getStatus());

            int linhas = stmt.executeUpdate();

            System.out.println("Linhas afetadas: " + linhas);

        } catch (Exception erro) {
            erro.printStackTrace();
        }
    }

    public void deletar(int idTreino) {

        String sql = "DELETE FROM treino WHERE id_treino = ?";

        try (Connection conn = ConnectionDataBase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idTreino);
            stmt.executeUpdate();

        } catch (Exception erro) {
            erro.printStackTrace();
        }
    }

    public boolean treinoJaAtribuido(int idTreino, int idUsuario) {

        String sql = "SELECT COUNT(*) FROM treino_atribuido WHERE id_treino = ? AND id_usuario = ?";

        try (Connection conn = obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idTreino);
            stmt.setInt(2, idUsuario);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (Exception erro) {
            erro.printStackTrace();
        }
        return false;
    }
}