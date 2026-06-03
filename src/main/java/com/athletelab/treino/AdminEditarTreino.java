package com.athletelab.treino;

import java.io.IOException;

import com.google.gson.Gson;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/api/treino")
public class AdminEditarTreino extends HttpServlet {

    private final TreinoDAO treinoDAO = new TreinoDAO();
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try {
            int idTreino = Integer.parseInt(req.getParameter("id"));

            TreinoModel treino = treinoDAO.buscarPorId(idTreino);

            if (treino == null) {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return;
            }

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");

            resp.getWriter().write(gson.toJson(treino));

        } catch (Exception erro) {

            erro.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try {

            String idStr = req.getParameter("idTreino");
            String nome = req.getParameter("nome");
            String categoria = req.getParameter("categoria");
            String status = req.getParameter("status");

            System.out.println("==============");
            System.out.println("ID RECEBIDO: " + idStr);
            System.out.println("NOME: " + nome);
            System.out.println("CAT: " + categoria);
            System.out.println("STATUS: " + status);
            System.out.println("==============");

            if (idStr == null || idStr.isBlank()) {
                resp.sendError(400, "idTreino não enviado");
                return;
            }

            int idTreino = Integer.parseInt(idStr);

            TreinoModel treino = treinoDAO.buscarPorId(idTreino);

            if (treino == null) {
                resp.sendError(404, "Treino não encontrado");
                return;
            }

            treino.setNome(nome);
            treino.setCategoria(categoria);
            treino.setStatus(status);

            treinoDAO.atualizar(treino);

            resp.setContentType("application/json");
            resp.getWriter().write("{\"sucesso\":true}");

        } catch (Exception erro) {

            erro.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, erro.getMessage()
            );
        }
    }
}