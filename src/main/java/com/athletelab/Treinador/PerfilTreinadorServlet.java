package com.athletelab.Treinador;

import com.athletelab.treino.TreinoDAO;
import com.athletelab.treino.TreinoModel;
import com.athletelab.usuario.UsuarioDAO;
import com.athletelab.usuario.UsuarioModel;
import com.athletelab.equipe.EquipeDAO;
import com.athletelab.equipe.EquipeModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/perfil-treinador")
public class PerfilTreinadorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }

        UsuarioModel usuarioSessao =
                (UsuarioModel) session.getAttribute("usuarioLogado");

        if (usuarioSessao == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        try {

            UsuarioDAO dao = new UsuarioDAO();

            UsuarioModel perfilCompleto =
                    dao.buscarPorId(usuarioSessao.getIdUsuario());

            if (perfilCompleto == null) {
                response.sendRedirect(request.getContextPath() + "/login?erro=usuario_inexistente");
                return;
            }

            // 🔹 TREINOS (Mantido o que você já tinha)
            TreinoDAO treinoDAO = new TreinoDAO();
            List<TreinoModel> treinos =
                    treinoDAO.listarPorUsuario(usuarioSessao.getIdUsuario());

            // ==========================================
            // 🔹 EQUIPES (Nova parte adicionada)
            // ==========================================
            EquipeDAO equipeDAO = new EquipeDAO();
            List<EquipeModel> equipes =
                    equipeDAO.buscarEquipesDoTreinador(usuarioSessao.getIdUsuario());

            // Atributos para JSP
            request.setAttribute("perfil", perfilCompleto);
            request.setAttribute("treinos", treinos);
            request.setAttribute("minhasEquipes", equipes); // 🔹 Enviando as equipes pro JSP

            // forward único
            request.getRequestDispatcher("/WEB-INF/perfilTreinador.jsp")
                    .forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();

            response.sendRedirect(request.getContextPath() + "/login?erro=falha_interna");
        }
    }
}