package com.example.toptativa2.Models;

public class DetallesJuego {
    private int id;
    private int id_juego;
    private float premio_efectivo;
    private String premio_nombre;
    private String num_ganador;

    private String nombre_juego_principal;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_juego() {
        return id_juego;
    }

    public void setId_juego(int id_juego) {
        this.id_juego = id_juego;
    }

    public float getPremio_efectivo() {
        return premio_efectivo;
    }

    public void setPremio_efectivo(float premio_efectivo) {
        this.premio_efectivo = premio_efectivo;
    }

    public String getPremio_nombre() {
        return premio_nombre;
    }

    public void setPremio_nombre(String premio_nombre) {
        this.premio_nombre = premio_nombre;
    }

    public String getNum_ganador() {
        return num_ganador;
    }

    public void setNum_ganador(String num_ganador) {
        this.num_ganador = num_ganador;
    }

    public String getNombre_juego_principal() {
        return nombre_juego_principal;
    }

    public void setNombre_juego_principal(String nombre_juego_principal) {
        this.nombre_juego_principal = nombre_juego_principal;
    }
}
