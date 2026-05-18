package com.athletelab.treino;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/treino/editar")
public class EditarTreinoServlet extends HttpServlet {

    private TreinoDAO treinoDAO = new TreinoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("id");

        // se veio ID → edição
        if (idStr != null && !idStr.isEmpty()) {

            int id = Integer.parseInt(idStr);

            // 🔥 aqui você PRECISA de um método buscarPorId no DAO
            TreinoModel treino = treinoDAO.buscarPorId(id);

            request.setAttribute("treino", treino);
        }

        request.getRequestDispatcher("/WEB-INF/editar_treino.jsp")
                .forward(request, response);
    }
}