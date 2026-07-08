package com.mycompany.sistemagestionhotelera.modelo;

public class Reserva {

    private int idReserva;
    private int idHuesped;
    private int idHabitacion;
    private String fechaIngreso;
    private String fechaSalida;
    private double costoTotal;
    private String estadoReserva;
    private String canalReserva;

    public Reserva() {
    }

    public Reserva(int idHuesped, int idHabitacion, String fechaIngreso, String fechaSalida, double costoTotal, String canalReserva) {
        this.idHuesped = idHuesped;
        this.idHabitacion = idHabitacion;
        this.fechaIngreso = fechaIngreso;
        this.fechaSalida = fechaSalida;
        this.costoTotal = costoTotal;
        this.canalReserva = canalReserva;
    }

    // Getters y Setters
    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public int getIdHuesped() {
        return idHuesped;
    }

    public void setIdHuesped(int idHuesped) {
        this.idHuesped = idHuesped;
    }

    public int getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(int idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(double costoTotal) {
        this.costoTotal = costoTotal;
    }

    public String getEstadoReserva() {
        return estadoReserva;
    }

    public void setEstadoReserva(String estadoReserva) {
        this.estadoReserva = estadoReserva;
    }

    public String getCanalReserva() {
        return canalReserva;
    }

    public void setCanalReserva(String canalReserva) {
        this.canalReserva = canalReserva;
    }
}
