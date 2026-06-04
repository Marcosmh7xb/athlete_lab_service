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

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private final PerfilAtletaDAO atletaDAO = new PerfilAtletaDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        UsuarioModel logado = (UsuarioModel) session.getAttribute("usuarioLogado");

        if (logado == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        UsuarioModel perfilCompleto = usuarioDAO.buscarPorId(logado.getIdUsuario());
        request.setAttribute("perfil", perfilCompleto);
        request.getRequestDispatcher("/WEB-INF/editarPerfilAtleta.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }

        UsuarioModel logado = (UsuarioModel) session.getAttribute("usuarioLogado");

        if (logado == null) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }

        try {
            Part filePart = request.getPart("foto");

            if (filePart != null && filePart.getSize() > 0) {

                String fileName = filePart.getSubmittedFileName();
                String uploadPath = getServletContext().getRealPath("") + File.separator + "banco_imagens" + File.separator + "icones";
                File pasta = new File(uploadPath);
                if (!pasta.exists()) {
                    pasta.mkdirs();
                }

                filePart.write(uploadPath + File.separator + fileName);
                logado.setFoto(fileName);
            }

            String nome = request.getParameter("nome");

            if (nome != null && !nome.isEmpty()) {
                logado.setNome(nome);
            }
            String telefone = request.getParameter("telefone");

            if (telefone != null) {
                logado.setTelefone(telefone);
            }
            String cidadeUF = request.getParameter("cidadeUF");

            if (cidadeUF != null) {
                logado.setCidadeUF(cidadeUF);
            }
            String dataNascimento = request.getParameter("dataNascimento");

            if (dataNascimento != null) {
                logado.setDataNascimento(dataNascimento);
            }

            PerfilAtletaModel pa = new PerfilAtletaModel();

            pa.setIdUsuario(logado.getIdUsuario());
            pa.setModalidade(request.getParameter("modalidade"));
            pa.setNivelExperiencia(request.getParameter("nivelExperiencia"));
            pa.setObjetivo(request.getParameter("objetivo"));
            pa.setDiasSemana(request.getParameter("diasSemana"));
            pa.setAmbiente(request.getParameter("ambiente"));
            pa.setSexo(request.getParameter("sexo"));
            pa.setRestricaoFisica(request.getParameter("restricaoFisica"));

            String altura = request.getParameter("altura");
            if (altura != null && !altura.isEmpty()) {
                pa.setAltura(Float.parseFloat(altura));
            }


            String peso = request.getParameter("peso");
            if (peso != null && !peso.isEmpty()) {
                pa.setPeso(
                        Float.parseFloat(peso)
                );
            }

            usuarioDAO.atualizarPerfil(logado);
            atletaDAO.salvarOuAtualizar(pa);

            UsuarioModel perfilAtualizado = usuarioDAO.buscarPorId(logado.getIdUsuario());
            session.setAttribute("usuarioLogado", perfilAtualizado);
            response.sendRedirect(request.getContextPath() + "/perfil-atleta");

        } catch (Exception erro) {
            erro.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/editar-perfil-atleta?erro=1");
        }
    }
}