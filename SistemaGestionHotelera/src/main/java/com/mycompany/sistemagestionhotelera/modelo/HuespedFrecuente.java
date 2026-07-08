package com.mycompany.sistemagestionhotelera.modelo;

public class HuespedFrecuente extends Huesped {
    public HuespedFrecuente(String nombre, String documento, String telefono, String email) {
        super(nombre, documento, telefono, email);
    }
    @Override
    public String obtenerBeneficio() { return "Descuento del 15% por Fidelización + Acceso a Late Check-out."; }
    @Override
    public String getTipoCliente() { return "Frecuente"; }
}
