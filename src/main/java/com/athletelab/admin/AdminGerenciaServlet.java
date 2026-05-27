package com.athletelab.admin;

import java.io.IOException;

import com.athletelab.usuario.UsuarioModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/admin/treinos")
public class AdminGerenciaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {

        // ================= SESSÃO =================
        HttpSession session = req.getSession(false);

        if (session == null) {
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        // ================= USUÁRIO LOGADO =================
        UsuarioModel admin =
                (UsuarioModel) session.getAttribute("usuarioLogado");

        // ================= VALIDAÇÃO =================
        if (admin == null) {
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        if (!"ADMIN".equals(admin.getTipoUsuario())) {
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        // ================= ACESSO LIBERADO =================
        req.getRequestDispatcher("/WEB-INF/adm_treinos_equipes.jsp")
                .forward(req, resp);
    }
}