package com.example.finalhealty.model;

public class Evento {
    private int idEvento;
    private Actividad actividad;
    private Predio predio;
    private String fechaHora;
    private int estadoEvento;
    private String titulo;
    private String descripcion;

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public Predio getPredio() {
        return predio;
    }

    public void setPredio(Predio predio) {
        this.predio = predio;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    public int getEstadoEvento() {
        return estadoEvento;
    }

    public void setEstadoEvento(int estadoEvento) {
        this.estadoEvento = estadoEvento;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Evento(int idEvento, Actividad actividad, Predio predio, String fechaHora, int estadoEvento, String titulo, String descripcion) {
        this.idEvento = idEvento;
        this.actividad = actividad;
        this.predio = predio;
        this.fechaHora = fechaHora;
        this.estadoEvento = estadoEvento;
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    public Evento() {
    }

    public String toString(){
        String user=  getIdEvento() + ","+getActividad().getNombreActividad()+","+getPredio().getNombre()+","+getFechaHora()+","+getEstadoEvento()+","+getTitulo()+","+getDescripcion();
        return user;
    }

}
