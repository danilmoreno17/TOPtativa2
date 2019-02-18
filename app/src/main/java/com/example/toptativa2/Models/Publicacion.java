package com.example.toptativa2.Models;

public class Publicacion {
    private int id;
    private int id_usuario;
    private String estado;
    private String fecha_publicacion;

    private String nombre_juego;
    private String premio_mayor;
    private float premio_dinero;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFecha_publicacion() {
        return fecha_publicacion;
    }

    public void setFecha_publicacion(String fecha_publicacion) {
        this.fecha_publicacion = fecha_publicacion;
    }

    public String getNombre_juego() {
        return nombre_juego;
    }

    public void setNombre_juego(String nombre_juego) {
        this.nombre_juego = nombre_juego;
    }

    public String getPremio_mayor() {
        return premio_mayor;
    }

    public void setPremio_mayor(String premio_mayor) {
        this.premio_mayor = premio_mayor;
    }

    public float getPremio_dinero() {
        return premio_dinero;
    }

    public void setPremio_dinero(float premio_dinero) {
        this.premio_dinero = premio_dinero;
    }
}
