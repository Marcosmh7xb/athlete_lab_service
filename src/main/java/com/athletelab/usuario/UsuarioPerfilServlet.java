package com.athletelab.usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/perfil")
public class UsuarioPerfilServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession sessao = req.getSession(false);

        if (sessao == null) {
            resp.sendRedirect(req.getContextPath() + "/index");
            return;
        }

        UsuarioModel usuario = (UsuarioModel) sessao.getAttribute("usuarioLogado");

        if (usuario == null) {
            resp.sendRedirect(req.getContextPath() + "/index");
            return;
        }

        String tipoUsuario = usuario.getTipoUsuario();

        if ("TREINADOR".equals(tipoUsuario)) {

            resp.sendRedirect(req.getContextPath() + "/perfil-treinador");

        } else if ("ATLETA".equals(tipoUsuario)) {

            resp.sendRedirect(req.getContextPath() + "/perfil-atleta");

        } else {

            resp.sendRedirect(req.getContextPath() + "/index");
        }
    }
}