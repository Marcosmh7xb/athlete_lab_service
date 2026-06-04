package com.athletelab.utilitarios;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;

@WebServlet("/teste-banco")
public class TesteBancoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException {

        resp.setContentType("text/plain");
        try {
            Connection conn = ConnectionDataBase.getConnection();
            if (conn != null && !conn.isClosed()) {
                resp.getWriter().println("🔥 BANCO CONECTADO COM SUCESSO!");
            }
        } catch (Exception e) {
            resp.getWriter().println("❌ ERRO AO CONECTAR");
            e.printStackTrace();
        }
    }
}