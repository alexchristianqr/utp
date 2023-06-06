package com.alexquispe.utp;

import java.util.Scanner;

public class Semana07 {

    public static void main(String[] args) {
        ejercicio05();
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
        // D
        int contador = 0, hasta, acumulador = 0;

        // I
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el maximo numero:");
        hasta = scanner.nextInt();

        // P
        do {
            // Al ser diferente de CERO es impar
            boolean esImpar = (contador % 2) != 0;

            if (esImpar) {
                System.out.println("El numero impar es: " + contador);
                // Sumar números impares
                acumulador = acumulador + contador;
            }

            contador++;
        } while (contador <= hasta);

        // S
        System.out.println("La suma de la serie es: " + acumulador);
    }

    public static void ejercicio03() {
        // D
        int acumulador = 0, cociente, residuo;

        // I
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese un numero para sumar sus digitos:");
        cociente = scanner.nextInt(); // 75231

        // PS
        do {
            residuo = cociente % 10;
            // Evaluar 2da vez para no perder el valor inicial del cociente
            cociente = cociente / 10;

            System.out.println("El cociente es: " + cociente + " y su residuo es: " + residuo);

            // Sumar números
            acumulador = acumulador + residuo;
        } while (cociente > 0);

        System.out.println("La suma de los digitos es: " + acumulador);
    }

    public static void ejercicio04() {
        // D
        int acumPares = 0, acumImpares = 0, cociente, residuo;

        // I
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese un numero para sumar sus digitos:");
        cociente = scanner.nextInt(); // 665510

        // P
        do {
            residuo = cociente % 10;
            // Evaluar 2da vez para no perder el valor inicial del cociente
            cociente = cociente / 10;

            boolean esImpar = (residuo % 2) != 0;// Al ser diferente de CERO es impar
            boolean esPar = (residuo % 2) == 0;// Al ser igual a CERO es par

            if (esPar) {
                System.out.println("El numero par es: " + residuo);
                // Sumar números pares
                acumPares = acumPares + residuo;
            }

            if (esImpar) {
                System.out.println("El numero impar es: " + residuo);
                // Sumar números impares
                acumImpares = acumImpares + residuo;
            }

        } while (cociente > 0);

        System.out.println("La suma de los digitos pares es: " + acumPares);
        System.out.println("La suma de los digitos impares es: " + acumImpares);
    }

    public static void ejercicio05() {
        // D
        int num;
        double promedio, nota, acumulador = 0;

        // I
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese una cantidad de notas");
        num = scanner.nextInt();

        // P
        for (int i = 1; i <= num; i++) {
            System.out.println("Ingrese la nota N°" + i);
            nota = scanner.nextDouble();

            acumulador = acumulador + nota;
        }

        promedio = acumulador / num;

        // S
        System.out.println("El promedio es: " + promedio);
    }
}
