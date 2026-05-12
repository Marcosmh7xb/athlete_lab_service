package com.athletelab.treino;

import com.athletelab.configBD.ConnectionDataBase;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TreinoDAO {

    public static void inserirTreino( TreinoModel treino){
        String slq = "INSERT INTO treino(nome, status) VELUES (?, ?)";

        try(Connection connection = ConnectionDataBase.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)){

            stmt.setString(1, treino.getNome());
            stmt.setBoolean(2, treino.getStatus());


            System.out.println("Usuário salvo no banco.");

        }catch (SQLException erro){
            System.out.println("Erro ao inserir: " + erro.getMessage());
        }

    }

}
