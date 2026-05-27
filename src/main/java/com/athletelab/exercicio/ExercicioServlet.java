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

        // =========================
        // SALVAR
        // =========================
        if ("salvar".equals(acao)) {

            int idTreino =
                    Integer.parseInt(request.getParameter("idTreino"));

            ExercicioModel exercicio = new ExercicioModel();

            exercicio.setIdTreino(idTreino);

            exercicio.setNome(
                    request.getParameter("nome")
            );

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

        // =========================
        // ATUALIZAR
        // =========================
        else if ("atualizar".equals(acao)) {

            int idExercicio =
                    Integer.parseInt(request.getParameter("idExercicio"));

            int idTreino =
                    Integer.parseInt(request.getParameter("idTreino"));

            ExercicioModel exercicio = new ExercicioModel();

            exercicio.setIdExercicio(idExercicio);

            exercicio.setIdTreino(idTreino);

            exercicio.setNome(
                    request.getParameter("nome")
            );

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

            ExercicioDAO.atualizar(exercicio);

            response.sendRedirect(
                    request.getContextPath()
                            + "/treino/editar?idTreino="
                            + idTreino
            );
        }

        // =========================
        // DELETAR
        // =========================
        else if ("deletar".equals(acao)) {

            int idExercicio =
                    Integer.parseInt(request.getParameter("idExercicio"));

            int idTreino =
                    Integer.parseInt(request.getParameter("idTreino"));

            ExercicioDAO.deletar(idExercicio);

            response.sendRedirect(
                    request.getContextPath()
                            + "/treino/editar?idTreino="
                            + idTreino
            );
        }
    }
}