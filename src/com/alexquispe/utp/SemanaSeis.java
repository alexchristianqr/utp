package com.alexquispe.utp;

import java.util.Scanner;

public class SemanaSeis {
    public static void main(String[] args) {
        ejercicio03();
    }

    static void ejercicio01() {
        // D
        int contador = 1, acumulador = 0, limite;

        // I
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el límite de la serie de números:");
        limite = scanner.nextInt();

        // PS
        while (contador <= limite) {
            acumulador = acumulador + contador;
            System.out.println("El número es: " + contador);
            contador++;
        }

        System.out.println("El total es: " + acumulador);
    }

    /**
     * Número a binario
     */
    static void ejercicio02() {
        // D
        int num, cociente = 0, residuo;
        String numBinario = "";

        // I
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese un número:");
        num = scanner.nextInt();

        // PS
        do {
            if (cociente > 0) {

                residuo = cociente % 2;
                cociente = (cociente / 2);

                if (cociente == 1) {
                    numBinario = cociente + "" + residuo + "" + numBinario;
                } else {
                    numBinario = residuo + "" + numBinario;
                }

                System.out.println("A: El residuo es:" + residuo + " cuando el cociente es: " + cociente);
            } else {
                residuo = num % 2;
                cociente = (num / 2);

                if (cociente == 1) {
                    numBinario = cociente + "" + residuo + "" + numBinario;
                } else {
                    numBinario = residuo + "" + numBinario;
                }

                System.out.println("B: El residuo es:" + residuo + " cuando el cociente es: " + cociente);
            }
        } while (cociente > 1);

        System.out.println("El binario es: " + numBinario);
    }

    /**
     * Cadena a binario
     */
    static void ejercicio03() {
        // D
        String letras, cadenaBinario, resultado = "";
        int indiceCadena, i = 0;

        // I
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese un nombre:");
        letras = sc.nextLine();

        // P
//        while (i < letras.length()) {
//            indiceCadena = letras.charAt(i);
//            cadenaBinario = Integer.toBinaryString(indiceCadena);
//            System.out.println("Cuando el caracter es: " + letras.charAt(i) + " su binario es: " + cadenaBinario);
//            resultado = resultado + cadenaBinario + " ";
//            i++;
//        }

        for (i = 0; i < letras.length(); i++) {
            indiceCadena = letras.charAt(i);
            cadenaBinario = Integer.toBinaryString(indiceCadena);
            System.out.println("Cuando el caracter es: " + letras.charAt(i) + " su binario es: " + cadenaBinario);
            resultado = resultado + cadenaBinario + " ";
        }

        // S
        System.out.printf("El binario es: " + resultado);
    }
}
