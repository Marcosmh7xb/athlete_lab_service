package com.athletelab.usuario;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;

@WebServlet("/login")
public class UsuarioServletLogin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest requisicao, HttpServletResponse resposta) throws ServletException, IOException {

        HttpSession sessao = requisicao.getSession();

        //  pega da sessão primeiro (CORRETO)
        String tipoUsuario = (String) sessao.getAttribute("tipoUsuario");

        //  se não tiver, pega da URL
        if (tipoUsuario == null) {
            tipoUsuario = requisicao.getParameter("tipoUsuario");
        }

        // pega o erro
        String erro = (String) sessao.getAttribute("erro");

        // envia pro JSP o tipo e erro
        requisicao.setAttribute("tipoUsuario", tipoUsuario);
        requisicao.setAttribute("erro", erro);

        // remove só o erro pra proxima tentativa
        sessao.removeAttribute("erro");

        // reiniciar a pagina de volta pra login
        RequestDispatcher dispatcher = requisicao.getRequestDispatcher("paginas/login.jsp");
        dispatcher.forward(requisicao, resposta);
    }

    @Override
    protected void doPost(HttpServletRequest requisicao, HttpServletResponse resposta)
            throws ServletException, IOException {

        String email = requisicao.getParameter("email");
        String senha = requisicao.getParameter("senha");
        String tipoUsuario = requisicao.getParameter("tipoUsuario");

        if (email == null || email.isBlank() || senha == null || senha.isBlank() || tipoUsuario == null || tipoUsuario.isBlank()) {

            HttpSession sessao = requisicao.getSession();
            sessao.setAttribute("erro", "Email ou senha não foram preenchidos.");
            sessao.setAttribute("tipoUsuario", tipoUsuario);

            sessao.getAttribute("erro");

            resposta.sendRedirect("login");
            return;
        }

        UsuarioModel usuario = UsuarioDAO.login(email, senha, tipoUsuario);

        if (usuario != null) {

            HttpSession sessao = requisicao.getSession();
            sessao.setAttribute("usuario", usuario);

            tipoUsuario = usuario.getTipoUsuario();

            if ("ATLETA".equals(tipoUsuario) || "TREINADOR".equals(tipoUsuario)) {
                resposta.sendRedirect("paginas/home.jsp");
            } else {
                resposta.sendRedirect("login?erro=true");
            }

        } else {
            HttpSession sessao = requisicao.getSession();
            sessao.setAttribute("tipoUsuario", tipoUsuario);
            sessao.setAttribute("erro", "Email ou senha inválidos, Por favor tente novamente");
            resposta.sendRedirect("login");
            return;
        }
    }
}

