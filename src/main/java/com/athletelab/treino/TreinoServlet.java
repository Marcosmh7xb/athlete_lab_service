package com.athletelab.treino;

import com.athletelab.exercicio.ExercicioDAO;
import com.athletelab.usuario.UsuarioModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/treinos")
public class TreinoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession sessao = req.getSession(false);

        if (sessao == null || sessao.getAttribute("usuarioLogado") == null) {
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        UsuarioModel usuario =
                (UsuarioModel) sessao.getAttribute("usuarioLogado");

        TreinoDAO treinoDAO = new TreinoDAO();
        ExercicioDAO exercicioDAO = new ExercicioDAO();

        List<TreinoModel> treinos;
        String tipo = usuario.getTipoUsuario();

        // ATLETA
        if ("ATLETA".equals(tipo)) {

            treinos = treinoDAO.listarPorAtleta(usuario.getIdUsuario());

            // só ativos
            treinos.removeIf(t ->
                    !"ATIVO".equalsIgnoreCase(t.getStatus()));
        }

        // TREINADOR
        else if ("TREINADOR".equals(tipo)) {

            treinos = treinoDAO.listarParaTreinador(
                    usuario.getIdUsuario()
            );
        }

        // ADMIN
        else {

            treinos = treinoDAO.listarTodos();
        }

        for (TreinoModel t : treinos) {
            t.setExercicios(
                    exercicioDAO.listarPorTreino(t.getIdTreino())
            );
        }

        req.setAttribute("treinos", treinos);

        req.getRequestDispatcher("/WEB-INF/treinos.jsp")
                .forward(req, resp);
    }
}