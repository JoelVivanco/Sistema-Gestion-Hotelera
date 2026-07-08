package com.mycompany.sistemagestionhotelera.dao;

import com.mycompany.sistemagestionhotelera.database.ConexionDB;
import com.mycompany.sistemagestionhotelera.modelo.Reserva;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class ReservaDAO {

    public boolean registrarReserva(Reserva reserva) {
        String sql = "{CALL sp_registrar_reserva(?, ?, ?, ?, ?, ?)}";
        Connection cn = ConexionDB.getInstancia().getConexion();
        
        try {
            cn.setAutoCommit(false);
            
            try (CallableStatement cstmt = cn.prepareCall(sql)) {
                cstmt.setInt(1, reserva.getIdHuesped());
                cstmt.setInt(2, reserva.getIdHabitacion());
                cstmt.setString(3, reserva.getFechaIngreso());
                cstmt.setString(4, reserva.getFechaSalida());
                cstmt.setDouble(5, reserva.getCostoTotal());
                cstmt.setString(6, reserva.getCanalReserva());
                
                int filasAfectadas = cstmt.executeUpdate();
                
                if (filasAfectadas > 0) {
                    // 2. SI TODO SALIÓ BIEN: Confirmamos los cambios en MySQL de forma permanente
                    cn.commit();
                    return true;
                } else {
                    // Si no afecto filas, forzamos un rollback por seguridad
                    cn.rollback();
                    return false;
                }
            } catch (SQLException e) {
                // 3. SI HUBO ERROR EN EL PROCESO: Deshacemos todo lo que se haya intentado hacer
                cn.rollback();
                System.out.println("Error en el Query de Reserva, se aplicó Rollback: " + e.getMessage());
                return false;
            } finally {
                // Siempre regresamos la conexión a su estado normal
                cn.setAutoCommit(true);
            }
            
        } catch (SQLException ex) {
            System.out.println("Error de transacción en ReservaDAO: " + ex.getMessage());
            return false;
        }
    }
}