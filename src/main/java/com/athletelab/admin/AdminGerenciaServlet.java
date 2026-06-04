package com.athletelab.admin;

import java.io.IOException;
import java.util.List;

import com.athletelab.treino.TreinoDAO;
import com.athletelab.treino.TreinoModel;
import com.athletelab.usuario.UsuarioModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/admin/treinos")
public class AdminGerenciaServlet extends HttpServlet {

    private TreinoDAO treinoDAO = new TreinoDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        HttpSession session = req.getSession(false);

        if (session == null) {
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }


        UsuarioModel admin = (UsuarioModel) session.getAttribute("usuarioLogado");

        if (admin == null || !"ADMIN".equals(admin.getTipoUsuario())) {
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        List<TreinoModel> treinos = treinoDAO.listarTodos();
        req.setAttribute("treinos", treinos);

        req.getRequestDispatcher("/WEB-INF/admin_treino.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        if (session == null) {
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        UsuarioModel admin = (UsuarioModel) session.getAttribute("usuarioLogado");

        if (admin == null || !"ADMIN".equals(admin.getTipoUsuario())) {
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        String acao = req.getParameter("acao");

        if ("criar".equals(acao)) {

            TreinoModel treino = new TreinoModel();
            treino.setNome(req.getParameter("nome"));
            treino.setCategoria(req.getParameter("categoria"));
            treino.setStatus(req.getParameter("status"));
            treino.setIdUsuario(admin.getIdUsuario());

            int idTreino = treinoDAO.inserir(treino);


            resp.sendRedirect(req.getContextPath() + "/admin/treinos");
            return;
        }

        if ("deletar".equals(acao)) {

            int idTreino = Integer.parseInt(req.getParameter("idTreino"));
            treinoDAO.deletar(idTreino);
            resp.sendRedirect(req.getContextPath() + "/admin/treinos"
            );
        }
    }
}