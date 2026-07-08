package com.mycompany.sistemagestionhotelera.controlador;

import com.mycompany.sistemagestionhotelera.dao.HabitacionDAO;
import com.mycompany.sistemagestionhotelera.modelo.Habitacion;
import java.util.List;

public class HabitacionControlador {

    private HabitacionDAO habitacionDAO;

    public HabitacionControlador() {
        this.habitacionDAO = new HabitacionDAO();
    }

    // Solicita al DAO la lista de habitaciones libres segun su tipo (1=Simple, 2=Doble, etc.)
    public java.util.List<Integer> obtenerHabitacionesDisponibles() {
        return habitacionDAO.listarHabitacionesDisponibles();
    }

    public boolean guardarNuevaHabitacion(int id, String numero, int idTipo, String estado) {
        return habitacionDAO.registrarHabitacion(id, numero, idTipo, estado);
    }
}
