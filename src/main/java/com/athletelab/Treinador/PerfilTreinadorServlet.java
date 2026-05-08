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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        UsuarioModel usuarioSessao = (UsuarioModel) session.getAttribute("usuarioLogado");

        // 1. Verifica se existe sessão
        if (usuarioSessao == null) {
            System.out.println("DEBUG PERFIL: Sessão nula. Redirecionando para login.");
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        try {
            // 2. Busca dados atualizados
            UsuarioDAO dao = new UsuarioDAO();
            UsuarioModel perfilCompleto = dao.buscarPorId(usuarioSessao.getIdUsuario());

            if (perfilCompleto != null) {
                System.out.println("DEBUG PERFIL: Usuário encontrado no banco. Abrindo JSP.");
                request.setAttribute("perfil", perfilCompleto);
                request.getRequestDispatcher("/WEB-INF/perfilTreinador.jsp").forward(request, response);
            } else {
                System.out.println("DEBUG PERFIL: ID da sessão não existe no banco.");
                response.sendRedirect(request.getContextPath() + "/login?erro=usuario_inexistente");
            }

        } catch (Exception e) {
            System.out.println("Erro no Servlet de Perfil: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/login?erro=falha_interna");
        }
    }
}