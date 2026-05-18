package com.athletelab.exercicio;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/exercicio")
public class ExercicioServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");

        // =========================
        // SALVAR EXERCÍCIO
        // =========================
        if ("salvar".equals(acao)) {

            String idTreinoStr = request.getParameter("idTreino");

            if (idTreinoStr == null || idTreinoStr.isEmpty()) {
                throw new RuntimeException("idTreino não veio do formulário");
            }

            int idTreino = Integer.parseInt(idTreinoStr);

            ExercicioModel e = new ExercicioModel();

            e.setIdTreino(idTreino);
            e.setNome(request.getParameter("nome"));
            e.setSeries(Integer.parseInt(request.getParameter("series")));
            e.setRepeticoes(Integer.parseInt(request.getParameter("repeticoes")));
            e.setTempoMin(Integer.parseInt(request.getParameter("tempoMin")));
            e.setObservacao(request.getParameter("observacao"));

            ExercicioDAO.inserir(e);

            // volta para a página do treino
            response.sendRedirect(
                    request.getContextPath() + "/treino/editar?idTreino=" + idTreino
            );
        }

        // =========================
        // DELETAR EXERCÍCIO
        // =========================
        if ("deletar".equals(acao)) {

            String idExStr = request.getParameter("idExercicio");
            String idTreinoStr = request.getParameter("idTreino");

            if (idExStr != null && !idExStr.isEmpty()) {
                int idEx = Integer.parseInt(idExStr);
                ExercicioDAO.deletar(idEx);
            }

            // volta para o treino
            response.sendRedirect(
                    request.getContextPath() + "/treino/editar?idTreino=" + idTreinoStr
            );
        }
    }
}