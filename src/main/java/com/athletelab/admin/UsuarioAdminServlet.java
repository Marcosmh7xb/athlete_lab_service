package com.athletelab.admin;

import java.io.IOException;
import java.time.LocalDate;
import com.athletelab.usuario.UsuarioDAO;
import com.athletelab.usuario.UsuarioModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/admin/usuario")
public class UsuarioAdminServlet extends HttpServlet {

    private UsuarioDAO dao = new UsuarioDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String acao = request.getParameter("acao");

        try {
            // ================= NOVO (AGORA CONTROLADO PELO MODAL) =================
            if ("novo".equals(acao)) {
                response.sendRedirect(request.getContextPath() + "/admin");
                return;
            }

            // ================= EDITAR =================
            else if ("editar".equals(acao)) {

                int id = Integer.parseInt(request.getParameter("id"));

                UsuarioModel usuario = dao.buscarPorId(id);

                // envia usuário para o ADMIN JSP (modal vai usar via JS futuramente)
                request.setAttribute("usuarioEditar", usuario);

                request.getRequestDispatcher("/paginas/admin/registros.jsp")
                        .forward(request, response);
            }

            // ================= DELETAR =================
            else if ("deletar".equals(acao)) {

                int id = Integer.parseInt(request.getParameter("id"));

                dao.deletar(id);

                response.sendRedirect(request.getContextPath() + "/admin");
            }

            // ================= PADRÃO =================
            else {
                response.sendRedirect(request.getContextPath() + "/admin");
            }

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            String id = request.getParameter("id");

            UsuarioModel u = new UsuarioModel();

            u.setNome(request.getParameter("nome"));
            u.setEmail(request.getParameter("email"));
            u.setSenha(request.getParameter("senha"));
            u.setTelefone(request.getParameter("telefone"));
            u.setCidadeUF(request.getParameter("cidade_uf"));
            u.setTipoUsuario(request.getParameter("tipo_usuario"));

            // ================= CREATE =================
            if (id == null || id.isEmpty()) {

                u.setAtivo(true);
                u.setDataCriacao(LocalDate.now());

                String dataNascimento = request.getParameter("data_nascimento");

                if (dataNascimento != null && !dataNascimento.isEmpty()) {
                    u.setDataNascimento(LocalDate.parse(dataNascimento));
                }

                UsuarioDAO.inserir(u);
            }

            // ================= UPDATE =================
            else {

                u.setIdUsuario(Integer.parseInt(id));

                String dataNascimento = request.getParameter("data_nascimento");

                if (dataNascimento != null && !dataNascimento.isEmpty()) {
                    u.setDataNascimento(LocalDate.parse(dataNascimento));
                }

                dao.atualizar(u);
            }

            response.sendRedirect(request.getContextPath() + "/admin");

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}