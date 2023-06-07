package com.alexquispe.utp;

import java.util.Scanner;

public class Semana11 {

    public static void main(String[] args) {
        tarea_06();
    }

    public static void ejercicio01() {
        int[][] matriz = new int[3][3];

        // fc => (0,[0,1,2])
        matriz[0][0] = 1;
        matriz[0][1] = 31;
        matriz[0][2] = 10;
        // fc => (1,[0,1,2])
        matriz[1][0] = 5;
        matriz[1][1] = 6;
        matriz[1][2] = 11;
        // fc => (2,[0,1,2])
        matriz[2][0] = 7;
        matriz[2][1] = 9;
        matriz[2][2] = 14;

        System.out.print("f[0]c[0] = " + matriz[0][0] + "\t");
        System.out.print("f[0]c[1] = " + matriz[0][1] + "\t");
        System.out.println("f[0]c[2] = " + matriz[0][2] + "\t");

        System.out.print("f[1]c[0] = " + matriz[1][0] + "\t");
        System.out.print("f[1]c[1] = " + matriz[1][1] + "\t");
        System.out.println("f[1]c[2] = " + matriz[1][2] + "\t");

        System.out.print("f[2]c[0] = " + matriz[2][0] + "\t");
        System.out.print("f[2]c[1] = " + matriz[2][1] + "\t");
        System.out.println("f[2]c[2] = " + matriz[2][2] + "\t");
    }

    public static void ejercicio02() {
        int[][] matriz = new int[3][3];

        // Cargar arreglo bidimensional
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {

                // (Math.randon) * (Max - Min) + min
                matriz[i][j] = (int) (Math.random() * (20 - 1) + 1);

            }

        }

        // Pintar arreglo bidimensional
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print("f[" + i + "]c[" + j + "] = " + matriz[i][j] + "\t");

            }
            System.out.println("\n");

        }
    }

    public static void ejercicio03() {
        int[][] matriz;
        int nfilas, ncolumnas, totalPares = 0, totalImpares = 0;
        Scanner dato = new Scanner(System.in);

        System.out.print("Ingresa N filas: ");
        nfilas = dato.nextInt();
        System.out.print("Ingresa N columnas: ");
        ncolumnas = dato.nextInt();

        matriz = new int[nfilas][ncolumnas];

        // Recorrer las filas
        for (int i = 0; i < matriz.length; i++) {

            // Obtener el tamaño de la fila[0]
            int totalFilas = matriz[0].length;

            // Recorrer las columnas
            for (int j = 0; j < totalFilas; j++) {

                // (Math.randon) * (Max - Min) + min
                matriz[i][j] = (int) (Math.random() * (20 - 1) + 1);

                if ((matriz[i][j] % 2) == 0) {
                    totalPares++;
                } else {
                    if ((matriz[i][j] % 2) == 1) {
                        totalImpares++;
                    }
                }
            }
        }
        System.out.print("\n");

        // Pintar arreglo bidimensional
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print("f[" + i + "]c[" + j + "] = " + matriz[i][j] + "\t");
            }
            System.out.println("\n");
        }

        System.out.println("El total de pares es: " + totalPares);
        System.out.println("El total de impares es: " + totalImpares);
    }

    public static void ejercicio04() {

        int[][] matriz;
        int[] vector;
        int multiplo;
        int nfilas, ncolumnas;
        Scanner dato = new Scanner(System.in);

        System.out.print("Ingresa N filas: ");
        nfilas = dato.nextInt();
        System.out.print("Ingresa M columnas: ");
        ncolumnas = dato.nextInt();
        System.out.print("Ingresa el multiplo: ");
        multiplo = dato.nextInt();

        matriz = new int[nfilas][ncolumnas];
        vector = new int[nfilas * ncolumnas];
        int k = 0;

        // Recorrer fila
        for (int i = 0; i < matriz.length; i++) {

            // Obtener el tamaño de la columna de la fila[0]
            int totalColumnas = matriz[0].length;

            // Recorrer columna
            for (int j = 0; j < totalColumnas; j++) {

                System.out.print("Ingresa el vaor para f[" + i + "]c[" + j + "]: ");
                matriz[i][j] = dato.nextInt();

                // (Math.randon) * (Max - Min) + min
                // matriz[i][j] = (int) (Math.random() * (20 - 1) + 1);
                // Evaluar si es multiplo
                if (matriz[i][j] % multiplo == 0) {
                    vector[k] = matriz[i][j];
                    k++;
                }
            }
        }
        System.out.print("\n");

        // Pintar matriz bidimensional
        System.out.println("La matriz bidimensional es: ");
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print("f[" + i + "]c[" + j + "] = " + matriz[i][j] + "\t");
            }
            System.out.print("\n");
        }
        System.out.print("\n");

        // Pintar vector
        System.out.println("El vector es: ");
        for (int i = 0; i < k; i++) {
            System.out.print("f[" + i + "] = " + vector[i] + "\n");
        }
    }

    public static void tarea_02() {
        int[][] matriz;
        int[] vectorImpares;
        int nfilas, ncolumnas, totalPares = 0, totalImpares = 0;
        Scanner dato = new Scanner(System.in);

        System.out.print("Ingresa N filas: ");
        nfilas = dato.nextInt();
        System.out.print("Ingresa N columnas: ");
        ncolumnas = dato.nextInt();

        matriz = new int[nfilas][ncolumnas];
        vectorImpares = new int[nfilas];

        // Recorrer fila
        for (int i = 0; i < matriz.length; i++) {

            // Obtener el tamaño de la columna de la fila[0]
            int totalColumnas = matriz[0].length;

            // Recorrer columna
            for (int j = 0; j < totalColumnas; j++) {

                // (Math.randon) * (Max - Min) + min
                matriz[i][j] = (int) (Math.random() * (20 - 1) + 1);

                if ((matriz[i][j] % 2) == 0) {
                    totalPares++;
                } else {
                    if ((matriz[i][j] % 2) == 1) {
                        vectorImpares[totalImpares] = matriz[i][j];// Guardar numero impar en un vector
                        totalImpares++;// Contar +1
                    }
                }
            }
        }
        System.out.print("\n");

        // Pintar matriz bidimensional
        System.out.println("La matriz bidimensional es: ");
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print("f[" + i + "]c[" + j + "] = " + matriz[i][j] + "\t");
            }
            System.out.print("\n");
        }
        System.out.print("\n");

        // Redimensionar vector
        // vectorImpares = Arrays.copyOf(vectorImpares, totalImpares);

        // Pintar vector
        System.out.println("El vector es: ");
        for (int i = 0; i < vectorImpares.length; i++) {
            System.out.print("f[" + i + "] = " + vectorImpares[i] + "\t");
        }
        System.out.println("\n");

        System.out.println("El total de pares es: " + totalPares);
        System.out.println("El total de impares es: " + totalImpares);
    }

    public static void tarea_03() {
        int[][] matriz;
        int nfilas, ncolumnas, numMayor, posicionFilaMayor, sumaFilaMayor = 0;
        Scanner dato = new Scanner(System.in);

        System.out.print("Ingresa N filas: ");
        nfilas = dato.nextInt();
        System.out.print("Ingresa N columnas: ");
        ncolumnas = dato.nextInt();

        matriz = new int[nfilas][ncolumnas];
        numMayor = matriz[0][0];
        posicionFilaMayor = 0;

        // Recorrer fila
        for (int i = 0; i < matriz.length; i++) {

            // Obtener el tamaño de la columna de la fila[0]
            int totalColumnas = matriz[0].length;

            // Recorrer columna
            for (int j = 0; j < totalColumnas; j++) {
                // (Math.randon) * (Max - Min) + min
                matriz[i][j] = (int) (Math.random() * (20 - 1) + 1);

                if (matriz[i][j] > numMayor) {
                    numMayor = matriz[i][j];
                    posicionFilaMayor = i;
                }
            }
        }
        System.out.print("\n");

        // Pintar matriz bidimensional
        System.out.println("La matriz bidimensional es: ");
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print("f[" + i + "]c[" + j + "] = " + matriz[i][j] + "\t");
            }
            System.out.print("\n");
        }
        System.out.print("\n");

        // Recorrer los elementos de la fila del numero mayor
        for (int j = 0; j < matriz[posicionFilaMayor].length; j++) {
            // Sumar elementos
            sumaFilaMayor = sumaFilaMayor + matriz[posicionFilaMayor][j];
        }

        System.out.println("El numero mayor es: " + numMayor);
        System.out.println("La fila del numero mayor es: " + posicionFilaMayor);
        System.out.println("La suma de los elementos de la fila del numero mayor es: " + sumaFilaMayor);
    }

    public static void tarea_04() {
        int[][] matriz;
        int nfilas, ncolumnas, posicionFilaInicial, posicionFilaFinal;
        Scanner dato = new Scanner(System.in);

        System.out.print("Ingresa N filas: ");
        nfilas = dato.nextInt();
        System.out.print("Ingresa N columnas: ");
        ncolumnas = dato.nextInt();

        matriz = new int[nfilas][ncolumnas];
        posicionFilaInicial = 0;
        posicionFilaFinal = matriz.length - 1;

        // Recorrer fila
        for (int i = 0; i < matriz.length; i++) {

            // Obtener el tamaño de la columna de la fila[0]
            int totalColumnas = matriz[0].length;

            // Recorrer columna
            for (int j = 0; j < totalColumnas; j++) {
                // (Math.randon) * (Max - Min) + min
                matriz[i][j] = (int) (Math.random() * (20 - 1) + 1);
            }
        }
        System.out.print("\n");

        // Pintar matriz bidimensional
        System.out.println("La matriz bidimensional es: ");
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print("f[" + i + "]c[" + j + "] = " + matriz[i][j] + "\t");
            }
            System.out.print("\n");
        }
        System.out.print("\n");

        // Recorrer los elementos de la fila del numero mayor
        for (int j = 0; j < matriz[posicionFilaInicial].length; j++) {
            // Guardar elemnto de la fila final antes que se pierda
            int elementoFilaFinal = matriz[posicionFilaFinal][j];

            // Actualizar los elemntos de la fila final
            matriz[posicionFilaFinal][j] = matriz[posicionFilaInicial][j];
            matriz[posicionFilaInicial][j] = elementoFilaFinal;
        }

        // Pintar matriz bidimensional
        System.out.println("La matriz bidimensional intercambiada es: ");
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print("f[" + i + "]c[" + j + "] = " + matriz[i][j] + "\t");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    public static void tarea_05() {
        int[][] matriz;
        int[] vectorNumPares;
        int nfilas, ncolumnas, numMayor;
        Scanner dato = new Scanner(System.in);

        System.out.print("Ingresa N filas: ");
        nfilas = dato.nextInt();
        System.out.print("Ingresa N columnas: ");
        ncolumnas = dato.nextInt();

        matriz = new int[nfilas][ncolumnas];
        vectorNumPares = new int[nfilas];

        // Recorrer fila
        for (int i = 0; i < matriz.length; i++) {

            // Obtener el tamaño de la columna de la fila[0]
            int totalColumnas = matriz[0].length;
            numMayor = 0;// Reiniciar la variable

            // Recorrer columna
            for (int j = 0; j < totalColumnas; j++) {
                // (Math.randon) * (Max - Min) + min
                matriz[i][j] = (int) (Math.random() * (100 - 1) + 1);

                if (matriz[i][j] > numMayor) {
                    numMayor = matriz[i][j];
                    vectorNumPares[i] = numMayor;
                }
            }
        }
        System.out.print("\n");

        // Pintar matriz bidimensional
        System.out.println("La matriz bidimensional es: ");
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print("f[" + i + "]c[" + j + "] = " + matriz[i][j] + "\t");
            }
            System.out.print("\n");
        }
        System.out.print("\n");

        // Pintar vector
        System.out.println("El vector de numeros pares es: ");
        for (int i = 0; i < vectorNumPares.length; i++) {
            System.out.print("f[" + i + "] = " + vectorNumPares[i] + "\n");
        }
    }

    public static void tarea_06() {
        int[][] matriz;
        int sumaDiagonalPrin = 0, sumaDiagonalSecu = 0, numPrin, numSecu;
        int nfilas, ncolumnas;
        Scanner dato = new Scanner(System.in);

        System.out.print("Ingresa N filas: ");
        nfilas = dato.nextInt();
        System.out.print("Ingresa N columnas: ");
        ncolumnas = dato.nextInt();

        matriz = new int[nfilas][ncolumnas];

        // Recorrer fila
        for (int i = 0; i < matriz.length; i++) {

            // Obtener el tamaño de la columna de la fila[0]
            int totalColumnas = matriz[0].length;

            // Recorrer columna
            for (int j = 0; j < totalColumnas; j++) {
                // (Math.randon) * (Max - Min) + min
                matriz[i][j] = (int) (Math.random() * (20 - 1) + 1);
            }
        }

        for (int i = 0; i < matriz.length; i++) {
            numPrin = matriz[i][i];
            numSecu = matriz[i][(matriz[i].length - 1) - i];
            sumaDiagonalPrin = sumaDiagonalPrin + numPrin;
            sumaDiagonalSecu = sumaDiagonalSecu + numSecu;
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

        System.out.println("La suma de la diagonal principal es: " + sumaDiagonalPrin);
        System.out.println("La suma de la diagonal secundaria es: " + sumaDiagonalSecu);
    }
}
