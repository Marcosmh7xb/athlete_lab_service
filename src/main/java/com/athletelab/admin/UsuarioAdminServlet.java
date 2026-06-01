package com.athletelab.admin;

import java.io.IOException;
import java.time.LocalDate;

import org.mindrot.jbcrypt.BCrypt;

import com.athletelab.usuario.UsuarioDAO;
import com.athletelab.usuario.UsuarioModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/admin/usuario")
public class UsuarioAdminServlet extends HttpServlet {

    private UsuarioDAO dao = new UsuarioDAO();

    private boolean isAdmin(HttpServletRequest request) {

        HttpSession sessao = request.getSession(false);

        if (sessao == null) return false;

        UsuarioModel user = (UsuarioModel) sessao.getAttribute("usuarioLogado");

        return user != null && "ADMIN".equals(user.getTipoUsuario());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // ================= SEGURANÇA ADMIN =================
        if (!isAdmin(request)) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }

        String acao = request.getParameter("acao");

        try {

            if ("novo".equals(acao)) {
                response.sendRedirect(request.getContextPath() + "/admin");
                return;
            }

            else if ("editar".equals(acao)) {

                int id = Integer.parseInt(request.getParameter("id"));

                UsuarioModel usuario = dao.buscarPorId(id);

                request.setAttribute("usuarioEditar", usuario);

                request.getRequestDispatcher("/WEB-INF/admin/registros.jsp")
                        .forward(request, response);
            }

            else if ("deletar".equals(acao)) {

                int id = Integer.parseInt(request.getParameter("id"));

                dao.deletar(id);

                response.sendRedirect(request.getContextPath() + "/admin/registros");
            }

            else {
                response.sendRedirect(request.getContextPath() + "/admin/painel");
            }

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // ================= SEGURANÇA ADMIN =================
        if (!isAdmin(request)) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }

        try {

            String id = request.getParameter("id");

            UsuarioModel u = new UsuarioModel();

            u.setNome(request.getParameter("nome"));
            u.setEmail(request.getParameter("email"));
            u.setSenha(BCrypt.hashpw(request.getParameter("senha"), BCrypt.gensalt()));
            u.setTelefone(request.getParameter("telefone"));
            u.setCidadeUF(request.getParameter("cidade_uf"));
            u.setTipoUsuario(request.getParameter("tipo_usuario"));

            String dataNascimento = request.getParameter("data_nascimento");

            if (dataNascimento != null && !dataNascimento.isEmpty()) {
                u.setDataNascimento(String.valueOf(LocalDate.parse(dataNascimento)));
            }

            if (id == null || id.isEmpty()) {

                u.setAtivo(true);
                u.setDataCriacao(LocalDate.now());

                dao.inserir(u);

            } else {

                u.setIdUsuario(Integer.parseInt(id));

                dao.atualizar(u);
            }

            response.sendRedirect(request.getContextPath() + "/admin/registros");

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}