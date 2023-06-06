package com.alexquispe.utp;

import java.util.Scanner;

public class Semana11 {

    public static void main(String[] args) {
        ejercicio01();
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

                matriz[i][j] = (int) (Math.random() * 20 + 0);

            }

        }

        // Pintar arreglo bidimensional
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {

                System.out.println("EL numero es: " + matriz[i][j]);

            }

        }

    }
}
