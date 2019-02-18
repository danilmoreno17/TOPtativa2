package com.example.toptativa2.Models;

public class Juego {
    private int id;
    private String fecha_juego;
    private String nombre_juego;
    private float premio_mayor;
    private String estado_juego;
    private String tipo_juego;
    private float cuota;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha_juego() {
        return fecha_juego;
    }

    public void setFecha_juego(String fecha_juego) {
        this.fecha_juego = fecha_juego;
    }

    public String getNombre_juego() {
        return nombre_juego;
    }

    public void setNombre_juego(String nombre_juego) {
        this.nombre_juego = nombre_juego;
    }

    public float getPremio_mayor() {
        return premio_mayor;
    }

    public void setPremio_mayor(float premio_mayor) {
        this.premio_mayor = premio_mayor;
    }

    public String getEstado_juego() {
        return estado_juego;
    }

    public void setEstado_juego(String estado_juego) {
        this.estado_juego = estado_juego;
    }

    public String getTipo_juego() {
        return tipo_juego;
    }

    public void setTipo_juego(String tipo_juego) {
        this.tipo_juego = tipo_juego;
    }

    public float getCuota() {
        return cuota;
    }

    public void setCuota(float cuota) {
        this.cuota = cuota;
    }
}
