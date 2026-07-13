package sistema.controlador;
import sistema.observer.ReservaObserver;
import sistema.observer.EmailNotifierObserver;
import sistema.observer.RoomStatusObserver;
import java.util.ArrayList;
import java.util.List;
import sistema.database.ConexionDB;
public class HotelFacade {

    private ReservaControlador reservaCtrl;
    private HabitacionControlador habitacionCtrl;
    private IHuespedServicio huespedProxy;

    // PATRÓN OBSERVER: Lista de observadores suscritos
    private List<ReservaObserver> observadores = new ArrayList<>();

    public HotelFacade() {
        this.reservaCtrl = new ReservaControlador();
        this.habitacionCtrl = new HabitacionControlador();
        this.huespedProxy = new HuespedControladorProxy();

        // Registrar los observadores por defecto automáticamente
        registrarObserver(new EmailNotifierObserver());
        registrarObserver(new RoomStatusObserver());
    }

    // Métodos para gestionar observadores dinámicamente
    public void registrarObserver(ReservaObserver observer) {
        this.observadores.add(observer);
    }

    public void removerObserver(ReservaObserver observer) {
        this.observadores.remove(observer);
    }

    // --- SUBSISTEMA DE HUÉSPEDES ---
    public boolean registrarHuesped(String nombre, String documento, String telf, String email, String tipo) {
        return huespedProxy.registrarHuesped(nombre, documento, telf, email, tipo);
    }

    public List<Object[]> obtenerListaHuespedes() {
        return huespedProxy.obtenerListaHuespedes();
    }

    public boolean modificarHuesped(int id, String nombre, String documento, String telf, String email, String tipo) {
        return huespedProxy.modificarHuesped(id, nombre, documento, telf, email, tipo);
    }

    public boolean eliminarHuesped(int id) {
        return huespedProxy.eliminarHuesped(id);
    }

    // --- SUBSISTEMA DE HABITACIONES ---
    public List<Integer> obtenerHabitacionesDisponibles() {
        return habitacionCtrl.obtenerHabitacionesDisponibles();
    }

    public boolean guardarNuevaHabitacion(int id, String numero, int idTipo, String estado) {
        return habitacionCtrl.guardarNuevaHabitacion(id, numero, idTipo, estado);
    }

    // --- SUBSISTEMA DE RESERVAS (CON EMISIÓN DE EVENTOS OBSERVER) ---
    public boolean registrarReserva(int idHuesped, int idHabitacion, String ingreso, String salida, double costo, String canal) {
        boolean exito = reservaCtrl.registrarReserva(idHuesped, idHabitacion, ingreso, salida, costo, canal);
        
        if (exito) {
            // NOTIFICAR EVENTO: Reserva creada
            for (ReservaObserver obs : observadores) {
                obs.onReservaCreada(idHuesped, idHabitacion, costo, canal);
            }
        }
        return exito;
    }

    public List<Object[]> obtenerListaReservas() {
        return reservaCtrl.obtenerListaReservas();
    }

    public boolean procesarCheckOut(int idReserva) {
        // Obtenemos el ID de la habitación asociada antes de liberar
        int idHabitacion = obtenerIdHabitacionDeReserva(idReserva);
        
        boolean exito = reservaCtrl.procesarCheckOut(idReserva);
        
        if (exito && idHabitacion != -1) {
            // NOTIFICAR EVENTO: Check-Out procesado
            for (ReservaObserver obs : observadores) {
                obs.onCheckOutProcesado(idReserva, idHabitacion);
            }
        }
        return exito;
    }

    // --- MÉTODOS AUXILIARES ---
    public String obtenerEmailHuesped(int idHuesped) {
        String email = null;
        String sql = "SELECT email FROM huespedes WHERE id_huesped = ?";
        java.sql.Connection con = ConexionDB.getInstancia().getConexion();
        try (java.sql.PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idHuesped);
            try (java.sql.ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    email = rs.getString("email");
                }
            }
        } catch (java.sql.SQLException ex) {
            System.out.println("Error Facade (obtenerEmailHuesped): " + ex.getMessage());
        }
        return email;
    }

    public int obtenerIdTipoHabitacion(int idHabitacion) {
        int idTipo = 1;
        String sql = "SELECT id_tipo FROM habitaciones WHERE id_habitacion = ?";
        java.sql.Connection con = ConexionDB.getInstancia().getConexion();
        try (java.sql.PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idHabitacion);
            try (java.sql.ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    idTipo = rs.getInt("id_tipo");
                }
            }
        } catch (java.sql.SQLException ex) {
            System.out.println("Error Facade (obtenerIdTipoHabitacion): " + ex.getMessage());
        }
        return idTipo;
    }

    // Nuevo método auxiliar requerido por el Observer para identificar la habitación liberada
    private int obtenerIdHabitacionDeReserva(int idReserva) {
        int idHab = -1;
        String sql = "SELECT id_habitacion FROM reservas WHERE id_reserva = ?";
        java.sql.Connection con = ConexionDB.getInstancia().getConexion();
        try (java.sql.PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idReserva);
            try (java.sql.ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    idHab = rs.getInt("id_habitacion");
                }
            }
        } catch (java.sql.SQLException ex) {
            System.out.println("Error Facade (obtenerIdHabitacionDeReserva): " + ex.getMessage());
        }
        return idHab;
    }
}