package com.athletelab.treino;

import com.athletelab.exercicio.ExercicioDAO;
import com.athletelab.usuario.UsuarioModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/treinos")
public class TreinoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession sessao = req.getSession();
        UsuarioModel usuario = (UsuarioModel) sessao.getAttribute("usuarioLogado");

        TreinoDAO treinoDAO = new TreinoDAO();
        ExercicioDAO exercicioDAO = new ExercicioDAO();

        List<TreinoModel> treinos = treinoDAO.listarPorUsuario(usuario.getIdUsuario());

        for (TreinoModel t : treinos) {
            t.setExercicios(exercicioDAO.listarPorTreino(t.getIdTreino()));
        }

        req.setAttribute("treinos", treinos);

        req.getRequestDispatcher("/WEB-INF/treinos.jsp").forward(req, resp);
    }

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp)
                throws IOException {

            HttpSession sessao = req.getSession();
            UsuarioModel usuario = (UsuarioModel) sessao.getAttribute("usuarioLogado");

            TreinoModel t = new TreinoModel();
            t.setIdUsuario(usuario.getIdUsuario());
            t.setNome(req.getParameter("nome"));
            t.setCategoria(req.getParameter("categoria"));
            t.setStatus(req.getParameter("status"));

            TreinoDAO.inserir(t);

            resp.sendRedirect("treinos");
        }
    }
