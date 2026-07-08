package com.mycompany.sistemagestionhotelera.dao;

import com.mycompany.sistemagestionhotelera.database.ConexionDB;
import com.mycompany.sistemagestionhotelera.modelo.Habitacion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HabitacionDAO {

    // metodo que invoca al Stored Procedure de MySQL
    public List<Habitacion> listarHabitacionesDisponibles(int idTipo) {
        List<Habitacion> lista = new ArrayList<>();
        String sql = "{CALL sp_listar_habitaciones_disponibles(?)}";
        Connection cn = ConexionDB.getInstancia().getConexion();
        
        try (CallableStatement cstmt = cn.prepareCall(sql)) {
            cstmt.setInt(1, idTipo);
            
            try (ResultSet rs = cstmt.executeQuery()) {
                while (rs.next()) {
                    Habitacion hab = new Habitacion();
                    hab.setIdHabitacion(rs.getInt("id_habitacion"));
                    hab.setNumero(rs.getString("numero"));
                    hab.setEstado(rs.getString("estado"));
                    lista.add(hab);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error en HabitacionDAO: " + e.getMessage());
        }
        return lista;
    }
}