package com.athletelab.atleta;

import com.athletelab.usuario.UsuarioDAO;
import com.athletelab.usuario.UsuarioModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.File;
import java.io.IOException;

@WebServlet("/editar-perfil-atleta")
@MultipartConfig
public class EditarPerfilAtletaServlet extends HttpServlet {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private PerfilAtletaDAO atletaDAO = new PerfilAtletaDAO();

    // 1. CARREGA OS DADOS PARA O FORMULÁRIO (Resolve o problema de não mostrar info)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UsuarioModel logado = (UsuarioModel) session.getAttribute("usuarioLogado");

        if (logado == null) {
            response.sendRedirect("login");
            return;
        }

        // AQUI ESTÁ O SEGREDO:
        // Não use apenas o 'logado' da sessão. Vá ao banco buscar os dados ATUAIS.
        UsuarioDAO dao = new UsuarioDAO();
        UsuarioModel perfilCompleto = dao.buscarPorId(logado.getIdUsuario());

        // Agora o JSP vai receber o objeto preenchido com o que já existe no banco
        request.setAttribute("perfil", perfilCompleto);

        request.getRequestDispatcher("/WEB-INF/editarPerfilAtleta.jsp").forward(request, response);
    }

    // 2. SALVA AS ALTERAÇÕES (Resolve o Erro 500)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // 1. Pegamos o usuário que JÁ ESTÁ na sessão
        UsuarioModel logado = (UsuarioModel) session.getAttribute("usuarioLogado");

        // Se por algum motivo a sessão expirou enquanto ele preenchia, manda pro login
        if (logado == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        try {
            // 2. TRATAMENTO DA FOTO (Só muda se ele enviou arquivo novo)
            Part filePart = request.getPart("foto");
            if (filePart != null && filePart.getSize() > 0) {
                String fileName = filePart.getSubmittedFileName();
                String uploadPath = getServletContext().getRealPath("") + File.separator + "banco_imagens" + File.separator + "icones";
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) uploadDir.mkdirs();
                filePart.write(uploadPath + File.separator + fileName);

                logado.setFoto(fileName); // Atualiza no objeto
            }

            // 3. CAPTURA DOS DADOS DE CADASTRO (Não deixe nenhum de fora!)
            // Pegamos o que veio do formulário e colocamos no objeto 'logado'
            logado.setNome(request.getParameter("nome"));
            logado.setEmail(request.getParameter("email"));
            logado.setTelefone(request.getParameter("telefone"));
            logado.setCidadeUF(request.getParameter("cidadeUF"));
            logado.setDataNascimento(request.getParameter("dataNascimento"));

            // 4. CAPTURA DOS DADOS DE ATLETA
            PerfilAtletaModel pa = new PerfilAtletaModel();
            pa.setIdUsuario(logado.getIdUsuario());
            pa.setModalidade(request.getParameter("modalidade"));
            pa.setNivelExperiencia(request.getParameter("nivelExperiencia"));
            pa.setObjetivo(request.getParameter("objetivo"));
            pa.setDiasSemana(request.getParameter("diasSemana"));
            pa.setRestricaoFisica(request.getParameter("restricaoFisica"));

            // Tratamento de números
            String h = request.getParameter("altura");
            String p = request.getParameter("peso");
            if (h != null && !h.isEmpty()) pa.setAltura(Float.parseFloat(h));
            if (p != null && !p.isEmpty()) pa.setPeso(Float.parseFloat(p));

            // 5. SALVAMENTO NO BANCO (Ordem de tabelas)
            UsuarioDAO uDAO = new UsuarioDAO();
            uDAO.atualizarPerfil(logado); // Primeiro salva a base (usuario)

            PerfilAtletaDAO aDAO = new PerfilAtletaDAO();
            aDAO.salvarOuAtualizar(pa); // Depois salva o específico (perfil_atleta)

            // 6. O PULO DO GATO: Atualizar a Sessão
            // Buscamos o perfil COMPLETO (com o JOIN) para garantir que o objeto na sessão tenha TUDO
            UsuarioModel perfilAtualizado = uDAO.buscarPorId(logado.getIdUsuario());

            if (perfilAtualizado != null) {
                session.setAttribute("usuarioLogado", perfilAtualizado);
                // REDIRECIONA PARA O SERVLET DE PERFIL (Dashboard)
                response.sendRedirect(request.getContextPath() + "/perfilAtleta");
            } else {
                // Se der erro ao buscar, volta pro login por segurança
                response.sendRedirect(request.getContextPath() + "/login");
            }

        } catch (Exception e) {
            e.printStackTrace();
            // Se der erro, volta para a edição com um aviso
            response.sendRedirect(request.getContextPath() + "/editar-perfilAtleta?erro=1");
        }
    }
}
