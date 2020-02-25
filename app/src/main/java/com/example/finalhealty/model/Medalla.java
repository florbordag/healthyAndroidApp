package com.example.finalhealty.model;

public class Medalla {
    private int idMedalla;
    private String nombreMedalla;
    private String descripcion;
    private String color;
    private Usuario usuario;

    public int getIdMedalla() {
        return idMedalla;
    }

    public void setIdMedalla(int idMedalla) {
        this.idMedalla = idMedalla;
    }

    public String getNombreMedalla() {
        return nombreMedalla;
    }

    public void setNombreMedalla(String nombreMedalla) {
        this.nombreMedalla = nombreMedalla;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


    public Medalla() {
    }

    public Medalla(int idMedalla, String nombreMedalla, String descripcion, String color, Usuario usuario) {
        this.idMedalla = idMedalla;
        this.nombreMedalla = nombreMedalla;
        this.descripcion = descripcion;
        this.color = color;
        this.usuario = usuario;
    }
}

