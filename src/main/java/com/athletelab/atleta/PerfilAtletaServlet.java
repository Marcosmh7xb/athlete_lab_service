
        package com.athletelab.atleta;

import com.athletelab.usuario.UsuarioDAO;
import com.athletelab.usuario.UsuarioModel;

import com.athletelab.treino.TreinoDAO;
import com.athletelab.treino.TreinoModel;

import com.athletelab.equipe.EquipeDAO;
import com.athletelab.equipe.EquipeModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/perfil-atleta")
public class PerfilAtletaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        // =========================
        // VERIFICA SESSÃO
        // =========================

        if (session == null) {

            response.sendRedirect(
                    request.getContextPath() + "/login"
            );

            return;
        }

        UsuarioModel logado =
                (UsuarioModel) session.getAttribute("usuarioLogado");

        if (logado == null) {

            response.sendRedirect(
                    request.getContextPath() + "/login"
            );

            return;
        }

        try {

            // =========================
            // PERFIL COMPLETO
            // =========================

            UsuarioDAO usuarioDAO = new UsuarioDAO();

            UsuarioModel perfilCompleto =
                    usuarioDAO.buscarPorId(
                            logado.getIdUsuario()
                    );

            if (perfilCompleto == null) {

                response.sendRedirect(
                        request.getContextPath()
                                + "/login?erro=usuario_inexistente"
                );

                return;
            }

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
            // EQUIPES DO ATLETA
            // =========================

            EquipeDAO equipeDAO = new EquipeDAO();

            List<EquipeModel> equipes =
                    equipeDAO.buscarEquipesDoAtleta(
                            logado.getIdUsuario()
                    );

            request.setAttribute(
                    "minhasEquipes",
                    equipes
            );

            // =========================
            // FORWARD
            // =========================

            request.getRequestDispatcher(
                    "/WEB-INF/perfilAtleta.jsp"
            ).forward(request, response);

        } catch (Exception e) {

            e.printStackTrace();

            response.sendRedirect(
                    request.getContextPath()
                            + "/login?erro=falha_interna"
            );
        }
    }
}
