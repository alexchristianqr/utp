/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.alexquispe.utp;

import java.util.Scanner;

/**
 * @author LAB-USR-AQ265-A0303
 */
public class SemanaCuatro {
    // Declarar como una variable global a nivel de clase
    static Scanner dato;


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // Instanciar o crear objeto
        dato = new Scanner(System.in);

        ejercicio03();
    }

    static void ejercicio01() {
        // D
        int dia;
        String nombreDia;

        // I
        System.out.println("Ingresa un dia de la semana:");
        System.out.println("----------------------------");
        System.out.println("1: Lunes");
        System.out.println("2: Martes");
        System.out.println("3: Miercoles");
        System.out.println("4: Jueves");
        System.out.println("5: Viernes");
        System.out.println("6: Sabado");
        System.out.println("7: Domingo");
        System.out.print("\n");
        dia = dato.nextInt();

        // P
        switch (dia) {
            case 1:
                nombreDia = "El dia es LUNES";
                break;
            case 2:
                nombreDia = "El dia es MARTES";
                break;
            case 3:
                nombreDia = "El dia es MIERCOLES";
                break;
            case 4:
                nombreDia = "El dia es JUEVES";
                break;
            case 5:
                nombreDia = "El dia es VIERNES";
                break;
            case 6:
                nombreDia = "El dia es SABADO";
                break;
            case 7:
                nombreDia = "El dia es DOMINGO";
                break;
            default:
                nombreDia = "No existe ese dia de la semana";
        }

        // S
        System.out.println("Rpta: " + nombreDia);


    }

    static void ejercicio02() {
        // D
        int num;
        String mensaje;

        // I
        System.out.println("Ingresa un numero de 0 a 9:");
        System.out.println("----------------------------");
        System.out.print("\n");
        num = dato.nextInt();

        // P
        if (num < 0 || num > 9) {
            mensaje = "No es un numero valido";
        } else {
            switch (num) {
                case 0:
                case 1:
                case 3:
                case 5:
                case 7:
                case 9:
                    mensaje = "Es impar";
                    break;
                default:
                    mensaje = "Es par";
            }
        }

        // S
        System.out.println("Rpta: " + mensaje);
    }

    /**
     * Ejercicio 1. Construir un programa que simule el funcionamiento
     * de una calculadora que pueda realizar las cuatro operaciones
     * aritméticas básicas (suma, resta, producto y división) al
     * ingresar 2 números enteros.
     */
    static void ejercicio03() {
        // D
        int opcion, operador;
        double num1, num2, resultado = 0;
        String mensaje = "";

        // I
        System.out.println("-------------------");
        System.out.println("Mi calculadora básica:");
        System.out.println("-------------------");
        System.out.println("Seleccionar una opcion:");
        System.out.println("1: Sumar");
        System.out.println("2: Restar");
        System.out.println("3: Multiplicar");
        System.out.println("4: Dividir");
        opcion = dato.nextInt();
        System.out.print("\n");
        System.out.println("Ingresar N° 1:");
        num1 = dato.nextDouble();
        System.out.println("Ingresar N° 2:");
        num2 = dato.nextDouble();
        System.out.print("\n");

        // P
        switch (opcion) {
            case 1:
                resultado = num1 + num2;
                mensaje = "El resultado es " + resultado;
                break;
            case 2:
                resultado = num1 - num2;
                mensaje = "El resultado es " + resultado;
                break;
            case 3:
                resultado = num1 * num2;
                mensaje = "El resultado es " + resultado;
                break;
            case 4:
                resultado = num1 / num2;
                mensaje = "El resultado es " + resultado;
                break;
            default:
                mensaje = "La opcion ingresada es invalida";
        }

        // S
        System.out.println("Rpta: " + mensaje);
    }

}
