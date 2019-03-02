package com.example.toptativa2.Models;

public class NumeroJuego {

    private int id;
    private int id_juego;
    private int id_user;
    private String numero_juego;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_juego() {
        return id_juego;
    }

    public void setId_juego(int id_juego) {
        this.id_juego = id_juego;
    }

    public String getNumero_juego() {
        return numero_juego;
    }

    public void setNumero_juego(String numero_juego) {
        this.numero_juego = numero_juego;
    }
}
