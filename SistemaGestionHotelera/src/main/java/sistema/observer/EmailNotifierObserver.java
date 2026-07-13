package sistema.observer;

import sistema.observer.ReservaObserver;

public class EmailNotifierObserver implements ReservaObserver {
    @Override
    public void onReservaCreada(int idHuesped, int idHabitacion, double costo, String canal) {
        System.out.println("\n[OBSERVER - NOTIFICACIÓN] Enviando correo de confirmación al huésped con ID: " + idHuesped);
        System.out.println("Detalles de Reserva -> Habitación: " + idHabitacion + " | Costo: S/ " + costo + " | Canal: " + canal);
        System.out.println("[OBSERVER - STATUS] ¡Correo electrónico enviado con éxito!\n");
    }
    @Override
    public void onCheckOutProcesado(int idReserva, int idHabitacion) {
        System.out.println("\n[OBSERVER - NOTIFICACIÓN] Enviando boleta digital de Check-Out por correo.");
        System.out.println("Reserva ID: " + idReserva + " finalizada. ¡Agradecemos la preferencia del huésped!");
        System.out.println("[OBSERVER - STATUS] ¡Correo de Check-Out enviado!\n");
    }
}