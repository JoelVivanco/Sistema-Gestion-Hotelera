package sistema.dao;

import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sistema.database.ConexionDB;

public class HuespedDAO {

    // metodo para guardar un nuevo huesped llamando al sp
    public boolean registrarHuesped(String nombre, String documento, String telefono, String email, String tipo) {
        String sql = "{call sp_registrar_huesped(?,?,?,?,?)}";
        Connection con = ConexionDB.getInstancia().getConexion();
        try (CallableStatement cs = con.prepareCall(sql)) {

            cs.setString(1, nombre);
            cs.setString(2, documento);
            cs.setString(3, telefono);
            cs.setString(4, email);
            cs.setString(5, tipo);

            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error en HuespedDAO: " + e.getMessage());
            return false;
        }
    }

    // aca jalamos la lista completa de huespedes para la tabla
    public List<Object[]> listarHuespedes() {
        List<Object[]> lista = new ArrayList<>();
        String sql = "{call sp_listar_huespedes()}";
        Connection con = ConexionDB.getInstancia().getConexion();
        try (CallableStatement cs = con.prepareCall(sql); ResultSet rs = cs.executeQuery()) {

            while (rs.next()) {
                Object[] fila = new Object[]{
                    rs.getInt("id_huesped"),
                    rs.getString("nombre_completo"),
                    rs.getString("documento_identidad"),
                    rs.getString("telefono"),
                    rs.getString("email"),
                    rs.getString("tipo_cliente")
                };
                lista.add(fila);
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar huéspedes: " + ex.getMessage());
        }
        return lista;
    }

    // para actualizar los datos de un cliente usando su id
    public boolean modificarHuesped(int id, String nombre, String documento, String telefono, String email, String tipo) {
        String sql = "{call sp_modificar_huesped(?,?,?,?,?,?)}";
        Connection con = ConexionDB.getInstancia().getConexion();
        try (CallableStatement cs = con.prepareCall(sql)) {
            cs.setInt(1, id);
            cs.setString(2, nombre);
            cs.setString(3, documento);
            cs.setString(4, telefono);
            cs.setString(5, email);
            cs.setString(6, tipo);
            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error en HuespedDAO (modificar): " + e.getMessage());
            return false;
        }
    }

    // borra un huesped de la bd con el id
    public boolean eliminarHuesped(int id) {
        String sql = "{call sp_eliminar_huesped(?)}";
        Connection con = ConexionDB.getInstancia().getConexion();
        try (CallableStatement cs = con.prepareCall(sql)) {
            cs.setInt(1, id);
            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error en HuespedDAO (eliminar): " + e.getMessage());
            return false;
        }
    }
}
