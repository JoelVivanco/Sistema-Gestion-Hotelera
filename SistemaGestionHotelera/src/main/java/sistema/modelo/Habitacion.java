package sistema.modelo;

import sistema.modelo.state.EstadoLimpieza;
import sistema.modelo.state.EstadoOcupada;
import sistema.modelo.state.EstadoDisponible;
import sistema.modelo.state.EstadoHabitacion;

public class Habitacion {
    private int idHabitacion;
    private String numero;
    private int idTipo;
    private EstadoHabitacion estadoActual;

    public Habitacion(int idHabitacion, String numero, int idTipo, String estadoStr) {
        this.idHabitacion = idHabitacion;
        this.numero = numero;
        this.idTipo = idTipo;
        this.estadoActual = mapearEstado(estadoStr);
    }
    
    private EstadoHabitacion mapearEstado(String estadoStr) {
        if (estadoStr == null) return new EstadoDisponible();
        switch (estadoStr.toLowerCase()) {
            case "ocupada":
                return new EstadoOcupada();
            case "limpieza":
                return new EstadoLimpieza();
            case "disponible":
            default:
                return new EstadoDisponible();
        }
    }
    
    public void reservarHabitacion() {
        estadoActual.reservar(this);
    }

    public void liberarHabitacion() {
        estadoActual.liberar(this);
    }

    public void limpiarHabitacion() {
        estadoActual.limpiar(this);
    }

    public int getIdHabitacion() { return idHabitacion; }
    public void setIdHabitacion(int idHabitacion) { this.idHabitacion = idHabitacion; }
    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }
    public int getIdTipo() { return idTipo; }
    public void setIdTipo(int idTipo) { this.idTipo = idTipo; }
    public EstadoHabitacion getEstadoActual() { return estadoActual; }
    public void setEstadoActual(EstadoHabitacion nuevoEstado) { this.estadoActual = nuevoEstado; }

    // Método de compatibilidad para tus DAOs existentes
    public String getEstado() {
        return estadoActual.obtenerNombreEstado();
    }
}