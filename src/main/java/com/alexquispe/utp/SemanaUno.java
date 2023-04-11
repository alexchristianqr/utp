/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.alexquispe.utp;

import java.util.Scanner;

/**
 *
 * @author alex
 */
public class SemanaUno {

    // Declarar como una variable global a nivel de clase
    static Scanner dato;

    // Ejecutar programa principal
    public static void main(String[] args) {

        // Instanciar o crear objeto
        dato = new Scanner(System.in);

        // Ejecutar método
        tarea02();
    }

    /**
     * Tarea 01 Ingrese su nombre, edad, genero y talla desde el teclado, luego
     * mostrar estos datos.
     */
    public static void tarea01() {
        // Declarar variables
        String nombre, genero, talla;
        int edad;

        // Inicializar variables
        System.out.println("Ingresar nombre: ");
        nombre = dato.next();
        System.out.println("Ingresar genero M o F");
        genero = dato.next();
        System.out.println("Ingresar talla S, M o L");
        talla = dato.next();
        System.out.println("Ingresar edad");
        edad = dato.nextInt();

        // Salida
        System.out.println("[Datos Personales]");
        System.out.println("1.Nombre: " + nombre);
        System.out.println("2.Genero: " + genero);
        System.out.println("3.Talla: " + talla);
        System.out.println("4.Edad: " + edad);

    }

    /**
     * Tarea 02 Escriba un programa para ingresar 3 notas de un alumno, calcular
     * y mostrar su promedio, sabiendo que la tercera nota tiene peso 2.
     */
    public static void tarea02() {
        // Declarar variables
        double n1, n2, n3, promedio, pesoN1, pesoN2, pesoN3;

        // Inicializar variables
        System.out.println("Ingresar Nota #1");
        n1 = dato.nextDouble();
        System.out.println("Ingresar Nota #2");
        n2 = dato.nextDouble();
        System.out.println("Ingresar Nota #3");
        n3 = dato.nextDouble();

        // Proceso
        // Aplicar peso para la nota #3
        pesoN1 = n1 * 0.25;
        pesoN2 = n2 * 0.25;
        pesoN3 = n3 * 0.5;

        // Calcular nota promedio ponderado
        promedio = (pesoN1 + pesoN2 + pesoN3);

        // Salida
        System.out.println("El promedio es: " + promedio);
    }

    /**
     * Tarea 03 Una tienda ofrece un descuento del 5% del monto de la compra,
     * mostrar el monto del descuento y el monto final que paga un cliente
     */
    public static void tarea03() {
        // Declarar variables
        double porcentajeDscto, montoCompra, montoDscto, montoFinal;

        // Inicializar variables
        porcentajeDscto = 0.05; // 5%
        System.out.println("Ingresar el monto de la compra");
        montoCompra = dato.nextDouble();

        // Proceso 
        montoDscto = montoCompra * porcentajeDscto;
        montoFinal = montoCompra - montoDscto;// 100% - 5%

        // Salida
        System.out.println("El monto total a pagar es: " + montoFinal);
        System.out.println("EL descuento aplicado es : " + porcentajeDscto * 100);
    }

    /**
     * Tarea 04 Desarrolle un programa que permita calcular el porcentaje de
     * hombres y mujeres que se encuentran en el aula.
     */
    public static void tarea04() {
        // Declarar variables
        int cantMujeres, cantHombres, total;
        double porcentajeMujeres, porcentajeHombres;

        // Inicializar variables
        System.out.println("Ingresar total mujeres");
        cantMujeres = dato.nextInt();
        System.out.println("Ingresar total hombres");
        cantHombres = dato.nextInt();

        // Proceso 
        total = cantMujeres + cantHombres;
        porcentajeHombres = (cantHombres / total) * 100;
        porcentajeMujeres = (cantMujeres / total) * 100;

        // Salida
        System.out.println("El porcentaje de hombres es: " + porcentajeHombres);
        System.out.println("El porcentaje de mujeres es: " + porcentajeMujeres);
    }

    /**
     * Tarea 05 a = b + 2c + 3m c = a + 3b + n z = m + n + 5a
     */
    public static void tarea05() {
        // Declarar variables
        int a, b, c, m, n, z;

        // Inicializar variables
        System.out.println("Ingresar un valor entero para a");
        a = dato.nextInt();
        System.out.println("Ingresar un valor entero para b");
        b = dato.nextInt();
        System.out.println("Ingresar un valor entero para c");
        c = dato.nextInt();
        System.out.print("\r");

        // Proceso 
        // Ecuación para hallar m
        System.out.println("Ecuaciones:");

        m = (a - b - (2 * c)) / 3;// m = (a - b - 2c)/3
        System.out.println("Ecuación para hallar m = " + "(" + a + " - " + b + " - " + "2(" + c + "))" + "/ 3");
        System.out.println("m = " + m);
        System.out.print("\r");

        // Ecuación para hallar n
        n = c - a - (3 * b);// n = c - a - 3b
        System.out.println("Ecuación para hallar n = " + "" + c + " - " + a + " - " + "3(" + b + ")");
        System.out.println("n = " + n);
        System.out.print("\r");

        // Ecuación para hallar z
        z = m + n + (5 * a);// z = m + n + 5a
        System.out.println("Ecuación para hallar z = " + "" + m + " + (" + n + ") + " + "5(" + a + ")");
        System.out.println("z = " + z);
        System.out.print("\r");

        // Salida
        System.out.println("El valor de m es: " + m);
        System.out.println("El valor de z es: " + z);
    }

}
