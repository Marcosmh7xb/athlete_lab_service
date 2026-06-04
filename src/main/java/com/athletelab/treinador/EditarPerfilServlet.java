package com.athletelab.treinador;

import com.athletelab.usuario.UsuarioDAO;
import com.athletelab.usuario.UsuarioModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.File;
import java.io.IOException;

@WebServlet("/editar-perfil")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10,      // 10MB
        maxRequestSize = 1024 * 1024 * 50    // 50MB
)
public class EditarPerfilServlet extends HttpServlet {

    private UsuarioDAO dao = new UsuarioDAO();
    private TreinadorDAO treinadorDao = new TreinadorDAO(); // INSTÂNCIA DO NOVO DAO

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UsuarioModel logado = (UsuarioModel) session.getAttribute("usuarioLogado");

        if (logado != null) {
            UsuarioModel perfil = dao.buscarPorId(logado.getIdUsuario());
            request.setAttribute("perfil", perfil);
            request.getRequestDispatcher("/WEB-INF/editarPerfil.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            System.out.println(">>>> [DEBUG] INICIANDO PROCESSAMENTO DO POST <<<<");
            HttpSession session = request.getSession();
            UsuarioModel logado = (UsuarioModel) session.getAttribute("usuarioLogado");

            if (logado == null) {
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            }

            logado.setNome(request.getParameter("nome"));
            logado.setEmail(request.getParameter("email"));
            logado.setTelefone(request.getParameter("telefone"));
            logado.setCidadeUF(request.getParameter("cidadeUF"));
            logado.setDataNascimento(request.getParameter("dataNascimento"));

            Part filePart = request.getPart("foto");
            if (filePart != null && filePart.getSubmittedFileName() != null && !filePart.getSubmittedFileName().isEmpty()) {

                String fileName = filePart.getSubmittedFileName();
                String uploadPath = getServletContext().getRealPath("") + File.separator + "banco_imagens" + File.separator + "icones";
                File uploadDir = new File(uploadPath);

                if (!uploadDir.exists()) uploadDir.mkdirs();

                filePart.write(uploadPath + File.separator + fileName);
                logado.setFoto(fileName);
            }


            if ("TREINADOR".equals(logado.getTipoUsuario())) {
                System.out.println(">>>> [DEBUG] PREPARANDO PERFIL TÉCNICO...");

                PerfilTreinadorModel pt = new PerfilTreinadorModel();
                pt.setIdUsuario(logado.getIdUsuario());
                pt.setModalidade(request.getParameter("modalidade"));
                pt.setNivelExperiencia(request.getParameter("nivelExperiencia"));
                pt.setObjetivo(request.getParameter("objetivo"));
                pt.setAmbiente(request.getParameter("ambiente"));
                pt.setSexo(request.getParameter("sexo"));
                pt.setRestricaoFisica(request.getParameter("restricaoFisica"));


                treinadorDao.salvarOuAtualizar(pt);
                logado.setPerfilTreinador(pt);
            }

            dao.atualizarPerfil(logado);
            UsuarioModel usuarioAtualizado = dao.buscarPorId(logado.getIdUsuario());
            session.setAttribute("usuarioLogado", usuarioAtualizado);

            System.out.println(">>>> [DEBUG] Sessão atualizada. Perfil técnico presente? " + (usuarioAtualizado.getPerfilTreinador() != null));
            response.sendRedirect(request.getContextPath() + "/perfil-treinador?sucesso=1");
            return;

        } catch (Exception erro) {
            System.err.println(">>>> [ERRO NO EDITAR PERFIL]: " + erro.getMessage());
            erro.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/perfil-treinador?erro=processamento");
        }

    }
}
