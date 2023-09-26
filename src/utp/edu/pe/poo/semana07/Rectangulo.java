/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utp.edu.pe.poo.Semana07;

/**
 *
 * @author LAB-USR-AQ265-A0302
 */
public class Rectangulo extends Figura {

    private double largo;
    private double ancho;

    public Rectangulo(double x, double y, double largo, double ancho) {
        super(x, y);
        this.largo = largo;
        this.ancho = ancho;
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

    @Override
    public double area() {
        return largo * ancho;
    }

    @Override
    public String mostrarInfo() {
        return super.mostrarInfo() + " Largo: " + largo + ", Ancho: " + ancho + ", Area: " + area();
    }

}
