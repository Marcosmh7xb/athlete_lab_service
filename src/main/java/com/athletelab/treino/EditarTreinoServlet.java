package com.athletelab.treino;

import com.athletelab.exercicio.ExercicioDAO;
import com.athletelab.exercicio.ExercicioModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/treino/editar")
public class EditarTreinoServlet extends HttpServlet {

    private TreinoDAO treinoDAO = new TreinoDAO();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        // 🔥 pega o idTreino da URL
        String idStr = request.getParameter("idTreino");

        // validação
        if (idStr == null || idStr.isEmpty()) {
            throw new RuntimeException("idTreino não informado");
        }

        int idTreino = Integer.parseInt(idStr);

        // 🔥 busca treino
        TreinoModel treino = treinoDAO.buscarPorId(idTreino);
        System.out.println("TREINO: " + treino);

        // 🔥 busca exercícios
        ExercicioDAO exercicioDAO = new ExercicioDAO();
        List<ExercicioModel> exercicios = exercicioDAO.listarPorTreino(idTreino);

        // envia para o JSP
        request.setAttribute("treino", treino);
        request.setAttribute("exercicios", exercicios);

        request.getRequestDispatcher("/WEB-INF/editar_treino.jsp")
                .forward(request, response);
    }
}