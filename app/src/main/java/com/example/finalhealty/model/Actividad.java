package com.example.finalhealty.model;

public class Actividad {
    private int idActividad;
    private String nombreActividad;
    private String horario;
    private String descripcionActividad;
    private String fechaUltModActividad;
    private int estadoActividad;
    private Usuario coordinadorActividad;

    public Actividad() {
    }

    public Actividad(int idActividad, String nombreActividad, String horario, String descripcionActividad, String fechaUltModActividad, int estadoActividad, Usuario coordinadorActividad) {
        this.idActividad = idActividad;
        this.nombreActividad = nombreActividad;
        this.horario = horario;
        this.descripcionActividad = descripcionActividad;
        this.fechaUltModActividad = fechaUltModActividad;
        this.estadoActividad = estadoActividad;
        this.coordinadorActividad = coordinadorActividad;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }

    public String getDescripcionActividad() {
        return descripcionActividad;
    }

    public void setDescripcionActividad(String descripcionActividad) {
        this.descripcionActividad = descripcionActividad;
    }

    public String getFechaUltModActividad() {
        return fechaUltModActividad;
    }

    public void setFechaUltModActividad(String fechaUltModActividad) {
        this.fechaUltModActividad = fechaUltModActividad;
    }

    public int getEstadoActividad() {
        return estadoActividad;
    }

    public void setEstadoActividad(int estadoActividad) {
        this.estadoActividad = estadoActividad;
    }

    public Usuario getCoordinadorActividad() {
        return coordinadorActividad;
    }

    public void setCoordinadorActividad(Usuario coordinadorActividad) {
        this.coordinadorActividad = coordinadorActividad;
    }
}
