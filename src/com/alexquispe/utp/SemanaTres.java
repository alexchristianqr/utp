/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alexquispe.utp;

import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JComponent;

/**
 *
 * @author alex
 */
public class SemanaTres {

    // Declarar como una variable global a nivel de clase
    static Scanner dato;

    // Ejecutar programa principal
    public static void main(String[] args) {

        // Instanciar o crear objeto
        dato = new Scanner(System.in);

        // Ejecutar método
        ejercicio03();
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
        System.out.println("Su sexo es: " + (sexo.equals("H") ? "Hombre" : sexo.equals("M") ? "Mujer" : ""));
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

    public static void ejercicio02() {
        // D
        int a, b, c, d, e;

        // I
        System.out.println("Ingresar numero para a:");
        a = dato.nextInt();
        System.out.println("Ingresar numero para b:");
        b = dato.nextInt();
        System.out.println("Ingresar numero para c:");
        c = dato.nextInt();
        System.out.println("Ingresar numero para d:");
        d = dato.nextInt();
        System.out.println("Ingresar numero para e:");
        e = dato.nextInt();

        // P
        if (a > b && a > c && a > d && a > e) {
            System.out.println("El numero mayor es a: " + a);
        } else if (b > a && b > c && b > d && d > e) {
            System.out.println("El numero mayor es b: " + b);
        } else if (c > a && c > b && c > d && c > e) {
            System.out.println("El numero mayor es c: " + c);
        } else if (d > a && d > b && d > c && d > e) {
            System.out.println("El numero mayor es d: " + d);
        } else if (e > a && e > b && e > c && e > d) {
            System.out.println("El numero mayor es e: " + e);
        } else {
            System.out.println("No existe numero mayor");
        }
        // S
    }

    public static void ejercicio03() {
        // D
        double tarifa, total;
        int tipoPrenda, cantidad;

        // I
        tarifa = 0;
        System.out.println("Ingresar tipo de prenda");
        System.out.println("1: Pantalon");
        System.out.println("2: Saco");
        System.out.println("3: Abrigo");
        tipoPrenda = dato.nextInt();

        System.out.println("Ingresar cantidad de unidades:");
        cantidad = dato.nextInt();

        // P
        if (tipoPrenda == 1) {
            tarifa = 12;
        } else if (tipoPrenda == 2) {
            tarifa = 32;
        } else if (tipoPrenda == 3) {
            tarifa = 42;
        } else {
            System.out.println("No existe el tipo de prenda");
        }
        total = cantidad * tarifa;

        // S
        System.out.println("El sueldo total a pagar es: " + total);
    }

    public static void ejercicio04() {
        // D
        int t, d;
        float v;

        // I
        v = 0;
        String sd = JOptionPane.showInputDialog("Ingrese Distancia:");
        String st = JOptionPane.showInputDialog("Ingrese Tiempo:");

        // P
        d = Integer.parseInt(sd);
        t = Integer.parseInt(st);
        if (t > 0) {
            v = (float) (d) / t;
        }

        // S
        System.out.println("La velocidad (m/s) es: " + v);

    }

    public static void ejercicio05() {
        // D
        int t, d;
        float v;

        // I
        String sd = JOptionPane.showInputDialog("Ingrese Distancia:");
        String st = JOptionPane.showInputDialog("Ingrese Tiempo:");

        // PS
        d = Integer.parseInt(sd);
        t = Integer.parseInt(st);
        if (t > 0 && d > 0) {
            v = (float) (d) / t;
            System.out.println("LA VELOCIDAD (m/s) ES: " + v);
        } else {
            JOptionPane.showMessageDialog(new JComponent() {
            }, "Los datos ingresados deben ser diferentes de cero");
        }

    }
}
