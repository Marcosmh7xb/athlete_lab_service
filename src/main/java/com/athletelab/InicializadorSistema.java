package com.athletelab;

import com.athletelab.usuario.UsuarioDAO;
import com.athletelab.usuario.UsuarioModel;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.mindrot.jbcrypt.BCrypt;

import java.time.LocalDate;

@WebListener
public class InicializadorSistema implements ServletContextListener {

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        for (int tentativa = 1; tentativa <= 10; tentativa++) {
            try {
                System.out.println("Tentando inicializar usuários... tentativa " + tentativa);

                criarUsuarios();

                System.out.println("Usuários iniciais carregados com sucesso.");
                return;

            } catch (Exception e) {
                System.out.println("Banco ainda não disponível. Aguardando 5 segundos...");
                e.printStackTrace();

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ignored) {
                }
            }
        }

        System.out.println("Falha ao inicializar usuários após várias tentativas.");
    }

    private void criarUsuarios() {

        criarUsuario("Administrador", "admin@gmail.com", "admin123", "ADMIN");

        criarUsuario("Carlos Silva", "carlos@gmail.com", "123456", "TREINADOR");
        criarUsuario("Fernanda Rocha", "fernanda@gmail.com", "123456", "TREINADOR");

        criarUsuario("Lucas Almeida", "lucas@gmail.com", "123456", "ATLETA");
        criarUsuario("Mariana Souza", "mariana@gmail.com", "123456", "ATLETA");
        criarUsuario("Pedro Henrique", "pedro@gmail.com", "123456", "ATLETA");
        criarUsuario("Ana Clara", "ana@gmail.com", "123456", "ATLETA");
        criarUsuario("Gabriel Santos", "gabriel@gmail.com", "123456", "ATLETA");
        criarUsuario("Juliana Lima", "juliana@gmail.com", "123456", "ATLETA");
        criarUsuario("Rafael Costa", "rafael@gmail.com", "123456", "ATLETA");
        criarUsuario("Beatriz Martins", "beatriz@gmail.com", "123456", "ATLETA");
        criarUsuario("Thiago Oliveira", "thiago@gmail.com", "123456", "ATLETA");
    }

    private void criarUsuario(String nome, String email, String senha, String tipo) {

        try {
            if (usuarioDAO.buscarPorEmail(email) != null) {
                System.out.println("Usuário já existe: " + email);
                return;
            }

            UsuarioModel usuario = new UsuarioModel();

            String senhaCriptografada = BCrypt.hashpw(senha, BCrypt.gensalt());

            usuario.setNome(nome);
            usuario.setEmail(email);
            usuario.setSenha(senhaCriptografada);
            usuario.setTelefone("(77) 99999-9999");
            usuario.setCidadeUF("Guanambi - BA");
            usuario.setTipoUsuario(tipo);
            usuario.setDataNascimento("2000-01-01");
            usuario.setDataCriacao(LocalDate.now());
            usuario.setAtivo(true);

            UsuarioDAO.inserir(usuario);

            System.out.println("Usuário criado: " + email);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar usuário: " + email, e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Sistema finalizado.");
    }
}