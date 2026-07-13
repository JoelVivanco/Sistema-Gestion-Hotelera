package sistema.modelo.state;

import sistema.modelo.Habitacion;

public interface EstadoHabitacion {
    String obtenerNombreEstado();
    
    void reservar(Habitacion habitacion);
    void liberar(Habitacion habitacion);
    void limpiar(Habitacion habitacion);
}