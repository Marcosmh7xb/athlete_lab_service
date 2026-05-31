package com.athletelab.equipe;

import com.athletelab.usuario.UsuarioDAO;
import com.athletelab.usuario.UsuarioModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/equipe")
public class EquipeServlet extends HttpServlet {

    private EquipeDAO dao = new EquipeDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String acao = request.getParameter("acao");
        UsuarioModel logado = (UsuarioModel) request.getSession().getAttribute("usuarioLogado");

        if (logado == null) { response.sendRedirect("login"); return; }

        if ("gerenciar".equals(acao)) {
            int idEquipe = Integer.parseInt(request.getParameter("id"));
            EquipeModel equipe = dao.buscarPorId(idEquipe);

            // Proteção: Só o dono pode gerenciar
            if (equipe.getIdTreinador() != logado.getIdUsuario()) {
                response.sendRedirect("perfil-treinador");
                return;
            }

            request.setAttribute("equipe", equipe);
            request.getRequestDispatcher("/WEB-INF/gerenciarEquipe.jsp").forward(request, response);
        }

        if ("ver".equals(acao)) {
            int idEquipe = Integer.parseInt(request.getParameter("id"));

            EquipeDAO dao = new EquipeDAO();
            EquipeModel equipe = dao.buscarPorId(idEquipe);

            if (equipe != null) {
                request.setAttribute("equipe", equipe);
                // O treinador agora está dentro de equipe.getTreinador()
                request.setAttribute("treinador", equipe.getTreinador());

                request.getRequestDispatcher("/WEB-INF/detalhesEquipeAtleta.jsp").forward(request, response);
            } else {
                response.sendRedirect("perfil-atleta?erro=equipe_nao_encontrada");
            }
        }
         acao = request.getParameter("acao");

        if ("verAtleta".equals(acao)) {
            int idAtleta = Integer.parseInt(request.getParameter("idAtleta"));

            UsuarioDAO usuarioDAO = new UsuarioDAO();
            // Busca a ficha do atleta no banco
            UsuarioModel atleta = usuarioDAO.buscarFichaCompletaAtleta(idAtleta);

            if (atleta != null) {
                request.setAttribute("atleta", atleta);
                // Despacha para a nova página de exibição
                request.getRequestDispatcher("/WEB-INF/detalhesAtleta.jsp").forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/home?erro=atletaNaoEncontrado");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String acao = request.getParameter("acao");
        UsuarioModel logado = (UsuarioModel) request.getSession().getAttribute("usuarioLogado");

        if ("criar".equals(acao)) {
            EquipeModel eq = new EquipeModel();
            eq.setNome(request.getParameter("nome"));
            eq.setDescricao(request.getParameter("descricao"));
            eq.setEsporte(request.getParameter("esporte"));
            eq.setIdTreinador(logado.getIdUsuario());
            dao.criar(eq);
            response.sendRedirect("perfil-treinador"); // Volta pro perfil do treinador
        }
        else if ("editar".equals(acao)) {
            EquipeModel eq = new EquipeModel();
            eq.setIdEquipe(Integer.parseInt(request.getParameter("idEquipe")));
            eq.setNome(request.getParameter("nome"));
            eq.setDescricao(request.getParameter("descricao"));
            eq.setEsporte(request.getParameter("esporte"));
            dao.atualizar(eq);
            response.sendRedirect("equipe?acao=gerenciar&id=" + eq.getIdEquipe());
        }
        else if ("convidar".equals(acao)) {
            int idEquipe = Integer.parseInt(request.getParameter("idEquipe"));
            String email = request.getParameter("email");
            boolean sucesso = dao.convidarAtleta(idEquipe, email);
            String msg = sucesso ? "Atleta adicionado com sucesso!" : "Erro: E-mail não encontrado ou atleta já está na equipe.";
            response.sendRedirect("equipe?acao=gerenciar&id=" + idEquipe + "&msg=" + msg);
        }
        else if ("remover".equals(acao)) {
            int idEquipe = Integer.parseInt(request.getParameter("idEquipe"));
            int idAtleta = Integer.parseInt(request.getParameter("idAtleta"));
            dao.removerAtleta(idEquipe, idAtleta);
            response.sendRedirect("equipe?acao=gerenciar&id=" + idEquipe);
        }
    }
}