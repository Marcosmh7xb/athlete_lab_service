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
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");

        if ("salvar".equals(acao)) {

            String idTreinoStr = request.getParameter("idTreino");

            if (idTreinoStr == null || idTreinoStr.isEmpty()) {
                throw new RuntimeException("idTreino não veio do formulário");
            }

            int idTreino = Integer.parseInt(idTreinoStr);

            ExercicioModel exercicio = new ExercicioModel();

            exercicio.setIdTreino(idTreino);

            exercicio.setNome(request.getParameter("nome"));

            exercicio.setSeries(
                    Integer.parseInt(request.getParameter("series"))
            );

            exercicio.setRepeticoes(
                    Integer.parseInt(request.getParameter("repeticoes"))
            );

            exercicio.setTempoMin(
                    Integer.parseInt(request.getParameter("tempoMin"))
            );

            exercicio.setObservacao(
                    request.getParameter("observacao")
            );

            ExercicioDAO.inserir(exercicio);

            response.sendRedirect(
                    request.getContextPath()
                            + "/treino/editar?idTreino="
                            + idTreino
            );
        }
    }
}