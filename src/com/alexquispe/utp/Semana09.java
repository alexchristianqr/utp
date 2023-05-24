package com.alexquispe.utp;

import java.util.Scanner;

public class Semana09 {
    public static void main(String[] args) {
        ejercicio10();
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

    public static void ejercicio07() {
        // DI
        Scanner scanner = new Scanner(System.in);
        int numeros[] = new int[4];
        int suma = 0;
        double promedio = 0;
//        numeros[0] = 1;
//        numeros[1] = 2;
//        numeros[2] = 3;
//        numeros[3] = 4;

        System.out.print("Ingresa el valor #1: ");
        numeros[0] = scanner.nextInt();
        System.out.print("Ingresa el valor #2: ");
        numeros[1] = scanner.nextInt();
        System.out.print("Ingresa el valor #3: ");
        numeros[2] = scanner.nextInt();
        System.out.print("Ingresa el valor #4: ");
        numeros[3] = scanner.nextInt();

//        suma = numeros[0] + numeros[1] + numeros[2] + numeros[3];
//        promedio = suma / 4;
        // PS
        for (int i = 0; i < numeros.length; i++) {
            System.out.println("El numero es: " + numeros[i]);
            suma = suma + numeros[i];
        }
//        promedio = (suma/numeros.length -1);
        promedio = (suma / 4);
        System.out.print("La suma es: " + suma + "\n");
        System.out.print("El promedio es: " + promedio + "\n");
    }

    public static void ejercicio08() {
        // DI
        int max;
        int index;
        int dato;
        String continuar = "N";
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingresa el tamaño de tu arreglo: ");
        max = scanner.nextInt();
        int numeros[] = new int[max];

        // PS
        // Iterar para ingresar datos por cada posición del arreglo
        do {
            index = max - 1;// Disminuir desde el maximo tamaño del arreglo
            System.out.print("Ingresa un numero para tu arreglo en el indice [" + index + "]: ");
            dato = scanner.nextInt();
            numeros[index] = dato;// Añadir un valor al arreglo en la posición "x"

            // Validar si el indice es mayor a CERO
            if (index > 0) {
                System.out.print("Quieres continuar agregando datos al arreglo S/N: ");
                continuar = scanner.next();
                max--;
            }

        } while (continuar.equalsIgnoreCase("S") && index != 0);

        // Iterar para imprimir los datos del arreglo
        for (int i = 0; i < numeros.length; i++) {
            System.out.println("El numero en el indice[" + i + "] es: " + numeros[i]);
        }

        // Nueva forma de recorrer los datos del arreglo
        /*for (int num : numeros) {
            System.out.println("El numero es: " + num);
        }*/
    }

    public static void ejercicio09() {
        int tamano;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el tamano del arreglo:");
        tamano = scanner.nextInt();

        int[] arreglo = new int[tamano];

        for (int i = 0; i < arreglo.length; i++) {
            // System.out.print("Ingrese el valor para el indice[" + i + "]: ");
            // arreglo[i] = scanner.nextInt();

            arreglo[i] = (int) (Math.random() * 50 + 1);
            System.out.println("El numero es: " + arreglo[i]);
        }
    }

    public static void ejercicio10() {
        int[] arreglo = new int[4];

        arreglo[0] = 4;
        arreglo[1] = 30;
        arreglo[2] = 3;
        arreglo[3] = 2;

        int menor = arreglo[0];
        int mayor = 0;

        for (int num : arreglo) {
            System.out.println("el valor es: " + num);

            if (num < menor) {
                menor = num;
            }

            if (num > mayor) {
                mayor = num;
            }
        }

        System.out.println("El numero mayor es: " + mayor);
        System.out.println("El numero menor es: " + menor);
    }

}
