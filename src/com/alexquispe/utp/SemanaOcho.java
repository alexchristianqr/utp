package com.alexquispe.utp;

import java.util.Scanner;

public class SemanaOcho {
    public static void main(String[] args) {
        ejercicio19();
    }

    public static void ejercicio19() {
        // DI
        int totalPostulantes = 0, totalRango1 = 0, totalRango2 = 0, totalRango3 = 0, puntos;
        Scanner scanner = new Scanner(System.in);
        String registrarPostulante;
        System.out.println("Desea registrar un postulante S/N");
        registrarPostulante = scanner.next();

        // P
        while (registrarPostulante.equalsIgnoreCase("S")) {
            System.out.println("Ingresa la cantidad de puntos para este postulante:");
            puntos = scanner.nextInt();
            if (puntos >= 0 && puntos <= 49) {
                totalRango1 = totalRango1 + 1;
            } else {
                if (puntos >= 50 && puntos <= 79) {
                    totalRango2 = totalRango2 + 1;
                } else {
                    if (puntos >= 80 && puntos <= 100) {
                        totalRango3 = totalRango3 + 1;
                    }
                }
            }

            totalPostulantes++;

            System.out.println("Desea registrar un postulante S/N");
            registrarPostulante = scanner.next();
        }

        // S
        System.out.println("La cantidad de postulantes es: " + totalPostulantes);
        System.out.println("Total rango 0-49 es: " + totalRango1);
        System.out.println("Total rango 50-79 es: " + totalRango2);
        System.out.println("Total rango 80-100 es: " + totalRango3);
    }
}
