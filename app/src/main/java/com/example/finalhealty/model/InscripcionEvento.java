package com.example.finalhealty.model;

public class InscripcionEvento {
    private int id;
    private int usuarioId;
    private Usuario usuario;
    private int eventoId;
    private Evento evento;
    private String fechaUltMod;
    private int estado;

    public InscripcionEvento() {
    }

    public InscripcionEvento(int id, int usuarioId, int eventoId, String fechaUltMod, int estado) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.eventoId = eventoId;
        this.fechaUltMod = fechaUltMod;
        this.estado = estado;
    }

    public InscripcionEvento(int id, Usuario usuario, Evento evento, String fechaUltMod, int estado) {
        this.id = id;
        this.usuario = usuario;
        this.evento = evento;
        this.fechaUltMod = fechaUltMod;
        this.estado = estado;
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

    public int getEventoId() {
        return eventoId;
    }

    public void setEventoId(int eventoId) {
        this.eventoId = eventoId;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
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
        return "InscripcionEvento{" +
                "id=" + id +
                ", usuarioId=" + usuarioId +
                ", usuario=" + usuario +
                ", eventoId=" + eventoId +
                ", evento=" + evento +
                ", fechaUltMod='" + fechaUltMod + '\'' +
                ", estado=" + estado +
                '}';
    }
}
