package com.athletelab.atleta;

import com.athletelab.usuario.UsuarioDAO;
import com.athletelab.usuario.UsuarioModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/perfil-atleta")
public class PerfilAtletaServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UsuarioModel logado = (UsuarioModel) session.getAttribute("usuarioLogado");

        if (logado == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        UsuarioModel perfilCompleto = new UsuarioDAO().buscarPorId(logado.getIdUsuario());
        request.setAttribute("perfil", perfilCompleto);

        // Manda SEMPRE para a tela de visualização
        request.getRequestDispatcher("/WEB-INF/perfilAtleta.jsp").forward(request, response);
    }
}
