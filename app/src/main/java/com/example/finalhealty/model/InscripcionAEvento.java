package com.example.finalhealty.model;

public class InscripcionAEvento {
    private int idInscripcion;
    private Evento evento;
    private String fechaUltModInscripcion;
    private int equipo;
    private int estadoInscripcion;
    private int resultado;

    public int getIdInscripcion() {
        return idInscripcion;
    }

    public void setIdInscripcion(int idInscripcion) {
        this.idInscripcion = idInscripcion;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public String getFechaUltModInscripcion() {
        return fechaUltModInscripcion;
    }

    public void setFechaUltModInscripcion(String fechaUltModInscripcion) {
        this.fechaUltModInscripcion = fechaUltModInscripcion;
    }

    public int getEquipo() {
        return equipo;
    }

    public void setEquipo(int equipo) {
        this.equipo = equipo;
    }

    public int getEstadoInscripcion() {
        return estadoInscripcion;
    }

    public void setEstadoInscripcion(int estadoInscripcion) {
        this.estadoInscripcion = estadoInscripcion;
    }

    public int getResultado() {
        return resultado;
    }

    public void setResultado(int resultado) {
        this.resultado = resultado;
    }


    public InscripcionAEvento(int idInscripcion, Evento evento, String fechaUltModInscripcion, int equipo, int estadoInscripcion, int resultado) {
        this.idInscripcion = idInscripcion;
        this.evento = evento;
        this.fechaUltModInscripcion = fechaUltModInscripcion;
        this.equipo = equipo;
        this.estadoInscripcion = estadoInscripcion;
        this.resultado = resultado;
    }

    public InscripcionAEvento() {
    }
}
