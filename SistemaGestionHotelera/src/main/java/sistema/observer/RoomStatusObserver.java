package sistema.observer;
import sistema.observer.ReservaObserver;
import sistema.database.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class RoomStatusObserver implements ReservaObserver {

    @Override
    public void onReservaCreada(int idHuesped, int idHabitacion, double costo, String canal) {
        // Al reservar, la habitación pasa de Disponible a Ocupada
        actualizarEstadoHabitacionEnDB(idHabitacion, "Ocupada");
    }

    @Override
    public void onCheckOutProcesado(int idReserva, int idHabitacion) {
        // Al hacer Check-Out, la habitación pasa de Ocupada a Limpieza
        System.out.println("[OBSERVER] Reaccionando a Check-Out. La habitación " + idHabitacion + " requiere limpieza.");
        actualizarEstadoHabitacionEnDB(idHabitacion, "Limpieza");
    }

    private void actualizarEstadoHabitacionEnDB(int idHabitacion, String nuevoEstado) {
        String sql = "UPDATE habitaciones SET estado = ? WHERE id_habitacion = ?";
        Connection cn = ConexionDB.getInstancia().getConexion();
        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, nuevoEstado);
            ps.setInt(2, idHabitacion);
            ps.executeUpdate();
            System.out.println("[OBSERVER - DATABASE] Estado de habitación " + idHabitacion + " actualizado a '" + nuevoEstado + "' exitosamente.");
        } catch (SQLException e) {
            System.out.println("[OBSERVER - ERROR] No se pudo actualizar el estado físico en DB: " + e.getMessage());
        }
    }
}