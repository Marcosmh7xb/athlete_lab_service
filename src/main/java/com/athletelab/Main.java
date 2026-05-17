package com.athletelab;

import com.athletelab.usuario.UsuarioDAO;
import com.athletelab.usuario.UsuarioModel;

public class Main {

    public static void main(String[] args) {

        UsuarioModel admin = new UsuarioModel();

        admin.setNome("Administrador");
        admin.setEmail("admin@gmail");
        admin.setSenha("admin");
        admin.setTipoUsuario("ADMIN");

        UsuarioDAO.inserir(admin);

        System.out.println("Admin criado!");
    }
}