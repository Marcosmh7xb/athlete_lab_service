package com.athletelab.Treinador;

import com.athletelab.usuario.UsuarioDAO;
import com.athletelab.usuario.UsuarioModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/perfil-treinador")
public class PerfilTreinadorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        // Verifica se existe sessão
        if (session == null) {
            System.out.println("DEBUG PERFIL: Sessão nula. Redirecionando para login.");
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }

        UsuarioModel usuarioSessao =
                (UsuarioModel) session.getAttribute("usuarioLogado");

        // Verifica se usuário está logado
        if (usuarioSessao == null) {
            System.out.println("DEBUG PERFIL: Usuário não encontrado na sessão.");
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        try {

            UsuarioDAO dao = new UsuarioDAO();

            UsuarioModel perfilCompleto =
                    dao.buscarPorId(usuarioSessao.getIdUsuario());

            if (perfilCompleto != null) {

                request.setAttribute("perfil", perfilCompleto);

                request.getRequestDispatcher("/WEB-INF/perfilTreinador.jsp")
                        .forward(request, response);

            } else {

                response.sendRedirect(
                        request.getContextPath()
                                + "/login?erro=usuario_inexistente"
                );
            }

        } catch (Exception e) {

            e.printStackTrace();

            response.sendRedirect(
                    request.getContextPath()
                            + "/login?erro=falha_interna"
            );
        }
    }
}