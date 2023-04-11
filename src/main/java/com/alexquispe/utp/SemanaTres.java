/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alexquispe.utp;

import java.util.Scanner;

/**
 *
 * @author alex
 */
public class SemanaTres {

    // Declarar como una variable global a nivel de clase
    static Scanner dato;

    // Ejecutar programa principal
    public static void main(String[] args) {

        // Declarar variables
        dato = new Scanner(System.in);

        ejercicio01(); // Ejecutar metodo
    }

    public static void tarea() {
        // D
        String sexo;
        int edad;
        double precio;

        // I
        System.out.println("Ingrese su sexo:");
        System.out.println("H: Hombre");
        System.out.println("M: Mujer");
        sexo = dato.next();

        System.out.println("Ingrese su edad:");
        edad = dato.nextInt();

        // P
        if (edad > 6 && edad <= 12) {
            precio = 10;
        } else if (edad > 12 && edad <= 60) {
            precio = 20;
        } else if (edad > 60) {
            precio = 15;
        } else {
            precio = 0;
        }

        // S
        System.out.println("Su sexo es: " + (sexo.equals("H") ? "Hombre" : "Mujer"));
        System.out.println("El precio de la entrada es: " + precio);
    }

    public static void ejercicio01() {
        // D
        int horasTrabajadas, horasTrabajadasExtra;
        double salarioSemanal, salarioJornal, salarioExtra, precioHora, precioHoraExtra;

        // I
        precioHora = 38;
        precioHoraExtra = 43;
        salarioSemanal = 0;

        System.out.println("Ingresar horas trabajadas:");
        horasTrabajadas = dato.nextInt();

        // P
        System.out.println("Horas trabajadas: " + horasTrabajadas);
        if (horasTrabajadas <= 40) {

            salarioSemanal = horasTrabajadas * precioHora;
            System.out.println("Salario jornal (" + horasTrabajadas + " * " + precioHora + ") : " + salarioSemanal);

        } else if (horasTrabajadas > 40) {

            horasTrabajadasExtra = horasTrabajadas - 40;// Diferencia de horas
            salarioExtra = horasTrabajadasExtra * precioHoraExtra;// Saldo por horas extras
            salarioJornal = 40 * precioHora; // Saldo por 40 horas 
            System.out.println("Salario jornal (40 * " + precioHora + ") : " + salarioJornal);
            System.out.println("Horas trabajadas extra: " + horasTrabajadasExtra);
            System.out.println("Salario extra (" + horasTrabajadasExtra + " * " + precioHoraExtra + ") : " + salarioExtra);
            salarioSemanal = salarioJornal + salarioExtra;

        }

        // S
        System.out.println("El salario semanal a pagar es: " + salarioSemanal);
    }
}
