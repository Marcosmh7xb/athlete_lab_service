package com.athletelab.treinador;

import com.athletelab.usuario.BaseDAO;
import java.sql.*;

public class TreinadorDAO extends BaseDAO {
    public void salvarOuAtualizar(PerfilTreinadorModel p) {
        String sql = "INSERT INTO perfil_treinador (id_usuario, modalidade, nivel_experiencia, objetivo, ambiente, sexo, restricao_fisica) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE modalidade=?, nivel_experiencia=?, objetivo=?, ambiente=?, sexo=?, restricao_fisica=?";

        try (Connection conn = obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, p.getIdUsuario());
            stmt.setString(2, p.getModalidade());
            stmt.setString(3, p.getNivelExperiencia());
            stmt.setString(4, p.getObjetivo());
            stmt.setString(5, p.getAmbiente());
            stmt.setString(6, p.getSexo());
            stmt.setString(7, p.getRestricaoFisica());

            stmt.setString(8, p.getModalidade());
            stmt.setString(9, p.getNivelExperiencia());
            stmt.setString(10, p.getObjetivo());
            stmt.setString(11, p.getAmbiente());
            stmt.setString(12, p.getSexo());
            stmt.setString(13, p.getRestricaoFisica());

            stmt.executeUpdate();
            System.out.println("DEBUG: Perfil técnico do treinador processado com sucesso.");

        } catch (Exception erro) {
            System.out.println("Erro no TreinadorDAO: " + erro.getMessage());
            erro.printStackTrace();
        }
    }

}


