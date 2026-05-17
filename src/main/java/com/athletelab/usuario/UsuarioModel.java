package com.athletelab.usuario;

import com.athletelab.Treinador.PerfilTreinadorModel;
import com.athletelab.atleta.PerfilAtletaModel;

import java.time.LocalDate;

public class UsuarioModel {

    private int idUsuario;
    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private String dataNascimento;
    private String cidadeUF;
    private LocalDate dataCriacao;
    private boolean ativo;
    private String tipoUsuario;
    private String foto;
    private PerfilTreinadorModel perfilTreinador;
    private PerfilAtletaModel perfilAtleta;

    public UsuarioModel(String nome, String email, String senha, String telefone, String dataNascimento, String cidadeUF, String tipoUsuario) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.cidadeUF = cidadeUF;
        this.tipoUsuario = tipoUsuario;
        this.dataCriacao = LocalDate.now();
        this.ativo = true;
    }

    public  UsuarioModel(){

    }
    public PerfilAtletaModel getPerfilAtleta() {
        return perfilAtleta;
    }
    public void setPerfilAtleta(PerfilAtletaModel perfilAtleta) {
        this.perfilAtleta = perfilAtleta;
    }
    public PerfilTreinadorModel getPerfilTreinador() {
        return perfilTreinador;
    }

    public void setPerfilTreinador(PerfilTreinadorModel perfilTreinador) {
        this.perfilTreinador = perfilTreinador;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCidadeUF() {
        return cidadeUF;
    }

    public void setCidadeUF(String cidadeUF) {
        this.cidadeUF = cidadeUF;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getFoto() { return foto; }

    public void setFoto(String foto) { this.foto = foto; }

    private PerfilTreinadorModel perfilTecnico; // Novo campo

    public PerfilTreinadorModel getPerfilTecnico() { return perfilTecnico; }
    public void setPerfilTecnico(PerfilTreinadorModel perfilTecnico) { this.perfilTecnico = perfilTecnico; }

    // Método para checar se o bloqueio deve aparecer
    public boolean hasPerfilCompleto() {
        return perfilTecnico != null && perfilTecnico.getModalidade() != null;
    }
}
