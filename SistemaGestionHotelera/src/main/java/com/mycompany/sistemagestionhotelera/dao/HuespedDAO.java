package com.mycompany.sistemagestionhotelera.dao;

import com.mycompany.sistemagestionhotelera.database.ConexionDB;
import com.mycompany.sistemagestionhotelera.modelo.Huesped;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class HuespedDAO {

    public boolean registrarHuesped(Huesped huesped) {
        String sql = "{CALL sp_registrar_huesped(?, ?, ?, ?, ?)}";
        Connection cn = ConexionDB.getInstancia().getConexion();
        
        try (CallableStatement cstmt = cn.prepareCall(sql)) {
            cstmt.setString(1, huesped.getNombreCompleto());
            cstmt.setString(2, huesped.getDocumentoIdentidad());
            cstmt.setString(3, huesped.getTelefono());
            cstmt.setString(4, huesped.getEmail());
            cstmt.setString(5, huesped.getTipoCliente());
            
            int filasAfectadas = cstmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.out.println("Error en HuespedDAO: " + e.getMessage());
            return false;
        }
    }
}