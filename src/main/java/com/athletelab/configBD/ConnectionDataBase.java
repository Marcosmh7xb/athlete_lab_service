package com.athletelab.configBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDataBase {

    private static final String HOST =
            System.getenv("DB_HOST") != null
                    ? System.getenv("DB_HOST")
                    : "localhost";

    private static final String DB_NAME =
            System.getenv("DB_NAME") != null
                    ? System.getenv("DB_NAME")
                    : "athlete_lab";

    private static final String USER =
            System.getenv("DB_USER") != null
                    ? System.getenv("DB_USER")
                    : "root";

    private static final String PASSWORD =
            System.getenv("DB_PASSWORD") != null
                    ? System.getenv("DB_PASSWORD")
                    : "root";

    private static final String URL =
            "jdbc:mysql://" + HOST + ":3306/" + DB_NAME
                    + "?useSSL=false&allowPublicKeyRetrieval=true";

    private static Connection connection = null;

    public static Connection getConnection() throws SQLException {

        if (connection == null || connection.isClosed()) {

            try {

                Class.forName("com.mysql.cj.jdbc.Driver");

                connection =
                        DriverManager.getConnection(
                                URL,
                                USER,
                                PASSWORD
                        );

                System.out.println(
                        "🔥 BANCO CONECTADO COM SUCESSO!"
                );

            } catch (ClassNotFoundException e) {

                throw new SQLException(
                        "Driver MySQL não encontrado...",
                        e
                );

            }

        }

        return connection;

    }
}