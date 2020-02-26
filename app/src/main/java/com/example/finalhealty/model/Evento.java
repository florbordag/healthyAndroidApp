package com.example.finalhealty.model;

public class Evento {
    private int id;
    private int actividadId;
    private Actividad actividad;
    private String fechaHora;
    private int estado;
    private String titulo;
    private String descripcion;
    private String fechaUltMod;

    public Evento() {
    }

    public Evento(int id, int actividadId, String fechaHora, int estado, String titulo, String descripcion, String fechaUltMod) {
        this.id = id;
        this.actividadId = actividadId;
        this.fechaHora = fechaHora;
        this.estado = estado;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaUltMod = fechaUltMod;
    }

    public Evento(int id, Actividad actividad, String fechaHora, int estado, String titulo, String descripcion, String fechaUltMod) {
        this.id = id;
        this.actividad = actividad;
        this.fechaHora = fechaHora;
        this.estado = estado;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaUltMod = fechaUltMod;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getActividadId() {
        return actividadId;
    }

    public void setActividadId(int actividadId) {
        this.actividadId = actividadId;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
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

    public String getFechaUltMod() {
        return fechaUltMod;
    }

    public void setFechaUltMod(String fechaUltMod) {
        this.fechaUltMod = fechaUltMod;
    }

    @Override
    public String toString() {
        return "Evento{" +
                "id=" + id +
                ", actividadId=" + actividadId +
                ", actividad=" + actividad +
                ", fechaHora='" + fechaHora + '\'' +
                ", estado=" + estado +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fechaUltMod='" + fechaUltMod + '\'' +
                '}';
    }
}
