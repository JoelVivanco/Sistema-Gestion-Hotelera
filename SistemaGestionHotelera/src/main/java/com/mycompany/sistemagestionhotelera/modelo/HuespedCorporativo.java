package com.mycompany.sistemagestionhotelera.modelo;

public class HuespedCorporativo extends Huesped {
    public HuespedCorporativo(String nombre, String documento, String telefono, String email) {
        super(nombre, documento, telefono, email);
    }
    @Override
    public String obtenerBeneficio() { return "Convenio Empresa: Crédito a 30 días + Tarifas Netas Negociadas."; }
    @Override
    public String getTipoCliente() { return "Corporativo"; }
}
