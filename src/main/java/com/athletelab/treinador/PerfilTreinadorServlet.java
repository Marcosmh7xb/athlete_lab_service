
package com.athletelab.treinador;

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }

        UsuarioModel usuarioSessao = (UsuarioModel) session.getAttribute("usuarioLogado");

        if (usuarioSessao == null) {

            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }

        try {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            UsuarioModel perfilCompleto = usuarioDAO.buscarPorId(usuarioSessao.getIdUsuario());

            if (perfilCompleto == null) {
                response.sendRedirect(request.getContextPath() + "/login?erro=usuario_inexistente");
                return;
            }

            TreinoDAO treinoDAO = new TreinoDAO();
            List<TreinoModel> treinos = treinoDAO.listarParaTreinador(usuarioSessao.getIdUsuario());

            EquipeDAO equipeDAO = new EquipeDAO();
            List<EquipeModel> equipes = equipeDAO.buscarEquipesDoTreinador(usuarioSessao.getIdUsuario());

            request.setAttribute("perfil", perfilCompleto);
            request.setAttribute("treinos", treinos);
            request.setAttribute("minhasEquipes", equipes);
            request.getRequestDispatcher("/WEB-INF/perfilTreinador.jsp").forward(request, response);

        } catch (Exception erro) {
            erro.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/login?erro=falha_interna");
        }
    }
}
