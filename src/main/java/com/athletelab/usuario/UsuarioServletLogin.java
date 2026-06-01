package com.athletelab.usuario;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class UsuarioServletLogin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest requisicao, HttpServletResponse resposta)
            throws ServletException, IOException {

        String tipoUsuario = requisicao.getParameter("tipoUsuario");
        HttpSession sessao = requisicao.getSession();

        if (tipoUsuario != null) {
            sessao.setAttribute("tipoUsuario", tipoUsuario);
        }

        RequestDispatcher dispatcher =
                requisicao.getRequestDispatcher("WEB-INF/login.jsp");
        dispatcher.forward(requisicao, resposta);
    }

    @Override
    protected void doPost(HttpServletRequest requisicao, HttpServletResponse resposta)
            throws ServletException, IOException {

        String email = requisicao.getParameter("email");
        String senha = requisicao.getParameter("senha");
        HttpSession sessao = requisicao.getSession();
        String tipoUsuario = (String) sessao.getAttribute("tipoUsuario");

        // ================= ADMIN TEMPORÁRIO =================
        if (email != null && email.equals("adm013@gmail.com")
                && senha != null && senha.equals("adm1234")) {

            UsuarioModel admin = new UsuarioModel();

            admin.setNome("Cauã Lobato");
            admin.setEmail(email);
            admin.setTipoUsuario("ADMIN");

            sessao.setAttribute("usuarioLogado", admin);

            resposta.sendRedirect("admin");
            return;
        }

        if (email == null || email.isBlank()
                || senha == null || senha.isBlank()
                || tipoUsuario == null || tipoUsuario.isBlank()) {

            requisicao.setAttribute("erro", "Preencha email e senha");

            RequestDispatcher dispatcher =
                    requisicao.getRequestDispatcher("WEB-INF/login.jsp");

            dispatcher.forward(requisicao, resposta);
            return;
        }

        UsuarioDAO dao = new UsuarioDAO();
        UsuarioModel usuario = dao.login(email, senha, tipoUsuario);

        if (usuario != null) {

            HttpSession sessaoAntiga = requisicao.getSession(false);

            if (sessaoAntiga != null) {
                sessaoAntiga.invalidate();
            }

            sessao = requisicao.getSession();
            sessao.setAttribute("usuarioLogado", usuario);

            tipoUsuario = usuario.getTipoUsuario();

            if ("TREINADOR".equals(tipoUsuario) || "ATLETA".equals(tipoUsuario)) {

                resposta.sendRedirect(requisicao.getContextPath() + "/home");

            } else if ("ADMIN".equals(tipoUsuario)) {

                resposta.sendRedirect(requisicao.getContextPath() + "/admin");

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