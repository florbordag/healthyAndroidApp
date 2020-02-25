package com.example.finalhealty.model;

public class Empresa {
    private int id;
    private String nombre;
    private String cuit;
    private String mail;
    private String domicilio;
    private String telefono;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


    public Empresa(int id, String nombre, String cuit, String mail, String domicilio, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.cuit = cuit;
        this.mail = mail;
        this.domicilio = domicilio;
        this.telefono = telefono;
    }

    public Empresa() {
    }
}
