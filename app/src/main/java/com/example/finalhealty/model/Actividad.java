package com.example.finalhealty.model;

public class Actividad {
    private int id;
    private String nombre;
    private String horario;
    private String descripcion;
    private String fechaUltMod;
    private String estado;
    private String coordinadorId;
    private Usuario Coordinador;

    public Actividad() {
    }

    public Actividad(int id, String nombre, String horario, String descripcion, String fechaUltMod, String estado, String coordinadorId) {
        this.id = id;
        this.nombre = nombre;
        this.horario = horario;
        this.descripcion = descripcion;
        this.fechaUltMod = fechaUltMod;
        this.estado = estado;
        this.coordinadorId = coordinadorId;
    }

    public Actividad(int id, String nombre, String horario, String descripcion, String fechaUltMod, String estado, Usuario coordinador) {
        this.id = id;
        this.nombre = nombre;
        this.horario = horario;
        this.descripcion = descripcion;
        this.fechaUltMod = fechaUltMod;
        this.estado = estado;
        Coordinador = coordinador;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return nombre;
    }

    public void setTitulo(String nombre) {
        this.nombre = nombre;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCoordinadorId() {
        return coordinadorId;
    }

    public void setCoordinadorId(String coordinadorId) {
        this.coordinadorId = coordinadorId;
    }

    public Usuario getCoordinador() {
        return Coordinador;
    }

    public void setCoordinador(Usuario coordinador) {
        Coordinador = coordinador;
    }

    @Override
    public String toString() {
        return "Actividad{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", horario='" + horario + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fechaUltMod='" + fechaUltMod + '\'' +
                ", estado='" + estado + '\'' +
                ", CoordinadorId='" + coordinadorId + '\'' +
                ", Coordinador=" + Coordinador +
                '}';
    }
}
