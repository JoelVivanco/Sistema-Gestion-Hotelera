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

    // metodo que llama al sp de mysql
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

    public java.util.List<Integer> listarHabitacionesDisponibles() {
        java.util.List<Integer> lista = new java.util.ArrayList<>();
        // usamos el procedimiento que pusimos en el readme
        String sql = "{call sp_listar_habitaciones_disponibles()}";
        java.sql.Connection con = com.mycompany.sistemagestionhotelera.database.ConexionDB.getInstancia().getConexion();
        try (java.sql.CallableStatement cs = con.prepareCall(sql); java.sql.ResultSet rs = cs.executeQuery()) {

            while (rs.next()) {
                // jalamos el numero o id de la habitacion disponible
                lista.add(rs.getInt("id_habitacion"));
            }
        } catch (java.sql.SQLException ex) {
            System.out.println("Error al listar habitaciones disponibles: " + ex.getMessage());
        }
        return lista;
    }

    // nuevo metodo para meter una habitacion desde el sistema
    public boolean registrarHabitacion(int id, String numero, int idTipo, String estado) {
        String sql = "{call sp_registrar_habitacion(?, ?, ?, ?)}";
        java.sql.Connection con = com.mycompany.sistemagestionhotelera.database.ConexionDB.getInstancia().getConexion();

        try (java.sql.CallableStatement cs = con.prepareCall(sql)) {
            cs.setInt(1, id);
            cs.setString(2, numero);
            cs.setInt(3, idTipo);
            cs.setString(4, estado);

            cs.execute();
            return true;
        } catch (java.sql.SQLException ex) {
            System.out.println("Error en HabitacionDAO al registrar: " + ex.getMessage());
            return false;
        }
    }
}
