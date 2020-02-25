package com.example.finalhealty.model;

public class Usuario {
    private int id;
    private int empresa;
    private String mail;
    private String nombre;
    private String apellido;
    private String dni;
    private String password;
    private String rol;
    private int adminUltModId;
    private String fechaUltMod;
    private int conducta;
    private int estadoCuenta;
    private String fecNac;
    private int fumador;
    private String fotoPerfil;
    private String fumaUltMod;

    public Usuario(int id) {
        this.id = id;
    }

    public Usuario() {
    }

    public Usuario(int id, int empresa, String mail, String nombre, String apellido, String dni, String password, String rol, int adminUltModId, String fechaUltMod, int conducta, int estadoCuenta, String fecNac, String fotoPerfil, int fumador, String fumaUltMod) {
        this.id = id;
        this.empresa = empresa;
        this.mail = mail;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.password = password;
        this.rol = rol;
        this.adminUltModId = adminUltModId;
        this.fechaUltMod = fechaUltMod;
        this.conducta = conducta;
        this.estadoCuenta = estadoCuenta;
        this.fecNac = fecNac;
        this.fotoPerfil = fotoPerfil;
        this.fumador = fumador;
        this.fumaUltMod = fumaUltMod;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmpresa() {
        return empresa;
    }

    public void setEmpresa(int empresa) {
        this.empresa = empresa;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public int getAdminUltModId() {
        return adminUltModId;
    }

    public void setAdminUltModId(int adminUltModId) {
        this.adminUltModId = adminUltModId;
    }

    public String getFechaUltMod() {
        return fechaUltMod;
    }

    public void setFechaUltMod(String fechaUltMod) {
        this.fechaUltMod = fechaUltMod;
    }

    public int getConducta() {
        return conducta;
    }

    public void setConducta(int conducta) {
        this.conducta = conducta;
    }

    public int getEstadoCuenta() {
        return estadoCuenta;
    }

    public void setEstadoCuenta(int estadoCuenta) {
        this.estadoCuenta = estadoCuenta;
    }

    public String getFecNac() {
        return fecNac;
    }

    public void setFecNac(String fecNac) {
        this.fecNac = fecNac;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public int getFumador() {
        return fumador;
    }

    public void setFumador(int fumador) {
        this.fumador = fumador;
    }

    public String getFumaUltMod() {
        return fumaUltMod;
    }

    public void setFumaUltMod(String fumaUltMod) {
        this.fumaUltMod = fumaUltMod;
    }


}
