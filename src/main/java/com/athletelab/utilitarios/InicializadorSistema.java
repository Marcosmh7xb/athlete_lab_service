package com.athletelab.utilitarios;

import com.athletelab.usuario.UsuarioDAO;
import com.athletelab.usuario.UsuarioModel;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

@WebListener
public class InicializadorSistema implements ServletContextListener {

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            criptografarSenhas();
            System.out.println("Sistema inicializado.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void criptografarSenhas() {

        List<UsuarioModel> usuarios = usuarioDAO.listar();

        for (UsuarioModel usuario : usuarios) {
            if (!usuario.getSenha().startsWith("$2a$")) {
                String senhaHash = BCrypt.hashpw(usuario.getSenha(), BCrypt.gensalt());
                usuarioDAO.atualizarSenha(usuario.getIdUsuario(), senhaHash);
                System.out.println("Senha criptografada: " + usuario.getEmail());
            }
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Sistema finalizado.");
    }
}