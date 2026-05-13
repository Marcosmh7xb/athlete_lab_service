package com.athletelab.usuario;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null ||
                session.getAttribute("usuarioLogado") == null) {

            response.sendRedirect(
                    request.getContextPath() + "/login"
            );

            return;
        }

        request.getRequestDispatcher("/WEB-INF/home.jsp")
                .forward(request, response);
    }
}

