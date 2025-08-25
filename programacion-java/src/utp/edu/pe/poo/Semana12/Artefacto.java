package semana12;

public class Artefacto {

    private int codigo;
    private String description;
    private String marca;
    private double precio;

    public Artefacto(int codigo, String description, String marca, double precio) {
        this.codigo = codigo;
        this.description = description;
        this.marca = marca;
        this.precio = precio;
    }

    @Override
    public String toString() {
        String mensaje = "";
        mensaje += "Código: " + codigo;
        mensaje += "\t Descrición: " + description;
        mensaje += "\t Marca: " + marca;
        mensaje += "\t Precio en PEN: " + precio;
        mensaje += "\t Precio en USD: " + precioDolar();
        mensaje += "\t Precio en EURO: " + precioEuro();

        return mensaje;
    }

    public double precioDolar() {
        return precio / 3.88;
    }

    public double precioEuro() {
        return precio / 4.08;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

}
