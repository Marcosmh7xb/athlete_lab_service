package com.athletelab.atleta;


import com.athletelab.usuario.BaseDAO;

import java.sql.*;

public class PerfilAtletaDAO extends BaseDAO {

    public void salvarOuAtualizar(PerfilAtletaModel a) {
        String sql = "INSERT INTO perfil_atleta (id_usuario, modalidade, nivel_experiencia, objetivo, altura, peso, dias_semana, ambiente, sexo, restricao_fisica) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE modalidade=?, nivel_experiencia=?, objetivo=?, altura=?, peso=?, dias_semana=?, ambiente=?, sexo=?, restricao_fisica=?";

        try (Connection conn = obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, a.getIdUsuario());
            stmt.setString(2, a.getModalidade());
            stmt.setString(3, a.getNivelExperiencia());
            stmt.setString(4, a.getObjetivo());
            stmt.setFloat(5, a.getAltura());
            stmt.setFloat(6, a.getPeso());
            stmt.setString(7, a.getDiasSemana());
            stmt.setString(8, a.getAmbiente());
            stmt.setString(9, a.getSexo());
            stmt.setString(10, a.getRestricaoFisica());

            // Update part
            stmt.setString(11, a.getModalidade());
            stmt.setString(12, a.getNivelExperiencia());
            stmt.setString(13, a.getObjetivo());
            stmt.setFloat(14, a.getAltura());
            stmt.setFloat(15, a.getPeso());
            stmt.setString(16, a.getDiasSemana());
            stmt.setString(17, a.getAmbiente());
            stmt.setString(18, a.getSexo());
            stmt.setString(19, a.getRestricaoFisica());

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
