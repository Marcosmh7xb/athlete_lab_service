package com.athletelab.equipe;

import com.athletelab.usuario.UsuarioModel;

import java.util.List;

public class EquipeModel {
    private int idEquipe;
    private String nome;
    private String descricao;
    private String esporte;
    private String dataCriacao;
    private int idTreinador;
    private List<UsuarioModel> atletas; // Para listar os membros
    private UsuarioModel treinador;

    public UsuarioModel getTreinador() {
        return treinador;
    }

    public void setTreinador(UsuarioModel treinador) {
        this.treinador = treinador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(int idEquipe) {
        this.idEquipe = idEquipe;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEsporte() {
        return esporte;
    }

    public void setEsporte(String esporte) {
        this.esporte = esporte;
    }

    public int getIdTreinador() {
        return idTreinador;
    }

    public void setIdTreinador(int idTreinador) {
        this.idTreinador = idTreinador;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public List<UsuarioModel> getAtletas() {
        return atletas;
    }

    public void setAtletas(List<UsuarioModel> atletas) {
        this.atletas = atletas;
    }
}