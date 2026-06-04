package com.athletelab.treinador;

import com.athletelab.usuario.BaseDAO;

import java.sql.*;

public class PerfilTreinadorDao extends BaseDAO {
    public void salvar(PerfilTreinadorModel perfil, int idTreinador) {
        String sql = "INSERT INTO perfil_treinador (modalidade, nivel_experiencia, objetivo, ambiente, sexo, restricao_fisica, id_treinador) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, perfil.getModalidade());
            stmt.setString(2, perfil.getNivelExperiencia());
            stmt.setString(3, perfil.getObjetivo());
            stmt.setString(4, perfil.getAmbiente());
            stmt.setString(5, perfil.getSexo());
            stmt.setString(6, perfil.getRestricaoFisica());
            stmt.setInt(7, idTreinador);

            stmt.executeUpdate();
        } catch (Exception erro) {
            erro.printStackTrace();
        }
    }


    public PerfilTreinadorModel buscarPorIdTreinador(int idTreinador) {//busca o treinador no banco para exibir suas informações ou altera-las
        String sql = "SELECT * FROM perfil_treinador WHERE id_treinador = ?";

        try (Connection conn = obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idTreinador);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                PerfilTreinadorModel p = new PerfilTreinadorModel();
                p.setModalidade(rs.getString("modalidade"));
                p.setNivelExperiencia(rs.getString("nivel_experiencia"));
                p.setObjetivo(rs.getString("objetivo"));
                p.setAmbiente(rs.getString("ambiente"));
                p.setSexo(rs.getString("sexo"));
                p.setRestricaoFisica(rs.getString("restricao_fisica"));
                return p;
            }
        } catch (Exception erro) {
            erro.printStackTrace();
        }
        return null;
    }

    public void atualizar(PerfilTreinadorModel perfil, int idTreinador) {
        String sql = "UPDATE perfil_treinador SET modalidade=?, nivel_experiencia=?, objetivo=?, ambiente=?, sexo=?, restricao_fisica=? WHERE id_treinador=?";

        try (Connection conn = obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, perfil.getModalidade());
            stmt.setString(2, perfil.getNivelExperiencia());
            stmt.setString(3, perfil.getObjetivo());
            stmt.setString(4, perfil.getAmbiente());
            stmt.setString(5, perfil.getSexo());
            stmt.setString(6, perfil.getRestricaoFisica());
            stmt.setInt(7, idTreinador);

            stmt.executeUpdate();
        } catch (Exception erro) {
            erro.printStackTrace();
        }
    }

}
