/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package semana07;

/**
 *
 * @author LAB-USR-AQ265-A0302
 */
public abstract class Figura {

    private double x;
    private double y;

    public Figura(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public abstract double area();

    public String mostrarInfo() {
        return "El valor de X: " + x + ", Y " + y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

}
