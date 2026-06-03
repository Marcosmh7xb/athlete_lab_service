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
public class EditarExercicioServlet extends HttpServlet {

    private TreinoDAO treinoDAO = new TreinoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idStr = request.getParameter("idTreino");

        if (idStr == null || idStr.isEmpty()) {
            throw new RuntimeException("idTreino não informado");
        }

        int idTreino = Integer.parseInt(idStr);

        TreinoModel treino = treinoDAO.buscarPorId(idTreino);
        System.out.println("TREINO: " + treino);

        ExercicioDAO exercicioDAO = new ExercicioDAO();
        List<ExercicioModel> exercicios = exercicioDAO.listarPorTreino(idTreino);

        request.setAttribute("treino", treino);
        request.setAttribute("exercicios", exercicios);
        request.getRequestDispatcher("/WEB-INF/editar_treino.jsp").forward(request, response);
    }
}