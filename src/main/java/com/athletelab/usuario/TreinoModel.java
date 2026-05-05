package com.athletelab.usuario;

import java.time.LocalDate;

public class TreinoModel {

    private int idTreino;
    private String nome;
    private LocalDate dataCriacao;
    private float duracao;
    private String status;

    public TreinoModel(){}

    public TreinoModel(int idTreino, String nome, LocalDate dataCriacao, float duracao, String status){
        this.idTreino = idTreino;
        this.nome = nome;
        this.dataCriacao = dataCriacao;
        this.duracao = duracao;
        this.status = status;
    }
}

