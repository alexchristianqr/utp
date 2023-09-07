/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utp.edu.pe.poo.Semana04;

/**
 *
 * @author LAB-USR-PT116-E306
 */
public class Revista extends Diario {

    private int tiraje;

    public Revista(String nombre, String fecha) {
        super(nombre, fecha);
    }

    public int getTiraje() {
        return tiraje;
    }

    public void setTiraje(int tiraje) {
        this.tiraje = tiraje;
    }

}
