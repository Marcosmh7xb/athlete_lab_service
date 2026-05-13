package com.athletelab.usuario;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/login")
public class UsuarioServletLogin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest requisicao, HttpServletResponse resposta) throws ServletException, IOException {

        String tipoUsuario = requisicao.getParameter("tipoUsuario");
        HttpSession sessao = requisicao.getSession();

        if (tipoUsuario != null) {
            sessao.setAttribute("tipoUsuario", tipoUsuario);
        }

        RequestDispatcher dispatcher = requisicao.getRequestDispatcher("WEB-INF/login.jsp");
        dispatcher.forward(requisicao, resposta);
    }

    @Override
    protected void doPost(HttpServletRequest requisicao, HttpServletResponse resposta)
            throws ServletException, IOException {

        String email = requisicao.getParameter("email");
        String senha = requisicao.getParameter("senha");
        String tipoUsuario = requisicao.getParameter("tipoUsuario");

        if (email == null || email.isBlank() || senha == null || senha.isBlank() || tipoUsuario == null || tipoUsuario.isBlank()) {

            requisicao.setAttribute("erro", "Preencha email e senha");

            RequestDispatcher dispatcher =
                    requisicao.getRequestDispatcher("WEB-INF/login.jsp");

            dispatcher.forward(requisicao, resposta);

            return;
        }

        UsuarioModel usuario = UsuarioDAO.login(email, senha, tipoUsuario);

        if (usuario != null) {

            HttpSession sessao = requisicao.getSession();
            sessao.setAttribute("usuarioLogado", usuario);

            tipoUsuario = usuario.getTipoUsuario();

            if ("ATLETA".equals(tipoUsuario) || "TREINADOR".equals(tipoUsuario)) {
                resposta.sendRedirect(requisicao.getContextPath() + "/home");
            } else if ("ADMIN".equals(tipoUsuario)) {

                resposta.sendRedirect("");

            } else {

                resposta.sendRedirect("login?erro=true");

            }

        } else {

            requisicao.setAttribute("erro", "Email ou senha inválidos");

            RequestDispatcher dispatcher =
                    requisicao.getRequestDispatcher("WEB-INF/login.jsp");

            dispatcher.forward(requisicao, resposta);

        }
    }
}

