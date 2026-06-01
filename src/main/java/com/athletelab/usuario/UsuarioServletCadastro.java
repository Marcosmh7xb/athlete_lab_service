package com.athletelab.usuario;

import java.io.IOException;
import java.time.LocalDate;

import org.mindrot.jbcrypt.BCrypt;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/cadastro")
public class UsuarioServletCadastro extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest requisicao, HttpServletResponse resposta)
            throws ServletException, IOException {

        String tipoUsuario = requisicao.getParameter("tipoUsuario");
        requisicao.setAttribute("tipoUsuario", tipoUsuario);

        RequestDispatcher dispatcher =
                requisicao.getRequestDispatcher("WEB-INF/cadastro.jsp");
        dispatcher.forward(requisicao, resposta);
    }

    @Override
    protected void doPost(HttpServletRequest requisicao, HttpServletResponse resposta)
            throws ServletException, IOException {

        String nome = requisicao.getParameter("nome");
        String email = requisicao.getParameter("email");
        String senha = requisicao.getParameter("senha");
        String senhaCriptografada = BCrypt.hashpw(senha, BCrypt.gensalt());

        String telefone = requisicao.getParameter("telefone");
        String cidadeUF = requisicao.getParameter("cidadeUF");

        String tipoUsuario = requisicao.getParameter("tipoUsuario");
        requisicao.setAttribute("tipoUsuario", tipoUsuario);

        String dataNascimentoStr = requisicao.getParameter("dataNascimento");
        LocalDate dataNascimento = null;

        if (dataNascimentoStr != null && !dataNascimentoStr.isEmpty()) {
            dataNascimento = LocalDate.parse(dataNascimentoStr);
        }

        UsuarioModel usuario = new UsuarioModel();

        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(senhaCriptografada);
        usuario.setTelefone(telefone);
        usuario.setCidadeUF(cidadeUF);
        usuario.setTipoUsuario(tipoUsuario);
        usuario.setDataNascimento(String.valueOf(dataNascimento));
        usuario.setDataCriacao(LocalDate.now());
        usuario.setAtivo(true);

        UsuarioDAO dao = new UsuarioDAO();
        dao.inserir(usuario);

        resposta.sendRedirect(
                requisicao.getContextPath() + "/index.jsp"
        );
    }
}