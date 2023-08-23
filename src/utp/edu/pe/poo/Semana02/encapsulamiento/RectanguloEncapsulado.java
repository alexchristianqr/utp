/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package encapsulamiento;

/**
 *
 * @author LAB-USR-PT116-E306
 */
public class RectanguloEncapsulado {

    protected double largo;
    protected double ancho;

    public RectanguloEncapsulado(double largo, double ancho) {
        this.largo = largo;
        this.ancho = ancho;
    }

    public double area() {
        double respuesta = (largo * ancho);
        System.out.println("El area es: " + respuesta);
        return respuesta;
    }

    public double perimetro() {
        double respuesta = (2 * (largo + ancho));
        System.out.println("El perimetro es: " + respuesta);
        return respuesta;
    }

    public double getLargo() {
        return largo;
    }

    public void setLargo(double largo) {
        this.largo = largo;
    }

    public double getAncho() {
        return ancho;
    }

    public void setAncho(double ancho) {
        this.ancho = ancho;
    }

}
