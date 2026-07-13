package sistema.controlador;

import java.util.List;

public interface IHuespedServicio {
    boolean registrarHuesped(String nombre, String documento, String telefono, String email, String tipo);
    List<Object[]> obtenerListaHuespedes();
    boolean modificarHuesped(int id, String nombre, String documento, String telefono, String email, String tipo);
    boolean eliminarHuesped(int id);
}
