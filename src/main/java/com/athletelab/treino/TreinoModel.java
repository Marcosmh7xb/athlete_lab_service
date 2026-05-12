package com.athletelab.treino;

import java.time.LocalDate;

public class TreinoModel {

    private int idTreino;
    private String nome;
    private LocalDate dataCriacao;
    private float duracao;
    private boolean status;

    public TreinoModel(){}

    public TreinoModel(int idTreino, String nome, LocalDate dataCriacao, float duracao, boolean status){
        this.idTreino = idTreino;
        this.nome = nome;
        this.dataCriacao = dataCriacao;
        this.duracao = duracao;
        this.status = status;
    }

    public int getIdTreino() {
        return idTreino;
    }
    public void setIdTreino(int idTreino) {
        this.idTreino = idTreino;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }
    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public float getDuracao() {
        return duracao;
    }
    public void setDuracao(float duracao) {
        this.duracao = duracao;
    }

    public boolean getStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
}

