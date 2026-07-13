package sistema.controlador;
import sistema.dao.HuespedDAO;
import java.util.List;
import sistema.modelo.Huesped;
import sistema.modelo.factory.HuespedFactory;
public class HuespedControlador implements IHuespedServicio {

    private HuespedDAO huespedDao;

    public HuespedControlador() {
        this.huespedDao = new HuespedDAO();
    }

    @Override
    public boolean registrarHuesped(String nombre, String documento, String telefono, String email, String tipo) {
        
        Huesped nuevoHuesped = HuespedFactory.crearHuesped(tipo, nombre, documento, telefono, email);

        System.out.println("=== PATRON FACTORY DETECTADO ===");
        System.out.println("Instanciado objeto tipo: " + nuevoHuesped.getClass().getSimpleName());
        System.out.println("Regra de Negocio Activa: " + nuevoHuesped.obtenerBeneficio());
        System.out.println("=================================");

        return huespedDao.registrarHuesped(
                nuevoHuesped.getNombreCompleto(),
                nuevoHuesped.getDocumentoIdentidad(),
                nuevoHuesped.getTelefono(),
                nuevoHuesped.getEmail(),
                nuevoHuesped.getTipoCliente()
        );
    }

    @Override 
    public List<Object[]> obtenerListaHuespedes() {
        return huespedDao.listarHuespedes();
    }

    @Override 
    public boolean modificarHuesped(int id, String nombre, String documento, String telefono, String email, String tipo) {
        Huesped h = HuespedFactory.crearHuesped(tipo, nombre, documento, telefono, email);

        return huespedDao.modificarHuesped(id, h.getNombreCompleto(), h.getDocumentoIdentidad(), h.getTelefono(), h.getEmail(), h.getTipoCliente());
    }

    @Override
    public boolean eliminarHuesped(int id) {
        return huespedDao.eliminarHuesped(id);
    }
}