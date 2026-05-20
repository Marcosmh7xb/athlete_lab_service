package com.athletelab.atleta;

import com.athletelab.usuario.UsuarioDAO;
import com.athletelab.usuario.UsuarioModel;

import com.athletelab.treino.TreinoDAO;
import com.athletelab.treino.TreinoModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/perfil-atleta")
public class PerfilAtletaServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        UsuarioModel logado =
                (UsuarioModel) session.getAttribute("usuarioLogado");

        if (logado == null) {

            response.sendRedirect(
                    request.getContextPath() + "/login"
            );

            return;
        }

        // =========================
        // PERFIL COMPLETO
        // =========================

        UsuarioModel perfilCompleto =
                new UsuarioDAO().buscarPorId(
                        logado.getIdUsuario()
                );

        request.setAttribute(
                "perfil",
                perfilCompleto
        );

        // =========================
        // TREINOS DO ATLETA
        // =========================

        TreinoDAO treinoDAO = new TreinoDAO();

        List<TreinoModel> treinos =
                treinoDAO.listarPorAtleta(
                        logado.getIdUsuario()
                );

        request.setAttribute(
                "treinos",
                treinos
        );

        // =========================
        // FORWARD
        // =========================

        request.getRequestDispatcher(
                "/WEB-INF/perfilAtleta.jsp"
        ).forward(request, response);
    }
}