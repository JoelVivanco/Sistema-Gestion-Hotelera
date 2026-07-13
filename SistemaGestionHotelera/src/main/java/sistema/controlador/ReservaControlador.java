package sistema.controlador;
import sistema.dao.ReservaDAO;
import sistema.modelo.Reserva;
public class ReservaControlador {
    private ReservaDAO reservaDao;

    public ReservaControlador() {
        this.reservaDao = new ReservaDAO();
    }

    public boolean registrarReserva(int idHuesped, int idHabitacion, String ingreso, String salida, double costo, String canal) {
        
        System.out.println("=== PATRÓN BUILDER DETECTADO ===");
        System.out.println("Construyendo el objeto Reserva paso a paso de forma fluida...");
        Reserva nuevaReserva = new Reserva.Builder()
                .conHuesped(idHuesped)
                .conHabitacion(idHabitacion)
                .enFechas(ingreso, salida)
                .conCosto(costo)
                .viaCanal(canal)
                .build();
        
        System.out.println("Objeto Reserva construido con éxito. Enviando a persistencia...");
        System.out.println("=================================");

        return reservaDao.registrarReserva(
                nuevaReserva.getIdHuesped(),
                nuevaReserva.getIdHabitacion(),
                nuevaReserva.getFechaIngreso(),
                nuevaReserva.getFechaSalida(),
                nuevaReserva.getCostoTotal(),
                nuevaReserva.getCanalReserva()
        );
    }

    public java.util.List<Object[]> obtenerListaReservas() {
        return reservaDao.listarReservas();
    }

    public boolean procesarCheckOut(int idReserva) {
        return reservaDao.procesarCheckOut(idReserva);
    }
}
