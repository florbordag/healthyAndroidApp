package com.example.finalhealty.model;

public class Participante {
    private int id;
    private int usuarioId;
    private  Usuario usuario;
    private int actividadId;
    private Actividad actividad;
    private String fechaUltMod;
    private int estado;

    public Participante(int id, int usuarioId, int actividadId, String fechaUltMod, int estado) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.actividadId = actividadId;
        this.fechaUltMod = fechaUltMod;
        this.estado = estado;
    }

    public Participante(int id, Usuario usuario, Actividad actividad, String fechaUltMod, int estado) {
        this.id = id;
        this.usuario = usuario;
        this.actividad = actividad;
        this.fechaUltMod = fechaUltMod;
        this.estado = estado;
    }

    public Participante() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

    public String getFechaUltMod() {
        return fechaUltMod;
    }

    public void setFechaUltMod(String fechaUltMod) {
        this.fechaUltMod = fechaUltMod;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Participante{" +
                "id=" + id +
                ", usuarioId=" + usuarioId +
                ", usuario=" + usuario +
                ", actividadId=" + actividadId +
                ", actividad=" + actividad +
                ", fechaUltMod='" + fechaUltMod + '\'' +
                ", estado=" + estado +
                '}';
    }
}
