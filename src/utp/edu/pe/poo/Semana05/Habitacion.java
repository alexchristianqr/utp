/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package herencia;

/**
 *
 * @author LAB-USR-AQ265-A0302
 */
public class Habitacion {

    private int nroHabitacion;
    private double tarifa;

    public Habitacion(int nroHabitacion) {
        this.nroHabitacion = nroHabitacion;

        if (nroHabitacion >= 299) {
            tarifa = 69.95;

        } else {
            tarifa = 89.95;
        }
    }

    public void mostrarHabitacion() {
        System.out.println("La habitacion #: " + nroHabitacion + " y su tarifa es: " + tarifa);
    }
}
