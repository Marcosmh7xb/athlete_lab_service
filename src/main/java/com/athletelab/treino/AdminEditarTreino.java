package com.athletelab.treino;

import com.google.gson.Gson;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/api/treino")
public class AdminEditarTreino extends HttpServlet {

    private TreinoDAO treinoDAO =
            new TreinoDAO();

    private Gson gson =
            new Gson();

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws IOException {

        int idTreino =
                Integer.parseInt(
                        req.getParameter("id")
                );

        TreinoModel treino =
                treinoDAO.buscarPorId(idTreino);

        resp.setContentType("application/json");

        resp.getWriter().write(
                gson.toJson(treino)
        );
    }

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
            throws IOException {

        int idTreino =
                Integer.parseInt(
                        req.getParameter("idTreino")
                );

        TreinoModel treino =
                treinoDAO.buscarPorId(idTreino);

        treino.setNome(
                req.getParameter("nome")
        );

        treino.setCategoria(
                req.getParameter("categoria")
        );

        treino.setStatus(
                req.getParameter("status")
        );

        treinoDAO.atualizar(treino);
    }
}