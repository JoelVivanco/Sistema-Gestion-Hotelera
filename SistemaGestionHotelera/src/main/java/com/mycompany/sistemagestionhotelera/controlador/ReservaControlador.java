package com.mycompany.sistemagestionhotelera.controlador;

import com.mycompany.sistemagestionhotelera.dao.ReservaDAO;
import java.util.List;

public class ReservaControlador {
    private ReservaDAO reservaDao;

    public ReservaControlador() {
        this.reservaDao = new ReservaDAO();
    }

    public boolean registrarReserva(int idHuesped, int idHabitacion, String ingreso, String salida, double costo, String canal) {
        return reservaDao.registrarReserva(idHuesped, idHabitacion, ingreso, salida, costo, canal);
    }

    public List<Object[]> obtenerListaReservas() {
        return reservaDao.listarReservas();
    }
    
    public boolean procesarCheckOut(int idReserva) {
    return reservaDao.procesarCheckOut(idReserva);
}
}