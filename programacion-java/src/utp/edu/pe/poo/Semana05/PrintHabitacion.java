/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package herencia;

import java.util.Scanner;

/**
 *
 * @author LAB-USR-AQ265-A0302
 */
public class PrintHabitacion {

    public static void main(String[] args) {
        int nroHabitacion;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el nro de habitacion");
        nroHabitacion = scanner.nextInt();

        Habitacion habitacion = new Habitacion(nroHabitacion);
        habitacion.mostrarHabitacion();

        System.out.println("Ingrese el nro de Suite");
        nroHabitacion = scanner.nextInt();

        Suite suite = new Suite(nroHabitacion);
        suite.mostrarHabitacion();

    }
}
