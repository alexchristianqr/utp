package semana11;

public class Producto {

    private int codigo;
    private String description;
    private String marca;
    private double precio;

    public Producto(int codigo, String description, String marca, double precio) {
        this.codigo = codigo;
        this.description = description;
        this.marca = marca;
        this.precio = precio;
    }
    
    public String mostrarInfo(){
        String message = "";
        
        message+= "Codigo: "+codigo;
        message+= "\t Descripcion: "+description;
        message+= "\t Marca: "+marca;
        message+= "\t Precio: "+precio;
        
        return message;
    }

    @Override
    public String toString() {
        return "Producto{" + "codigo=" + codigo + ", description=" + description + ", marca=" + marca + ", precio=" + precio + '}';
    }
    
    

    public double actualizarPrecio(double precio) {
        this.precio = precio;
        return this.precio;
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
