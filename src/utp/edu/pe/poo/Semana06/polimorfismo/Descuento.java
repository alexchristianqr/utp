package utp.edu.pe.poo.Semana06.polimorfismo;

public class Descuento extends Destino {
    private String tipoDescuento;
    private double descuento;

    public Descuento(String destino, String tipoDescuento) {
        super(destino);
        this.tipoDescuento = tipoDescuento;
        if (tipoDescuento.equalsIgnoreCase("convenio"))
            descuento = 0.08 * getCosto();
        else if (tipoDescuento.equalsIgnoreCase("Jubilado"))
            descuento = 0.12 * getCosto();
        else if (tipoDescuento.equalsIgnoreCase("Militar"))
            descuento = 0.5 * getCosto();
        else
            descuento = 0;
    }
}
