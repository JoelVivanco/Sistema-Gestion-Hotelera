package sistema.dao;

import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sistema.database.ConexionDB;

public class ReservaDAO {

    // metodo para guardar la reserva con control transaccional manual
    public boolean registrarReserva(int idHuesped, int idHabitacion, String ingreso, String salida, double costo, String canal) {
        Connection con = null;
        String sql = "{call sp_registrar_reserva(?,?,?,?,?,?)}";
        try {
            con = ConexionDB.getInstancia().getConexion();
            con.setAutoCommit(false); // aca apagamos el autocommit para iniciar transaccion

            try (CallableStatement cs = con.prepareCall(sql)) {
                cs.setInt(1, idHuesped);
                cs.setInt(2, idHabitacion);
                cs.setString(3, ingreso);
                cs.setString(4, salida);
                cs.setDouble(5, costo);
                cs.setString(6, canal);

                cs.executeUpdate();
            }

            con.commit(); // si todo corre bien mete el commit a la bd
            return true;
        } catch (SQLException e) {
            System.out.println("Error en transacción de Reserva: " + e.getMessage());
            if (con != null) {
                try {
                    con.rollback(); // si salta algun error revierte todo con rollback
                    System.out.println("Rollback ejecutado con éxito.");
                } catch (SQLException ex) {
                    System.out.println("Error al ejecutar Rollback: " + ex.getMessage());
                }
            }
            return false;
        } finally {
            if (con != null) {
                try {
                    con.setAutoCommit(true);
                } catch (SQLException e) {
                }
            }
        }
    }

    // este jala todas las reservas activas para cargarlas en la grilla
    public List<Object[]> listarReservas() {
        List<Object[]> lista = new ArrayList<>();
        String sql = "{call sp_listar_reservas()}";
        Connection con = ConexionDB.getInstancia().getConexion();
        try (CallableStatement cs = con.prepareCall(sql); ResultSet rs = cs.executeQuery()) {

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

    // ejecuta el sp de checkout para liberar el cuarto al toque
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
