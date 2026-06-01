package com.athletelab.treino;

import com.athletelab.usuario.UsuarioDAO;
import com.athletelab.usuario.UsuarioModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/treino/editarInfo")
public class EditarTreinoServelet extends HttpServlet {

    private TreinoDAO treinoDAO = new TreinoDAO();
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        int idTreino = Integer.parseInt(
                request.getParameter("idTreino")
        );

        TreinoModel treino = treinoDAO.buscarPorId(idTreino);

        request.setAttribute("treino", treino);

        request.getRequestDispatcher("/WEB-INF/editar_treino_info.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        try {

            // DEBUG
            System.out.println("===== EDITANDO TREINO =====");
            System.out.println("ID recebido: " + request.getParameter("idTreino"));
            System.out.println("Nome: " + request.getParameter("nome"));
            System.out.println("Categoria: " + request.getParameter("categoria"));
            System.out.println("Status recebido: " + request.getParameter("status"));

            TreinoModel treino = new TreinoModel();

            treino.setIdTreino(
                    Integer.parseInt(request.getParameter("idTreino"))
            );

            treino.setNome(request.getParameter("nome"));
            treino.setCategoria(request.getParameter("categoria"));
            treino.setStatus(request.getParameter("status"));

            // Atualiza treino
            treinoDAO.atualizar(treino);

            System.out.println("Treino atualizado com status: "
                    + treino.getStatus());

            // atribuição opcional
            String email = request.getParameter("email");

            if (email != null && !email.isBlank()) {

                UsuarioModel atleta =
                        usuarioDAO.buscarPorEmail(email);

                if (atleta != null) {

                    HttpSession session = request.getSession();

                    UsuarioModel treinador =
                            (UsuarioModel) session.getAttribute("usuarioLogado");

                    if (!treinoDAO.treinoJaAtribuido(
                            treino.getIdTreino(),
                            atleta.getIdUsuario())) {

                        treinoDAO.atribuirTreino(
                                treino.getIdTreino(),
                                atleta.getIdUsuario(),
                                treinador.getIdUsuario()
                        );

                        System.out.println("Treino atribuído ao atleta.");
                    }
                }
            }

            response.sendRedirect(
                    request.getContextPath() + "/perfil-treinador"
            );

        } catch (Exception e) {

            e.printStackTrace();

            request.setAttribute("erro", e.getMessage());

            request.getRequestDispatcher("/WEB-INF/editar_treino_info.jsp")
                    .forward(request, response);
        }
    }
}