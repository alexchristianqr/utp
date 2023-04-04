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

        // Declarar variables
        dato = new Scanner(System.in);

        tarea05(); // Ejeuctar tarea 1
    }

    /**
     * Tarea 01 
     * Ingrese su nombre, edad, genero y talla desde el teclado, luego
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
     * Tarea 02 
     * Escriba un programa para ingresar 3 notas de un alumno, calcular
     * y mostrar su promedio, sabiendo que la tercera nota tiene peso 2.
     */
    public static void tarea02() {
        // Declarar variables
        double n1, n2, n3, promedio, pesoN3;

        // Inicializar variables
        System.out.println("Ingresar Nota #1");
        n1 = dato.nextDouble();
        System.out.println("Ingresar Nota #2");
        n2 = dato.nextDouble();
        System.out.println("Ingresar Nota #3");
        n3 = dato.nextDouble();

        // Proceso
        // Aplicar peso para la nota #3
        pesoN3 = n3 * 2;

        // Calcular nota promedio ponderado
        promedio = (n1 + n2 + pesoN3) / 3;

        // Salida
        System.out.println("El promedio es: " + promedio);
    }

    /**
     * Tarea 03 
     * Una tienda ofrece un descuento del 5% del monto de la compra,
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
     * Tarea 04 
     * Desarrolle un programa que permita calcular el porcentaje de
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
     * Tarea 05 
     * a = b + 2c + 3m 
     * c = a + 3b + n 
     * z = m + n + 5a
     */
    public static void tarea05() {
        // Declarar variables
        double a, b, c, m, n, z;

        // Inicializar variables
        System.out.println("Ingresar el valor de a");
        a = dato.nextDouble();
        System.out.println("Ingresar el valor de b");
        b = dato.nextDouble();
        System.out.println("Ingresar el valor de c");
        c = dato.nextDouble();

        // Proceso 
        // Ecuacion para hallar m
        m = (a - b - (2*c)) / 3;// m = (b + 2c - a)/3
        
        // Ecuación para hallar n
        n = c - a - (3*b);// n = c - a - 3b
        
        // Ecuación para hallar z
        z = m + n + (5*a);// z = m + n + 5a
       

        // Salida
        System.out.println("El valor de m es: " + m);
        System.out.println("El valor de z es: " + z);
    }
}
