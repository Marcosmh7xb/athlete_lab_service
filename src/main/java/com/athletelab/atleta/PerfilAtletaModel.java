package com.athletelab.atleta;


public class PerfilAtletaModel {
    private int idUsuario;
    private int idPerfilAtleta;
    private String modalidade;
    private String nivelExperiencia;
    private String objetivo;
    private float altura;
    private float peso;
    private String diasSemana;
    private String ambiente;
    private String sexo;
    private String restricaoFisica;

    public PerfilAtletaModel() {}

    public int getIdPerfilAtleta() {
        return idPerfilAtleta;
    }
    public void setIdPerfilAtleta(int idPerfilAtleta) {
        this.idPerfilAtleta = idPerfilAtleta;
    }
    // Getters e Setters
    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }
    public String getModalidade() { return modalidade; }
    public void setModalidade(String modalidade) { this.modalidade = modalidade; }
    public String getNivelExperiencia() { return nivelExperiencia; }
    public void setNivelExperiencia(String nivelExperiencia) { this.nivelExperiencia = nivelExperiencia; }
    public String getObjetivo() { return objetivo; }
    public void setObjetivo(String objetivo) { this.objetivo = objetivo; }
    public float getAltura() { return altura; }
    public void setAltura(float altura) { this.altura = altura; }
    public float getPeso() { return peso; }
    public void setPeso(float peso) { this.peso = peso; }
    public String getDiasSemana() { return diasSemana; }
    public void setDiasSemana(String diasSemana) { this.diasSemana = diasSemana; }
    public String getAmbiente() { return ambiente; }
    public void setAmbiente(String ambiente) { this.ambiente = ambiente; }
    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }
    public String getRestricaoFisica() { return restricaoFisica; }
    public void setRestricaoFisica(String restricaoFisica) { this.restricaoFisica = restricaoFisica; }
}
