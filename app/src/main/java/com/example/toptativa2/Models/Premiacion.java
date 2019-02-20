package com.example.toptativa2.Models;

public class Premiacion {

    private int id;
    private int id_usuario;
    private int id_publicacion;
    private String num_juego;
    private int estado_numero;


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

    public int getId_publicacion() {
        return id_publicacion;
    }

    public void setId_publicacion(int id_publicacion) {
        this.id_publicacion = id_publicacion;
    }

    public String getNum_juego() {
        return num_juego;
    }

    public void setNum_juego(String num_juego) {
        this.num_juego = num_juego;
    }

    public int getEstado_numero() {
        return estado_numero;
    }

    public void setEstado_numero(int estado_numero) {
        this.estado_numero = estado_numero;
    }
}
