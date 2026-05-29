package com.athletelab.Treinador;

public class PerfilTreinadorModel {
    private int idPerfilTreinador;
    private int idUsuario;
    private String modalidade;
    private String nivelExperiencia;
    private String objetivo;
    private String ambiente;
    private String sexo;
    private String restricaoFisica;

    public PerfilTreinadorModel() {}

    // Getters e Setters

    public int getIdPerfilTreinador() {
        return idPerfilTreinador;
    }
    public void setIdPerfilTreinador(int idPerfilTreinador){this.idPerfilTreinador = idPerfilTreinador;}

    public int getIdUsuario(){
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getModalidade() { return modalidade; }
    public void setModalidade(String modalidade) { this.modalidade = modalidade; }

    public String getNivelExperiencia() { return nivelExperiencia; }
    public void setNivelExperiencia(String nivelExperiencia) { this.nivelExperiencia = nivelExperiencia; }

    public String getObjetivo() { return objetivo; }
    public void setObjetivo(String objetivo) { this.objetivo = objetivo; }

    public String getAmbiente() { return ambiente; }
    public void setAmbiente(String ambiente) { this.ambiente = ambiente; }

    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }

    public String getRestricaoFisica() { return restricaoFisica; }
    public void setRestricaoFisica(String restricaoFisica) { this.restricaoFisica = restricaoFisica; }
}
