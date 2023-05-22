package com.alexquispe.utp;

import java.util.Scanner;

public class Semana09 {
    public static void main(String[] args) {
        ejercicio06();
    }

    public static void ejercicio01() {
        // DI
        String[] nombres = {"Alex", "Marcos", "Pedro"};

        // PS
        for (int i = 0; i < nombres.length; i++) {
            System.out.println(nombres[i]);
        }
    }

    public static void ejercicio02() {
        // DI
        int[] numeros = {1, 2, 3, 4, 5};

        // PS
        for (int i = 0; i < numeros.length; i++) {
            System.out.println(numeros[i]);
        }
    }

    public static void ejercicio03() {
        // DI
        int[][] numeros = {{1, 2, 3, 4, 5}};

        // PS
        for (int i = 0; i < numeros.length; i++) {
            for (int j = 0; j < numeros[i].length; j++) {
                System.out.println(numeros[i][j]);
            }
        }
    }

    public static void ejercicio04() {
        int caras = 3;// Z
        int filas = 3;// Y
        int columnas = 3;// X
        int contador = 1;

        // Arreglo tridimensional (3 dimensiones)
        int[][][] numeros = new int[caras][filas][columnas];

        // Iterar cara x cara
        for (int z = 0; z < caras; z++) {
            System.out.println("Cara " + z);

            // Iterar fila x fila
            for (int y = 0; y < filas; y++) {

                // Iterar columna x columna
                for (int x = 0; x < columnas; x++) {
                    numeros[z][y][x] = contador;
                    contador++;
                    System.out.print("[" + numeros[z][y][x] + "]");
                }
                System.out.println();
            }
            contador = 1;// Reiniciar contador de arreglo bidimensional
        }
        System.out.println("--");
    }

    public static void ejercicio05() {
        // D
        String[] personas = {"Jose", "Juan", "Miguel", "Luis", "Alberto", "Daniela"};

        // IPS
        for (String persona : personas) {
            System.out.println(persona);
        }
    }

    public static void ejercicio06() {
        int filas = 3;
        int columnas = 3;
        int[][] matriz3x3 = new int[filas][columnas];

        Scanner leer = new Scanner(System.in);

        // Actualizar indices
        for (int i = 0; i < columnas; i++) {
            for (int j = 0; j < filas; j++) {
                System.out.println("Ingrese valores para la matriz 3x3");
                matriz3x3[i][j] = leer.nextInt();
            }
        }

        // Imprimir indices
        for (int i = 0; i < columnas; i++) {
            for (int j = 0; j < filas; j++) {
                System.out.print(" " + matriz3x3[i][j]);
            }
            System.out.println();
        }
    }

}
