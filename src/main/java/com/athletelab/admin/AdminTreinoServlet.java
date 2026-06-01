package com.athletelab.admin;

import com.athletelab.usuario.UsuarioModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/criartreino")
public class AdminTreinoServlet extends HttpServlet {

    private final AdminDAO adminTreinoDAO = new AdminDAO();

    // =========================
    // LISTAR / EDITAR
    // =========================
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        UsuarioModel usuario =
                (UsuarioModel) session.getAttribute("usuarioLogado");

        if (usuario == null ||
                !"ADMIN".equals(usuario.getTipoUsuario())) {

            response.sendRedirect(
                    request.getContextPath() + "/login.jsp"
            );
            return;
        }

        try {

            String id = request.getParameter("id");

            if (id != null) {

                AdminModel treino =
                        adminTreinoDAO.buscarPorId(
                                Integer.parseInt(id)
                        );

                request.setAttribute("treinoEditar", treino);
            }

            List<AdminModel> treinos =
                    adminTreinoDAO.listarTodos();

            request.setAttribute("treinos", treinos);

            request.getRequestDispatcher(
                    "/WEB-INF/admin_treino.jsp"
            ).forward(request, response);

        } catch (Exception e) {

            request.setAttribute("erro", e.getMessage());

            request.getRequestDispatcher(
                    "/WEB-INF/admin_treino.jsp"
            ).forward(request, response);
        }
    }

    // =========================
    // CRIAR / EDITAR / EXCLUIR
    // =========================
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        UsuarioModel usuario =
                (UsuarioModel) session.getAttribute("usuarioLogado");

        if (usuario == null ||
                !"ADMIN".equals(usuario.getTipoUsuario())) {

            response.sendError(
                    HttpServletResponse.SC_FORBIDDEN
            );

            return;
        }

        try {

            String acao = request.getParameter("acao");

            // =========================
            // CRIAR
            // =========================
            if ("salvar".equals(acao)) {

                AdminModel treino =
                        new AdminModel();

                treino.setNome(
                        request.getParameter("nome")
                );

                treino.setCategoria(
                        request.getParameter("categoria")
                );

                treino.setStatus(
                        request.getParameter("status")
                );

                adminTreinoDAO.inserir(treino);
            }

            // =========================
            // ATUALIZAR
            // =========================
            else if ("editar".equals(acao)) {

                AdminModel treino =
                        new AdminModel();

                treino.setIdTreino(
                        Integer.parseInt(
                                request.getParameter("idTreino")
                        )
                );

                treino.setNome(
                        request.getParameter("nome")
                );

                treino.setCategoria(
                        request.getParameter("categoria")
                );

                treino.setStatus(
                        request.getParameter("status")
                );

                adminTreinoDAO.atualizar(treino);
            }

            // =========================
            // DELETAR
            // =========================
            else if ("deletar".equals(acao)) {

                int idTreino = Integer.parseInt(
                        request.getParameter("idTreino")
                );

                adminTreinoDAO.deletar(idTreino);
            }

            response.sendRedirect(
                    request.getContextPath() + "/admin/treinos"
            );

        } catch (Exception e) {

            request.setAttribute("erro", e.getMessage());

            doGet(request, response);
        }
    }
}