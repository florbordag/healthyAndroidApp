package com.example.finalhealty.model;

public class Predio {
    private int idPredio;
    private String calle;
    private int numero;
    private int cp;
    private String localidad;
    private String nombre;


    public int getIdPredio() {
        return idPredio;
    }

    public void setIdPredio(int idPredio) {
        this.idPredio = idPredio;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getCp() {
        return cp;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public Predio(int idPredio, String calle, int numero, int cp, String localidad, String nombre) {
        this.idPredio = idPredio;
        this.calle = calle;
        this.numero = numero;
        this.cp = cp;
        this.localidad = localidad;
        this.nombre = nombre;
    }

    public Predio(int idPredio) {
        this.idPredio = idPredio;
    }

    public Predio() {
    }
}
