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
        requisicao.setAttribute("tipoUsuario", tipoUsuario);

        // CORREÇÃO: Adicionada a "/" inicial para garantir o caminho a partir da raiz
        RequestDispatcher dispatcher = requisicao.getRequestDispatcher("/WEB-INF/login.jsp");
        dispatcher.forward(requisicao, resposta);
    }

    @Override
    protected void doPost(HttpServletRequest requisicao, HttpServletResponse resposta)
            throws ServletException, IOException {
        System.out.println(">>>> CHEGOU NO DO-POST DO LOGIN! <<<<");
        String email = requisicao.getParameter("email");
        String senha = requisicao.getParameter("senha");
        String tipoUsuario = requisicao.getParameter("tipoUsuario");

        // 1. Validação básica antes de ir ao banco
        if (email == null || email.isBlank() || senha == null || senha.isBlank() || tipoUsuario == null || tipoUsuario.isBlank()) {
            requisicao.setAttribute("erro", "Preencha todos os campos corretamente.");
            requisicao.setAttribute("tipoUsuario", tipoUsuario);
            requisicao.getRequestDispatcher("/WEB-INF/login.jsp").forward(requisicao, resposta);
            return;
        }

        // 2. Tentativa de login
        UsuarioModel usuario = UsuarioDAO.login(email, senha, tipoUsuario);

        if (usuario != null) {
            // SUCESSO
            HttpSession sessao = requisicao.getSession();
            sessao.setAttribute("usuarioLogado", usuario);
            System.out.println("DEBUG LOGIN: Usuário encontrado! Nome: " + usuario.getNome() + " | ID: " + usuario.getIdUsuario());

            if ("TREINADOR".equalsIgnoreCase(usuario.getTipoUsuario())) {
                // O navegador vai para: http://localhost:8080/athletelab/perfil-treinador
                resposta.sendRedirect(requisicao.getContextPath() + "/perfil-treinador");
            } else {
                // Supondo que você tenha um Servlet mapeado como "/home"
                resposta.sendRedirect(requisicao.getContextPath() + "/home");
            }

        } else {
            // FALHA: Usuário não encontrado ou senha errada
            requisicao.setAttribute("erro", "E-mail, senha ou perfil inválidos.");
            requisicao.setAttribute("tipoUsuario", tipoUsuario); // Mantém o tipo para o hidden não esvaziar

            // CORREÇÃO: Caminho do dispatcher ajustado para a sua estrutura de pastas
            RequestDispatcher dispatcher = requisicao.getRequestDispatcher("/WEB-INF/login.jsp");
            dispatcher.forward(requisicao, resposta);
        }
    }
}