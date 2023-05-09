package com.alexquispe.utp;

import java.util.Scanner;

public class SemanaSiete {
    public static void main(String[] args) {
        ejercicio01();
    }

    public static void ejercicio01() {
        // D
        int num1, contador, hasta, acumulador = 0;

        // I
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese num 1:");
        num1 = scanner.nextInt();
        System.out.println("Ingrese num 2:");
        hasta = scanner.nextInt();

        // PS
        contador = num1;
        do {
            acumulador = acumulador + contador;
            contador++;
        } while (contador <= hasta);

        System.out.println("La suma de los numeros entre " + num1 + " y " + hasta + " es: " + acumulador);
    }

    public static void ejercicio02() {

    }
}
