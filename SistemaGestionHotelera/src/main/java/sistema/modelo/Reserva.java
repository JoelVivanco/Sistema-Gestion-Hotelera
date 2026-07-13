package sistema.modelo;
public class Reserva {
    private int idReserva;
    private int idHuesped;
    private int idHabitacion;
    private String fechaIngreso;
    private String fechaSalida;
    private double costoTotal;
    private String canalReserva;

    // Constructor original (mantiene compatibilidad)
    public Reserva(int idHuesped, int idHabitacion, String fechaIngreso, String fechaSalida, double costoTotal, String canalReserva) {
        this.idHuesped = idHuesped;
        this.idHabitacion = idHabitacion;
        this.fechaIngreso = fechaIngreso;
        this.fechaSalida = fechaSalida;
        this.costoTotal = costoTotal;
        this.canalReserva = canalReserva;
    }

    // Constructor privado que utiliza el Builder
    private Reserva(Builder builder) {
        this.idReserva = builder.idReserva;
        this.idHuesped = builder.idHuesped;
        this.idHabitacion = builder.idHabitacion;
        this.fechaIngreso = builder.fechaIngreso;
        this.fechaSalida = builder.fechaSalida;
        this.costoTotal = builder.costoTotal;
        this.canalReserva = builder.canalReserva;
    }

    // --- CLASE ESTÁTICA INTERNA: BUILDER ---
    public static class Builder {
        private int idReserva;
        private int idHuesped;
        private int idHabitacion;
        private String fechaIngreso;
        private String fechaSalida;
        private double costoTotal;
        private String canalReserva;

        public Builder() {}

        public Builder conIdReserva(int idReserva) {
            this.idReserva = idReserva;
            return this; 
        }

        public Builder conHuesped(int idHuesped) {
            this.idHuesped = idHuesped;
            return this;
        }

        public Builder conHabitacion(int idHabitacion) {
            this.idHabitacion = idHabitacion;
            return this;
        }

        public Builder enFechas(String fechaIngreso, String fechaSalida) {
            this.fechaIngreso = fechaIngreso;
            this.fechaSalida = fechaSalida;
            return this;
        }

        public Builder conCosto(double costoTotal) {
            this.costoTotal = costoTotal;
            return this;
        }

        public Builder viaCanal(String canalReserva) {
            this.canalReserva = canalReserva;
            return this;
        }

        // Método que finalmente construye y retorna el objeto Reserva validado
        public Reserva build() {
            // Aquí se pueden agregar validaciones antes de instanciar
            if (idHuesped <= 0) {
                throw new IllegalStateException("La reserva debe tener un Huésped válido.");
            }
            if (idHabitacion <= 0) {
                throw new IllegalStateException("La reserva debe tener una Habitación válida.");
            }
            return new Reserva(this);
        }
    }

    // --- GETTERS Y SETTERS ---
    public int getIdReserva() { return idReserva; }
    public void setIdReserva(int idReserva) { this.idReserva = idReserva; }

    public int getIdHuesped() { return idHuesped; }
    public void setIdHuesped(int idHuesped) { this.idHuesped = idHuesped; }

    public int getIdHabitacion() { return idHabitacion; }
    public void setIdHabitacion(int idHabitacion) { this.idHabitacion = idHabitacion; }

    public String getFechaIngreso() { return fechaIngreso; }
    public void setFechaIngreso(String fechaIngreso) { this.fechaIngreso = fechaIngreso; }

    public String getFechaSalida() { return fechaSalida; }
    public void setFechaSalida(String fechaSalida) { this.fechaSalida = fechaSalida; }

    public double getCostoTotal() { return costoTotal; }
    public void setCostoTotal(double costoTotal) { this.costoTotal = costoTotal; }

    public String getCanalReserva() { return canalReserva; }
    public void setCanalReserva(String canalReserva) { this.canalReserva = canalReserva; }
}