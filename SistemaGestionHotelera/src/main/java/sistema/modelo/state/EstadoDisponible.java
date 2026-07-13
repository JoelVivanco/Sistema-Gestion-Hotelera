package sistema.modelo.state;

import sistema.modelo.Habitacion;
import sistema.modelo.state.EstadoHabitacion;

public class EstadoDisponible implements EstadoHabitacion {
    @Override
    public String obtenerNombreEstado() {
        return "Disponible";
    }

    @Override
    public void reservar(Habitacion habitacion) {
        System.out.println("[STATE] Habitación " + habitacion.getNumero() + " ha sido Reservada.");
        habitacion.setEstadoActual(new EstadoOcupada());
    }

    @Override
    public void liberar(Habitacion habitacion) {
        System.out.println("[STATE ALERTA] La habitación ya está disponible.");
    }

    @Override
    public void limpiar(Habitacion habitacion) {
        System.out.println("[STATE ALERTA] La habitación ya está limpia y lista.");
    }
}