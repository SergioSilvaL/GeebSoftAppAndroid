package com.tecnologiasintech.geebsoftapp.MaestroPerfil.Recycler.DataModel;

/**
 * Created by Sergio on 1/17/2017.
 */

public class MaestroPregunta {

    MaestroPregunta(){}

    private String asunto;
    private String cuerpo;
    private int numero_respuestas;

    public MaestroPregunta(String asunto, String cuerpo, int numero_respuestas) {
        this.asunto = asunto;
        this.cuerpo = cuerpo;
        this.numero_respuestas = numero_respuestas;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public int getNumero_respuestas() {
        return numero_respuestas;
    }

    public void setNumero_respuestas(int numero_respuestas) {
        this.numero_respuestas = numero_respuestas;
    }
}

