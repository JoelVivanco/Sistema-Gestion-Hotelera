package sistema.modelo.factory;

import sistema.modelo.Huesped;

public class HuespedCorporativo extends Huesped {

    public HuespedCorporativo(String nombre, String documento, String telefono, String email) {
        super(nombre, documento, telefono, email);
    }

    @Override
    public String obtenerBeneficio() {
        return "Convenio Empresa: Credito a 30 días + Tarifas Netas Negociadas.";
    }

    @Override
    public String getTipoCliente() {
        return "Corporativo";
    }
}
