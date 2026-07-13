package sistema.observer;
public interface ReservaObserver {
    // Se ejecuta al registrar una reserva
    void onReservaCreada(int idHuesped, int idHabitacion, double costo, String canal);
    
    // Se ejecuta al hacer Check-Out
    void onCheckOutProcesado(int idReserva, int idHabitacion);
}
