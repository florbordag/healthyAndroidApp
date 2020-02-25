package com.example.finalhealty.model;

public class Participante {
    private Usuario usuario;
    private Actividad actividad;
    private String fechaUltModParticipante;
    private int estadoParticipante;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public String getFechaUltModParticipante() {
        return fechaUltModParticipante;
    }

    public void setFechaUltModParticipante(String fechaUltModParticipante) {
        this.fechaUltModParticipante = fechaUltModParticipante;
    }

    public int getEstadoParticipante() {
        return estadoParticipante;
    }

    public void setEstadoParticipante(int estadoParticipante) {
        this.estadoParticipante = estadoParticipante;
    }

    public Participante(Usuario usuario, Actividad actividad, String fechaUltModParticipante, int estadoParticipante) {
        this.usuario = usuario;
        this.actividad = actividad;
        this.fechaUltModParticipante = fechaUltModParticipante;
        this.estadoParticipante = estadoParticipante;
    }

    public Participante() {
    }

    public Participante(Usuario usuario, Actividad actividad) {
        this.usuario = usuario;
        this.actividad = actividad;
    }
}
