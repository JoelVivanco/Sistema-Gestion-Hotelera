package com.mycompany.sistemagestionhotelera.dao;

import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.mycompany.sistemagestionhotelera.database.ConexionDB;

public class ReservaDAO {

    public boolean registrarReserva(int idHuesped, int idHabitacion, String ingreso, String salida, double costo, String canal) {
        Connection con = null;
        String sql = "{call sp_registrar_reserva(?,?,?,?,?,?)}";
        try {
            con = ConexionDB.getInstancia().getConexion();
            con.setAutoCommit(false); // Inicia la transacción explícita

            try (CallableStatement cs = con.prepareCall(sql)) {
                cs.setInt(1, idHuesped);
                cs.setInt(2, idHabitacion);
                cs.setString(3, ingreso);
                cs.setString(4, salida);
                cs.setDouble(5, costo);
                cs.setString(6, canal);
                
                cs.executeUpdate();
            }

            con.commit(); // Si todo está bien, confirma la transacción
            return true;
        } catch (SQLException e) {
            System.out.println("Error en transacción de Reserva: " + e.getMessage());
            if (con != null) {
                try {
                    con.rollback(); // Si falla, ejecuta el Rollback
                    System.out.println("Rollback ejecutado con éxito.");
                } catch (SQLException ex) {
                    System.out.println("Error al ejecutar Rollback: " + ex.getMessage());
                }
            }
            return false;
        } finally {
            if (con != null) {
                try { con.setAutoCommit(true); } catch (SQLException e) {}
            }
        }
    }

    public List<Object[]> listarReservas() {
        List<Object[]> lista = new ArrayList<>();
        String sql = "{call sp_listar_reservas()}";
        Connection con = ConexionDB.getInstancia().getConexion();
        try (CallableStatement cs = con.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {
            
            while (rs.next()) {
                Object[] fila = new Object[]{
                    rs.getInt("id_reserva"),
                    rs.getInt("id_huesped"),
                    rs.getInt("id_habitacion"),
                    rs.getString("fecha_ingreso"),
                    rs.getString("fecha_salida"),
                    "S/. " + rs.getDouble("costo_total"),
                    rs.getString("canal_reserva")
                };
                lista.add(fila);
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar reservas: " + ex.getMessage());
        }
        return lista;
    }
    public boolean procesarCheckOut(int idReserva) {
    String sql = "{call sp_procesar_checkout(?)}";
    Connection con = ConexionDB.getInstancia().getConexion();
    try (CallableStatement cs = con.prepareCall(sql)) {
        cs.setInt(1, idReserva);
        return cs.executeUpdate() > 0;
    } catch (SQLException e) {
        System.out.println("Error en ReservaDAO (Check-Out): " + e.getMessage());
        return false;
    }
}
}