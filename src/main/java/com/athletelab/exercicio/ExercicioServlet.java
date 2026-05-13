package com.athletelab.exercicio;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/exercicio")
public class ExercicioServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String acao = req.getParameter("acao");

        if ("salvar".equals(acao)) {

            ExercicioModel e = new ExercicioModel();

            e.setIdTreino(Integer.parseInt(req.getParameter("idTreino")));
            e.setNome(req.getParameter("nome"));
            e.setSeries(Integer.parseInt(req.getParameter("series")));
            e.setRepeticoes(Integer.parseInt(req.getParameter("repeticoes")));
            e.setTempoMin(Integer.parseInt(req.getParameter("tempoMin")));
            e.setObservacao(req.getParameter("observacao"));

            ExercicioDAO.inserir(e);
        }

        if ("deletar".equals(acao)) {

            int id = Integer.parseInt(req.getParameter("idExercicio"));
            ExercicioDAO.deletar(id);
        }

        resp.sendRedirect(req.getHeader("referer"));
    }
}