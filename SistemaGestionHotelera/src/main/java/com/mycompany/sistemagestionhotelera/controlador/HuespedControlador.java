package com.mycompany.sistemagestionhotelera.controlador;

import com.mycompany.sistemagestionhotelera.dao.HuespedDAO;
import com.mycompany.sistemagestionhotelera.modelo.Huesped;

public class HuespedControlador {
    private HuespedDAO huespedDAO;

    public HuespedControlador() {
        this.huespedDAO = new HuespedDAO();
    }

    public boolean registrarHuesped(String nombre, String documento, String telefono, String email, String tipo) {
        // Validación de campos vacíos
        if (nombre.trim().isEmpty() || documento.trim().isEmpty()) {
            return false;
        }
        Huesped nuevo = new Huesped(nombre, documento, telefono, email, tipo);
        return huespedDAO.registrarHuesped(nuevo);
    }
}