package com.mycompany.sistemagestionhotelera.modelo;

public abstract class Huesped {

    private int idHuesped;
    private String nombreCompleto;
    private String documentoIdentidad;
    private String telefono;
    private String email;

    public Huesped(String nombreCompleto, String documentoIdentidad, String telefono, String email) {
        this.nombreCompleto = nombreCompleto;
        this.documentoIdentidad = documentoIdentidad;
        this.telefono = telefono;
        this.email = email;
    }

    // Getters y Setters Estandar
    public int getIdHuesped() {
        return idHuesped;
    }

    public void setIdHuesped(int idHuesped) {
        this.idHuesped = idHuesped;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public void setDocumentoIdentidad(String documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // METODOS ABSTRACTOS PARA JUSTIFICAR EL PATRÓN GOF
    public abstract String obtenerBeneficio();

    public abstract String getTipoCliente();
}
