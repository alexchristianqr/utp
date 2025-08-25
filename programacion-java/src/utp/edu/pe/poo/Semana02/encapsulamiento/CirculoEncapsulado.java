/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utp.edu.pe.poo.Semana02.encapsulamiento;

/**
 *
 * @author LAB-USR-PT116-E306
 */
public class CirculoEncapsulado {

    protected double radio;
    protected static final double pi = 3.14;

    public CirculoEncapsulado(double radio) {
        this.radio = radio;
    }

    public double area() {
        double respuesta = (pi * radio);
        System.out.println("El area es: " + respuesta);
        return respuesta;
    }

}
