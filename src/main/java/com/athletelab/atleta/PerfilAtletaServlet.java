package com.athletelab.atleta;

import com.athletelab.usuario.UsuarioDAO;
import com.athletelab.usuario.UsuarioModel;
import jakarta.servlet.ServletException;
import com.athletelab.equipe.EquipeDAO;
import com.athletelab.equipe.EquipeModel;
import java.util.List;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/perfil-atleta")
public class PerfilAtletaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UsuarioModel logado = (UsuarioModel) session.getAttribute("usuarioLogado");

        if (logado == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        try {
            // 1. DADOS DO PERFIL
            UsuarioModel perfilCompleto = new UsuarioDAO().buscarPorId(logado.getIdUsuario());
            request.setAttribute("perfil", perfilCompleto);

            // ==========================
            // 🔹 2. EQUIPES DO ATLETA
            // ==========================
            EquipeDAO equipeDAO = new EquipeDAO();
            List<EquipeModel> equipes = equipeDAO.buscarEquipesDoAtleta(logado.getIdUsuario());
            request.setAttribute("minhasEquipes", equipes);

            // 3. Manda para a tela de visualização
            request.getRequestDispatcher("/WEB-INF/perfilAtleta.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/login?erro=falha_interna");
        }
    }
}
