package sistema.controlador;

import java.util.List;

public class HuespedControladorProxy implements IHuespedServicio {

    private HuespedControlador servicioReal;
    // Variable estática para saber quién inició sesión en el sistema
    private static String rolUsuarioActual = "Personal"; 

    public HuespedControladorProxy() {
        // Inicialización perezosa (Lazy Initialization) del servicio real
        this.servicioReal = new HuespedControlador();
    }

    // Método para actualizar el rol de la sesión actual al hacer Login
    public static void setRolUsuarioActual(String rol) {
        rolUsuarioActual = rol;
    }

    private boolean tieneAccesoAdministrativo() {
        // Validamos si tiene permisos de administración de forma insensible a mayúsculas
        return rolUsuarioActual != null && rolUsuarioActual.equalsIgnoreCase("Administracion");
    }

    @Override
    public boolean registrarHuesped(String nombre, String documento, String telefono, String email, String tipo) {
        if (tieneAccesoAdministrativo()) {
            return servicioReal.registrarHuesped(nombre, documento, telefono, email, tipo);
        } else {
            System.out.println("[PROXY SEGURIDAD] Intento de REGISTRO bloqueado. Rol actual: " + rolUsuarioActual);
            return false;
        }
    }

    @Override
    public List<Object[]> obtenerListaHuespedes() {
        // El listado es público, tanto Personal como Administradores pueden ver la tabla
        return servicioReal.obtenerListaHuespedes();
    }

    @Override
    public boolean modificarHuesped(int id, String nombre, String documento, String telefono, String email, String tipo) {
        if (tieneAccesoAdministrativo()) {
            return servicioReal.modificarHuesped(id, nombre, documento, telefono, email, tipo);
        } else {
            System.out.println("[PROXY SEGURIDAD] Intento de MODIFICACIÓN bloqueado para ID " + id);
            return false;
        }
    }

    @Override
    public boolean eliminarHuesped(int id) {
        if (tieneAccesoAdministrativo()) {
            return servicioReal.eliminarHuesped(id);
        } else {
            System.out.println("[PROXY SEGURIDAD] Intento de ELIMINACIÓN bloqueado para ID " + id);
            return false;
        }
    }
}