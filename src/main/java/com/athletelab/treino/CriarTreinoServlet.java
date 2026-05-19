package com.athletelab.treino;

import com.athletelab.usuario.UsuarioModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/criartreino")
public class CriarTreinoServlet extends HttpServlet {

    private TreinoDAO treinoDAO = new TreinoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/criar_treino.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");

        if ("salvar".equals(acao)) {

            // 🔥 PEGA USUÁRIO DA SESSÃO
            HttpSession session = request.getSession();

            UsuarioModel usuario =
                    (UsuarioModel) session.getAttribute("usuarioLogado");

            // 🔥 VALIDA LOGIN
            if (usuario == null) {
                throw new RuntimeException("Usuário não está logado");
            }

            String nome = request.getParameter("nome");
            String categoria = request.getParameter("categoria");
            String status = request.getParameter("status");

            // 🔥 CRIA TREINO
            TreinoModel treino = new TreinoModel();

            treino.setNome(nome);
            treino.setCategoria(categoria);
            treino.setStatus(status);

            // 🔥 DEFINE DONO DO TREINO
            treino.setIdUsuario(usuario.getIdUsuario());

            // 🔥 SALVA
            int idGerado = treinoDAO.inserir(treino);

            // 🔥 REDIRECIONA PARA EDIÇÃO
            response.sendRedirect(
                    request.getContextPath()
                            + "/treino/editar?idTreino="
                            + idGerado
            );
        }
    }
}