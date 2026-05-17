package com.athletelab.admin;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.athletelab.usuario.UsuarioDAO;
import com.athletelab.usuario.UsuarioModel;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest requisicao,
                         HttpServletResponse resposta)
            throws ServletException, IOException {

        HttpSession sessao = requisicao.getSession(false);

        // ================= VERIFICA LOGIN =================
        if (sessao == null) {
            resposta.sendRedirect(requisicao.getContextPath() + "/index.jsp");
            return;
        }

        // ================= PEGA USUÁRIO =================
        UsuarioModel admin =
                (UsuarioModel) sessao.getAttribute("usuario");

        // ================= VERIFICA NULL =================
        if (admin == null) {
            resposta.sendRedirect(requisicao.getContextPath() + "/index.jsp");
            return;
        }

        // ================= VERIFICA ADMIN =================
        if (!"ADMIN".equals(admin.getTipoUsuario())) {
            resposta.sendRedirect(requisicao.getContextPath() + "/index.jsp");
            return;
        }

        // ================= PARAMETROS =================
        String nome = requisicao.getParameter("nome");
        String id = requisicao.getParameter("id");
        String tipo = requisicao.getParameter("tipo");

        UsuarioDAO dao = new UsuarioDAO();

        List<UsuarioModel> usuarios = dao.listar();

        if (nome != null && !nome.isEmpty()) {
            usuarios = usuarios.stream()
                    .filter(u -> u.getNome() != null &&
                            u.getNome().toLowerCase().contains(nome.toLowerCase()))
                    .collect(Collectors.toList());
        }

        if (id != null && !id.isEmpty()) {
            try {
                int idInt = Integer.parseInt(id);

                usuarios = usuarios.stream()
                        .filter(u -> u.getIdUsuario() == idInt)
                        .collect(Collectors.toList());

            } catch (Exception ignored) {}
        }

        if (tipo != null && !tipo.isEmpty()) {
            usuarios = usuarios.stream()
                    .filter(u -> tipo.equals(u.getTipoUsuario()))
                    .collect(Collectors.toList());
        }

        requisicao.setAttribute("usuarios", usuarios);

        RequestDispatcher dispatcher =
                requisicao.getRequestDispatcher("WEB-INF/registros.jsp");

        dispatcher.forward(requisicao, resposta);
    }
}