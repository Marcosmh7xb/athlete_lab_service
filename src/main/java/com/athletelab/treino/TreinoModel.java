package com.athletelab.treino;

import com.athletelab.exercicio.ExercicioModel;

import java.util.List;

public class TreinoModel {

    private int idTreino;
    private int idUsuario;
    private String nome;
    private String categoria;
    private String status;
    private String dataCriacao;
    private List<ExercicioModel> exercicios;

    public TreinoModel() {
    }

    public int getIdTreino() {
        return idTreino;
    }

    public void setIdTreino(int idTreino) {
        this.idTreino = idTreino;
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public List<ExercicioModel> getExercicios() {
        return exercicios;
    }

    public void setExercicios(List<ExercicioModel> exercicios) {
        this.exercicios = exercicios;
    }

}