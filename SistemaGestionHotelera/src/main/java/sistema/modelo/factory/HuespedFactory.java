package sistema.modelo.factory;

import sistema.modelo.Huesped;

public class HuespedFactory {

    // Método Fábrica que retorna la instancia polimórfica correcta
    public static Huesped crearHuesped(String tipo, String nombre, String documento, String telefono, String email) {
        if (tipo == null) {
            return null;
        }

        switch (tipo.trim().toLowerCase()) {
            case "frecuente":
                return new HuespedFrecuente(nombre, documento, telefono, email);
            case "corporativo":
                return new HuespedCorporativo(nombre, documento, telefono, email);
            case "regular":
            default:
                return new HuespedRegular(nombre, documento, telefono, email);
        }
    }
}
