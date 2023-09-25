/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package semana07;

/**
 *
 * @author LAB-USR-AQ265-A0302
 */
public class Circulo extends Figura {

    private double radio;
    private double area;

    public Circulo(double x, double y, double radio) {
        super(x, y);
        this.radio = radio;
    }

    @Override
    public double area() {
        return Math.PI * Math.pow(radio, 2);
    }

    @Override
    public String mostrarInfo() {
        return super.mostrarInfo() + ", Radio: " + radio + ", Area: " + area();
    }

    public double getRadio() {
        return radio;
    }

    public void setRadio(double radio) {
        this.radio = radio;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

}
