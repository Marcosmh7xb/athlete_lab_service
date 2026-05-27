package com.athletelab.admin;

import java.io.IOException;

import com.athletelab.usuario.UsuarioModel;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/admin/painel")
public class AdminPainelServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        // ================= LOGIN =================
        if (session == null) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }

        UsuarioModel admin =
                (UsuarioModel) session.getAttribute("usuarioLogado");

        // ================= VALIDA ADMIN =================
        if (admin == null || !"ADMIN".equals(admin.getTipoUsuario())) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }

        // ================= ABRE PAINEL =================
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/WEB-INF/paineladm.jsp");

        dispatcher.forward(request, response);
    }
}