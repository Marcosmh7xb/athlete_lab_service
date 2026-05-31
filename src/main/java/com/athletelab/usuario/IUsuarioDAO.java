package com.athletelab.usuario;

import java.util.List;

public interface IUsuarioDAO {

    void inserir(UsuarioModel u);

    List<UsuarioModel> listar();

    void atualizar(UsuarioModel u);

    void deletar(int idUsuario);

    UsuarioModel login(String email, String senha, String tipo_usuario);

    UsuarioModel buscarPorId(int id);

    void atualizarPerfil(UsuarioModel u);

    UsuarioModel buscarPorEmail(String email);

    UsuarioModel buscarFichaCompletaAtleta(int idAtleta);

    boolean atualizarSenha(int idUsuario, String novaSenha);
}