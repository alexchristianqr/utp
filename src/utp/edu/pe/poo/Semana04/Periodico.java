/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utp.edu.pe.poo.Semana04;

/**
 *
 * @author LAB-USR-PT116-E306
 */
public class Periodico extends Diario {

    private double precio;

    public Periodico(String nombre, String fecha) {
        super(nombre, fecha);
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

}
