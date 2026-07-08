package com.mycompany.sistemagestionhotelera.controlador;

import com.mycompany.sistemagestionhotelera.dao.HuespedDAO;
import java.util.List;

public class HuespedControlador {
    private HuespedDAO huespedDao;

    public HuespedControlador() {
        this.huespedDao = new HuespedDAO();
    }

    public boolean registrarHuesped(String nombre, String documento, String telefono, String email, String tipo) {
        // 1. Aplicación del Patrón GOF Factory: Delegamos la creación del objeto
        com.mycompany.sistemagestionhotelera.modelo.Huesped nuevoHuesped = 
            com.mycompany.sistemagestionhotelera.modelo.HuespedFactory.crearHuesped(tipo, nombre, documento, telefono, email);
        
        // Log de Auditoría en consola para demostrar el uso del patrón en la sustentación
        System.out.println("=== PATRON FACTORY DETECTADO ===");
        System.out.println("Instanciado objeto tipo: " + nuevoHuesped.getClass().getSimpleName());
        System.out.println("Regra de Negocio Activa: " + nuevoHuesped.obtenerBeneficio());
        System.out.println("=================================");

        // 2. Envío de los datos validados a la capa DAO para persistencia en MySQL
        return huespedDao.registrarHuesped(
            nuevoHuesped.getNombreCompleto(), 
            nuevoHuesped.getDocumentoIdentidad(), 
            nuevoHuesped.getTelefono(), 
            nuevoHuesped.getEmail(), 
            nuevoHuesped.getTipoCliente()
        );
    }

    public List<Object[]> obtenerListaHuespedes() {
        return huespedDao.listarHuespedes();
    }
    public boolean modificarHuesped(int id, String nombre, String documento, String telefono, String email, String tipo) {
        // Aplicamos la fábrica para validar el tipo modificado
        com.mycompany.sistemagestionhotelera.modelo.Huesped h = 
            com.mycompany.sistemagestionhotelera.modelo.HuespedFactory.crearHuesped(tipo, nombre, documento, telefono, email);
            
        return huespedDao.modificarHuesped(id, h.getNombreCompleto(), h.getDocumentoIdentidad(), h.getTelefono(), h.getEmail(), h.getTipoCliente());
    }

    public boolean eliminarHuesped(int id) {
        return huespedDao.eliminarHuesped(id);
    }
}