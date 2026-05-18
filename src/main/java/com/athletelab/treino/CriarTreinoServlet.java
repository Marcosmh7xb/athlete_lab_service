package com.athletelab.treino;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/criartreino")
public class CriarTreinoServlet extends HttpServlet {

    private TreinoDAO treinoDAO = new TreinoDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");

        // =========================
        // CRIAR TREINO
        // =========================
        if ("salvar".equals(acao)) {

            String nome = request.getParameter("nome");
            String categoria = request.getParameter("categoria");
            String status = request.getParameter("status");

            // validação básica
            if (nome == null || nome.isEmpty()) {
                throw new RuntimeException("Nome do treino não pode ser vazio");
            }

            TreinoModel treino = new TreinoModel();
            treino.setNome(nome);
            treino.setCategoria(categoria);
            treino.setStatus(status);

            // salva no banco
            int idGerado = TreinoDAO.inserir(treino);

            // redireciona direto para edição (onde adiciona exercícios)
            response.sendRedirect(
                    request.getContextPath() + "/treino/editar?idTreino=" + idGerado
            );
        }
    }
}