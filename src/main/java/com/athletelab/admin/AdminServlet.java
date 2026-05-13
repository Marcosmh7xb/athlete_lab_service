package com.athletelab.admin;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import com.athletelab.usuario.UsuarioDAO;
import com.athletelab.usuario.UsuarioModel;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;


@WebServlet("/admin")
public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest requisicao, HttpServletResponse resposta) throws ServletException, IOException {

        HttpSession sessao = requisicao.getSession(false);

        // ================= VERIFICA LOGIN =================

        if(sessao == null
                || sessao.getAttribute("usuario") == null){

            resposta.sendRedirect("index.jsp");
            return;
        }

        UsuarioModel admin =
                (UsuarioModel) sessao.getAttribute("usuario");

        // ================= VERIFICA ADMIN =================

        if(!"ADMIN".equals(admin.getTipoUsuario())){

            resposta.sendRedirect("index.jsp");
            return;
        }

        // ================= PARAMETROS DE BUSCA =================

        String nome = requisicao.getParameter("nome");
        String id = requisicao.getParameter("id");
        String tipo = requisicao.getParameter("tipo");

        // ================= LISTA BASE =================

        UsuarioDAO dao = new UsuarioDAO();

        List<UsuarioModel> usuarios =
                dao.listar();

        // ================= FILTRO POR NOME =================

        if (nome != null && !nome.isEmpty()) {
            usuarios = usuarios.stream()
                    .filter(u -> u.getNome() != null &&
                            u.getNome().toLowerCase().contains(nome.toLowerCase()))
                    .collect(Collectors.toList());
        }

        // ================= FILTRO POR ID =================

        if (id != null && !id.isEmpty()) {
            try {
                int idInt = Integer.parseInt(id);

                usuarios = usuarios.stream()
                        .filter(u -> u.getIdUsuario() == idInt)
                        .collect(Collectors.toList());

            } catch (Exception e) {
                // ignora erro de conversão
            }
        }

        // ================= FILTRO POR TIPO =================

        if (tipo != null && !tipo.isEmpty()) {
            usuarios = usuarios.stream()
                    .filter(u -> tipo.equals(u.getTipoUsuario()))
                    .collect(Collectors.toList());
        }

        // ================= ENVIA PARA JSP =================

        requisicao.setAttribute("usuarios", usuarios);

        RequestDispatcher dispatcher =
                requisicao.getRequestDispatcher(
                        "paginas/registros.jsp"
                );

        dispatcher.forward(requisicao, resposta);
    }
}