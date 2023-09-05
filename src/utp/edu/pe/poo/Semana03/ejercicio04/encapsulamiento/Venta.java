/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio04.encapsulamiento;

/**
 *
 * @author Alex
 */
public class Venta {

    private String nombreCliente;
    private String marca;
    private String modelo;
    private String color;
    private int numPuertas;
    private String motor;
    private int kilometraje;

    protected double precio = 0;
    protected double totalDesct = 0;
    protected double total = 0;

    public Venta(String nombreCliente, String marca, String modelo, String color, int numPuertas, String motor, int kilometraje) {
        this.nombreCliente = nombreCliente;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.numPuertas = numPuertas;
        this.motor = motor;
        this.kilometraje = kilometraje;
    }

    public String obtenerInformacion() {
        String mensaje = "";
        mensaje += "[Principal]";
        mensaje += "\n";
        mensaje += "\n";
        mensaje += "Cliente: " + nombreCliente;
        mensaje += "\n";
        mensaje += "Marca: " + marca;
        mensaje += "\n";
        mensaje += "Modelo: " + modelo;
        mensaje += "\n";
        mensaje += "Color: " + color;
        mensaje += "\n";
        mensaje += "Cantidad de puertas: " + numPuertas;
        mensaje += "\n";
        mensaje += "Motor: " + motor;
        mensaje += "\n";
        mensaje += "Kilometraje: " + kilometraje;
        mensaje += "\n";
        mensaje += "\n";
        mensaje += "[Detalle]";
        mensaje += "\n";
        mensaje += "Subtotal: " + precio;
        mensaje += "\n";
        mensaje += "Total desct: " + totalDesct;
        mensaje += "\n";
        mensaje += "Total: " + total;

        System.out.println(mensaje);

        return mensaje;
    }

    private double obtenerPrecio() {

        if (kilometraje >= 0 && kilometraje <= 5000) {
            precio = 15000;
        } else if (kilometraje > 5000 && kilometraje <= 15000) {
            precio = 12000;
        } else if (kilometraje > 15000 && kilometraje <= 30000) {
            precio = 10000;
        } else if (kilometraje > 30000) {
            precio = 8000;
        } else {
            precio = 0;
        }

        return precio;
    }

    private double calcularDescuento() {
        double descuento = 0;

        if (marca != null) {
            switch (marca) {
                case "Toyota":
                case "Nissan":
                    descuento = 0;
                    break;
                case "Kia":
                    descuento = 0.08;
                    break;
                case "Honda":
                    descuento = 0.1;
                    break;
                case "Chery":
                    descuento = 0.15;
                    break;
            }
        }

        precio = obtenerPrecio();
        totalDesct = descuento * precio;
        return totalDesct;
    }

    public double calcularPrecioFinal() {
        double totalDscto = calcularDescuento();
        total = precio - totalDscto;
        return total;
    }

    // Setters y Getters
    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getNumPuertas() {
        return numPuertas;
    }

    public void setNumPuertas(int numPuertas) {
        this.numPuertas = numPuertas;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public int getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(int kilometraje) {
        this.kilometraje = kilometraje;
    }

}
