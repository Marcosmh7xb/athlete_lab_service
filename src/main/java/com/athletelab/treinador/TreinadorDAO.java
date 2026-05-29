package com.athletelab.Treinador;

import com.athletelab.configBD.ConnectionDataBase;

import java.sql.*;

public class TreinadorDAO {
    public void salvarOuAtualizar(PerfilTreinadorModel p) {
        // ON DUPLICATE KEY UPDATE: Se não existir o id_usuario lá, ele cria. Se existir, ele atualiza.
        String sql = "INSERT INTO perfil_treinador (id_usuario, modalidade, nivel_experiencia, objetivo, ambiente, sexo, restricao_fisica) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE modalidade=?, nivel_experiencia=?, objetivo=?, ambiente=?, sexo=?, restricao_fisica=?";

        try (Connection conn = ConnectionDataBase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Dados para o INSERT
            stmt.setInt(1, p.getIdUsuario());
            stmt.setString(2, p.getModalidade());
            stmt.setString(3, p.getNivelExperiencia());
            stmt.setString(4, p.getObjetivo());
            stmt.setString(5, p.getAmbiente());
            stmt.setString(6, p.getSexo());
            stmt.setString(7, p.getRestricaoFisica());

            // Repete os mesmos dados para o UPDATE (caso a chave id_usuario já exista)
            stmt.setString(8, p.getModalidade());
            stmt.setString(9, p.getNivelExperiencia());
            stmt.setString(10, p.getObjetivo());
            stmt.setString(11, p.getAmbiente());
            stmt.setString(12, p.getSexo());
            stmt.setString(13, p.getRestricaoFisica());

            stmt.executeUpdate();
            System.out.println("DEBUG: Perfil técnico do treinador processado com sucesso.");

        } catch (SQLException e) {
            System.out.println("Erro no TreinadorDAO: " + e.getMessage());
            e.printStackTrace();
        }
    }

}


