package sistema.modelo.state;

import sistema.modelo.Habitacion;
import sistema.modelo.state.EstadoHabitacion;

public class EstadoOcupada implements EstadoHabitacion {
    @Override
    public String obtenerNombreEstado() {
        return "Ocupada";
    }

    @Override
    public void reservar(Habitacion habitacion) {
        System.out.println("[STATE ERROR] No se puede reservar una habitación ya ocupada.");
    }

    @Override
    public void liberar(Habitacion habitacion) {
        System.out.println("[STATE] Habitación " + habitacion.getNumero() + " liberada. Pasa a Limpieza.");
        habitacion.setEstadoActual(new EstadoLimpieza());
    }

    @Override
    public void limpiar(Habitacion habitacion) {
        System.out.println("[STATE ALERTA] No se puede limpiar mientras el huésped esté adentro.");
    }
}