package com.mycompany.sistemagestionhotelera.modelo;

public class Habitacion {

    private int idHabitacion;
    private String numero;
    private int idTipo;
    private String estado; // Disponible, Ocupada, Limpieza, Mantenimiento

    public Habitacion() {
    }

    public Habitacion(int idHabitacion, String numero, int idTipo, String estado) {
        this.idHabitacion = idHabitacion;
        this.numero = numero;
        this.idTipo = idTipo;
        this.estado = estado;
    }

    // Getters y Setters
    public int getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(int idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
