package com.alexquispe.utp.Semana15;

import java.util.Scanner;

public class Semana15 {

    public static void main(String[] args) {
        ejercicio06();
    }

    /* Practica calificada 03 */

    public static void pregunta04() {

        int N = 4;
        int M = 4;
        int[][] matriz = new int[N][M];

        // Asignar valores a todos los elementos
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                matriz[i][j] = (int) (Math.random() * (30 - 1) + 1);
            }
        }

        // Pintar matriz bidimensional
        System.out.println("La matriz bidimensional es: ");
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print("f[" + i + "]c[" + j + "] = " + matriz[i][j] + "\t");
            }
            System.out.print("\n");
        }
        System.out.print("\n");

        // Asignar a la diagonal secundaria
        for (int i = 0; i < matriz.length; i++) {
            // matriz[i][(M-1) -i] = 0;
            matriz[i][(M - 1) - i] *= 4;
        }

        // Mostrar la matriz
        System.out.println("Matriz resultante:");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print("f[" + i + "]c[" + j + "] = " + matriz[i][j] + "\t");
            }
            System.out.print("\n");
        }

    }
    
    public static void pregunta02() {
        Scanner scanner = new Scanner(System.in);

        // Ingresar tamaño de la matriz
        System.out.print("Ingrese el número de filas (N): ");
        int N = scanner.nextInt();
        System.out.print("Ingrese el número de columnas (M): ");
        int M = scanner.nextInt();

        // Crear la matriz
        int[][] matriz = new int[N][M];

        // Cargar matriz
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                matriz[i][j] = (int) (Math.random() * (30 - 1) + 1);
            }
        }

        // Pintar matriz bidimensional
        System.out.println("La matriz bidimensional es: ");
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print("f[" + i + "]c[" + j + "] = " + matriz[i][j] + "\t");
            }
            System.out.print("\n");
        }
        System.out.print("\n");

        // Multiplicar por 3 los elementos formando la letra C
        // Sumar la fila inferior de la "C"
        for (int i = 0; i < M; i++) {
            matriz[0][i] *= 3;// Fila superior
            matriz[N - 1][i] *= 3;// Fila inferior
        }

        // Sumar la columna izquierda de la "C"
        for (int i = 1; i < N - 1; i++) {
            matriz[i][0] *= 3;// Columna izquierda
        }

        // Imprimir la matriz resultante
        System.out.println("Matriz resultante:");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print("f[" + i + "]c[" + j + "] = " + matriz[i][j] + "\t");
            }
            System.out.print("\n");
        }
    }
    
    
    
    
    /* -- */

    public static void ejercicio01() {
        Scanner scanner = new Scanner(System.in);

        double[] vector;

        System.out.print("Ingrese la cantidad de alumnos: ");
        int nAlumnos = scanner.nextInt();

        System.out.print("Ingrese la cantidad de notas por alumno: ");
        int nNotas = scanner.nextInt();

        int[][] matrizNotas = new int[nAlumnos][nNotas];
        vector = new double[nAlumnos * nNotas];
        int k = 0;

        // Ingresar las notas en la matriz
        for (int i = 0; i < nAlumnos; i++) {
            System.out.println("Ingrese las notas del alumno " + (i + 1) + ":");
            for (int j = 0; j < nNotas; j++) {
                matrizNotas[i][j] = scanner.nextInt();
            }
        }

        // Pintar matriz bidimensional
        System.out.println("La matriz bidimensional es: ");
        for (int i = 0; i < matrizNotas.length; i++) {
            for (int j = 0; j < matrizNotas[i].length; j++) {
                System.out.print("f[" + i + "]c[" + j + "] = " + matrizNotas[i][j] + "\t");
            }
            System.out.print("\n");
        }
        System.out.print("\n");

        // Calcular el promedio eliminando la nota más baja
        double promedioTotal = 0;
        for (int i = 0; i < nAlumnos; i++) {
            int notaMinima = matrizNotas[i][0];
            int sumaNotas = 0;
            promedioTotal = 0;

            // Encontrar la nota más baja
            for (int j = 0; j < nNotas; j++) {
                sumaNotas += matrizNotas[i][j];
                if (matrizNotas[i][j] < notaMinima) {
                    notaMinima = matrizNotas[i][j];
                }
            }

            // Calcular el promedio eliminando la nota más baja
            int cantidadNotas = nNotas - 1;
            double promedioAlumno = (sumaNotas - notaMinima) / (double) cantidadNotas;

            promedioTotal += promedioAlumno;

            // Guardar el promedio
            vector[k] = promedioTotal;
            k++;
        }

        // Pintar vector
        System.out.println("El vector es: ");
        for (int i = 0; i < k; i++) {
            System.out.print("f[" + i + "] = " + vector[i] + "\n");
        }
    }

    public static void ejercicio02() {
        int[][] matriz = new int[3][3];

        // Asignar ceros a todos los elementos
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                matriz[i][j] = 0;
            }
        }

        // Asignar unos a la diagonal principal
        for (int i = 0; i < matriz.length; i++) {
            matriz[i][i] = 1;
        }

        // Mostrar la matriz
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void ejercicio03() {
        Scanner scanner = new Scanner(System.in);

        // Ingresar tamaño de la matriz
        System.out.print("Ingrese el número de filas (N): ");
        int N = scanner.nextInt();
        System.out.print("Ingrese el número de columnas (M): ");
        int M = scanner.nextInt();

        // Crear la matriz
        int[][] matriz = new int[N][M];

        // Ingresar los elementos de la matriz
        System.out.println("Ingrese los elementos de la matriz:");

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print("Ingrese el elemento en la posición (" + i + ", " + j + "): ");
                matriz[i][j] = scanner.nextInt();
            }
        }

        // Multiplicar por 2 los elementos formando la letra U
        // Sumar la fila inferior de la "U"
        for (int i = 0; i < M; i++) {
            matriz[N - 1][i] *= 2;
        }

        // Sumar la columna izquierda y derecha de la "U"
        for (int i = 0; i < N - 1; i++) {
            matriz[i][0] *= 2;// Columna izquierda
            matriz[i][M - 1] *= 2; // Columna derecha
        }

        // Imprimir la matriz resultante
        System.out.println("Matriz resultante:");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print("f[" + i + "]c[" + j + "] = " + matriz[i][j] + "\t");
            }
            System.out.print("\n");
        }
    }

    public static void ejercicio04() {
        int[][] matriz = {{5, 8, 12}, {7, 13, 14}, {9, 11, 6}, {3, 4, 6}};

        int filas = matriz.length;
        int columnas = matriz[0].length;

        int[] vectorSuma = new int[filas * columnas];
        int k = 0;

        for (int i = 0; i < filas; i++) {
            int sumaFila = 0;
            for (int j = 0; j < columnas; j++) {
                sumaFila += matriz[i][j];
            }
            vectorSuma[k] = sumaFila;
            k++;
        }

        // Mostrar el vector de suma
        for (int i = 0; i < filas; i++) {
            System.out.println("Suma de la fila " + (i + 1) + ": " + vectorSuma[i]);
        }
    }

    public static void ejercicio05() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el número de filas (N): ");
        int n = scanner.nextInt();

        System.out.print("Ingrese el número de columnas (M): ");
        int m = scanner.nextInt();

        int[][] matriz = new int[n][m];

        System.out.print("\n");

        // Ingresar elementos a la matriz
        System.out.print("Ingrese los elementos de la matriz:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // matriz[i][j] = scanner.nextInt();
                // (Math.randon) * (Max - Min) + min
                matriz[i][j] = (int) (Math.random() * (50 - 1) + 1);
            }
        }
        System.out.print("\n");

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print("f[" + i + "]c[" + j + "] = " + matriz[i][j] + "\t");
            }
            System.out.print("\n");
        }
        System.out.print("\n");

        // Encontrar el número mayor y menor
        int mayor = matriz[0][0];
        int menor = matriz[0][0];
        int filaMayor = 0;
        int colMayor = 0;
        int filaMenor = 0;
        int colMenor = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matriz[i][j] > mayor) {
                    mayor = matriz[i][j];
                    filaMayor = i;
                    colMayor = j;
                }

                if (matriz[i][j] < menor) {
                    menor = matriz[i][j];
                    filaMenor = i;
                    colMenor = j;
                }
            }
        }

        // Intercambiar el número mayor con el menor
        int temp = matriz[filaMayor][colMayor];
        matriz[filaMayor][colMayor] = matriz[filaMenor][colMenor];
        matriz[filaMenor][colMenor] = temp;

        // Imprimir la matriz intercambiada
        System.out.println("Matriz después del intercambio:");
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print("f[" + i + "]c[" + j + "] = " + matriz[i][j] + "\t");
            }
            System.out.print("\n");
        }
    }

    public static void ejercicio06() {

        int version;
        double precio = 0, dscto = 0, totalDscto, totalPago;
        int cantidadLicencias;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese la version:");
        System.out.println("1: Estudiante");
        System.out.println("2: Profesional");
        System.out.println("3: Empresarial");
        version = scanner.nextInt();
        System.out.println("Ingrese la cantidad");
        cantidadLicencias = scanner.nextInt();

        switch (version) {
            case 1:
                precio = 90;
                break;
            case 2:
                precio = 120;
                break;
            case 3:
                precio = 150;
                break;
            default:
                return;
        }

        if (cantidadLicencias < 5) {
            dscto = 0.11;// 11%
        } else if (cantidadLicencias >= 5 && cantidadLicencias < 10) {
            dscto = 0.13;// 13%
        } else if (cantidadLicencias >= 10) {
            dscto = 0.14;// 14%
        }

        totalDscto = (precio * dscto) * cantidadLicencias;
        totalPago = (precio * cantidadLicencias) - totalDscto;

        System.out.println("El total es: " + (precio * cantidadLicencias));
        System.out.println("El total dscto es: " + totalDscto);
        System.out.println("El precio total con dscto " + (dscto * 100) + "% a pagar es: " + totalPago);
    }

}
