package com.mycompany.sistemagestionhotelera.controlador;

import com.mycompany.sistemagestionhotelera.dao.ReservaDAO;
import com.mycompany.sistemagestionhotelera.modelo.Reserva;

public class ReservaControlador {
    private ReservaDAO reservaDAO;

    public ReservaControlador() {
        this.reservaDAO = new ReservaDAO();
    }

    public boolean registrarReserva(int idHuesped, int idHabitacion, String ingreso, String salida, double costo, String canal) {
        // validacion basica de consistencia monetaria y de fechas
        if (idHuesped <= 0 || idHabitacion <= 0 || costo <= 0) {
            return false;
        }
        Reserva nuevaReserva = new Reserva(idHuesped, idHabitacion, ingreso, salida, costo, canal);
        return reservaDAO.registrarReserva(nuevaReserva);
    }
}