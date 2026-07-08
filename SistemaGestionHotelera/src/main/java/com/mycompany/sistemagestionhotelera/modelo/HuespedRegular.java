package com.mycompany.sistemagestionhotelera.modelo;

public class HuespedRegular extends Huesped {
    public HuespedRegular(String nombre, String documento, String telefono, String email) {
        super(nombre, documento, telefono, email);
    }
    @Override
    public String obtenerBeneficio() { return "Tarifa estándar - Sin descuentos aplicados."; }
    @Override
    public String getTipoCliente() { return "Regular"; }
}