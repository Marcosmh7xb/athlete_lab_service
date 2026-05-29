package com.athletelab.Treinador;

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
            // O buscarPorId agora traz o Usuario + PerfilTreinador (via JOIN)
            UsuarioModel perfil = dao.buscarPorId(logado.getIdUsuario());
            request.setAttribute("perfil", perfil);
            request.getRequestDispatcher("/WEB-INF/editarPerfil.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. Iniciamos o try para capturar qualquer erro e evitar o "Erro 500" seco
        try {
            System.out.println(">>>> [DEBUG] INICIANDO PROCESSAMENTO DO POST <<<<");
            HttpSession session = request.getSession();
            UsuarioModel logado = (UsuarioModel) session.getAttribute("usuarioLogado");

            if (logado == null) {
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            }

            // 2. Captura dados básicos
            logado.setNome(request.getParameter("nome"));
            logado.setEmail(request.getParameter("email"));
            logado.setTelefone(request.getParameter("telefone"));
            logado.setCidadeUF(request.getParameter("cidadeUF"));
            logado.setDataNascimento(request.getParameter("dataNascimento"));

            // 3. Processamento da Foto (Sua lógica original)
            Part filePart = request.getPart("foto");
            if (filePart != null && filePart.getSubmittedFileName() != null && !filePart.getSubmittedFileName().isEmpty()) {
                String fileName = filePart.getSubmittedFileName();
                String uploadPath = getServletContext().getRealPath("") + File.separator + "banco_imagens" + File.separator + "icones";

                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) uploadDir.mkdirs();

                filePart.write(uploadPath + File.separator + fileName);
                logado.setFoto(fileName);
            }

            // 4. Lógica Específica do Treinador (Aqui é onde o bicho costuma pegar)
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

                // Chamada ao DAO do Treinador
                treinadorDao.salvarOuAtualizar(pt);

                // Importante: Vincula o perfil ao objeto logado
                logado.setPerfilTreinador(pt);
            }

            // 5. Atualiza o banco principal (Tabela Usuario)
            dao.atualizarPerfil(logado);

            // 6. Busca o usuário atualizado com o JOIN do banco
            // Isso garante que o atributo 'perfilTreinador' venha preenchido
            UsuarioModel usuarioAtualizado = dao.buscarPorId(logado.getIdUsuario());

            // 7. Atualiza a sessão com o objeto que tem os dados do JOIN
            session.setAttribute("usuarioLogado", usuarioAtualizado);

            System.out.println(">>>> [DEBUG] Sessão atualizada. Perfil técnico presente? " + (usuarioAtualizado.getPerfilTreinador() != null));

            // 8. REDIRECIONA APENAS UMA VEZ E PARA O MÉTODO IMEDIATAMENTE
            response.sendRedirect(request.getContextPath() + "/perfil-treinador?sucesso=1");
            return; // Importante para não executar nada abaixo

        } catch (Exception e) {
            // Se der Erro 500, o motivo aparecerá aqui no terminal do Docker/IDE
            System.err.println(">>>> [ERRO NO EDITAR PERFIL]: " + e.getMessage());
            e.printStackTrace();

            // Opcional: Redirecionar para uma página de erro amigável
            response.sendRedirect(request.getContextPath() + "/perfil-treinador?erro=processamento");
        }

    }
}
