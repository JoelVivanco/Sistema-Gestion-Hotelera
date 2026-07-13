package sistema.modelo.state;

import sistema.modelo.Habitacion;
import sistema.modelo.state.EstadoDisponible;
import sistema.modelo.state.EstadoHabitacion;

public class EstadoLimpieza implements EstadoHabitacion {
    @Override
    public String obtenerNombreEstado() {
        return "Limpieza";
    }

    @Override
    public void reservar(Habitacion habitacion) {
        System.out.println("[STATE ERROR] No se puede reservar. Aún está en proceso de limpieza.");
    }

    @Override
    public void liberar(Habitacion habitacion) {
        System.out.println("[STATE ALERTA] La habitación ya está vacía.");
    }

    @Override
    public void limpiar(Habitacion habitacion) {
        System.out.println("[STATE] Limpieza terminada. Habitación " + habitacion.getNumero() + " lista para usar.");
        habitacion.setEstadoActual(new EstadoDisponible());
    }
}
