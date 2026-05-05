package com.athletelab.treino;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/treino")
public class TreinoServlet extends  HttpServlet{

    @Override
    public void doPost( HttpServletRequest requisicao, HttpServletResponse resposta) throws ServletException, IOException{
        String nome = requisicao.getParameter("nome");
        String status = requisicao.getParameter("status");
     }
}

