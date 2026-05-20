package com.athletelab.treino;

import com.athletelab.usuario.UsuarioModel;
import com.athletelab.usuario.UsuarioDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/criartreino")
public class CriarTreinoServlet extends HttpServlet {

    private TreinoDAO treinoDAO = new TreinoDAO();
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/criar_treino.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");

        if ("salvar".equals(acao)) {

            HttpSession session = request.getSession();

            UsuarioModel usuarioLogado =
                    (UsuarioModel) session.getAttribute("usuarioLogado");

            if (usuarioLogado == null) {

                response.sendRedirect(
                        request.getContextPath() + "/login.jsp"
                );

                return;
            }

            String nome = request.getParameter("nome");
            String categoria = request.getParameter("categoria");
            String status = request.getParameter("status");

            String email = request.getParameter("email");

            TreinoModel treino = new TreinoModel();

            treino.setNome(nome);
            treino.setCategoria(categoria);
            treino.setStatus(status);

            // =====================================
            // ADMIN
            // cria treino global do sistema
            // =====================================

            if ("ADMIN".equals(usuarioLogado.getTipoUsuario())) {

                treino.setIdUsuario(
                        usuarioLogado.getIdUsuario()
                );

                int idTreino =
                        treinoDAO.inserir(treino);

                response.sendRedirect(

                        request.getContextPath()
                                + "/treino/editar?idTreino="
                                + idTreino
                );

                return;
            }

            // =====================================
            // TREINADOR
            // precisa do email do atleta
            // =====================================

            if (email == null || email.isBlank()) {

                throw new RuntimeException(
                        "Informe o email do atleta."
                );
            }

            UsuarioModel atleta =
                    usuarioDAO.buscarPorEmail(email);

            if (atleta == null) {

                throw new RuntimeException(
                        "Atleta não encontrado."
                );
            }

            // treino pertence ao treinador
            treino.setIdUsuario(
                    usuarioLogado.getIdUsuario()
            );

            int idTreino =
                    treinoDAO.inserir(treino);

            // atribui ao atleta
            treinoDAO.atribuirTreino(
                    idTreino,
                    atleta.getIdUsuario(),
                    usuarioLogado.getIdUsuario()
            );

            response.sendRedirect(

                    request.getContextPath()
                            + "/treino/editar?idTreino="
                            + idTreino
            );
        }
    }


}