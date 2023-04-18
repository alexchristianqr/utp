/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.alexquispe.utp;

import java.util.Scanner;

/**
 * @author LAB-USR-AQ265-A0303
 */
public class SemanaCuatro {
    // Declarar como una variable global a nivel de clase
    static Scanner dato;


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // Instanciar o crear objeto
        dato = new Scanner(System.in);

        maquinaDispensadoraUTP();
    }

    static void ejercicio01() {
        // D
        int dia;
        String nombreDia;

        // I
        System.out.println("Ingresa un dia de la semana:");
        System.out.println("----------------------------");
        System.out.println("1: Lunes");
        System.out.println("2: Martes");
        System.out.println("3: Miercoles");
        System.out.println("4: Jueves");
        System.out.println("5: Viernes");
        System.out.println("6: Sabado");
        System.out.println("7: Domingo");
        System.out.print("\n");
        dia = dato.nextInt();

        // P
        switch (dia) {
            case 1:
                nombreDia = "El dia es LUNES";
                break;
            case 2:
                nombreDia = "El dia es MARTES";
                break;
            case 3:
                nombreDia = "El dia es MIERCOLES";
                break;
            case 4:
                nombreDia = "El dia es JUEVES";
                break;
            case 5:
                nombreDia = "El dia es VIERNES";
                break;
            case 6:
                nombreDia = "El dia es SABADO";
                break;
            case 7:
                nombreDia = "El dia es DOMINGO";
                break;
            default:
                nombreDia = "No existe ese dia de la semana";
        }

        // S
        System.out.println("Rpta: " + nombreDia);


    }

    static void ejercicio02() {
        // D
        int num;
        String mensaje;

        // I
        System.out.println("Ingresa un numero de 0 a 9:");
        System.out.println("----------------------------");
        System.out.print("\n");
        num = dato.nextInt();

        // P
        if (num < 0 || num > 9) {
            mensaje = "No es un numero valido";
        } else {
            switch (num) {
                case 0:
                case 1:
                case 3:
                case 5:
                case 7:
                case 9:
                    mensaje = "Es impar";
                    break;
                default:
                    mensaje = "Es par";
            }
        }

        // S
        System.out.println("Rpta: " + mensaje);
    }

    /**
     * Ejercicio 1. Construir un programa que simule el funcionamiento
     * de una calculadora que pueda realizar las cuatro operaciones
     * aritméticas básicas (suma, resta, producto y división) al
     * ingresar 2 números enteros.
     */
    static void ejercicio03() {
        // D
        int opcion;
        double num1, num2, resultado;
        String mensaje;
        Scanner dato = new Scanner(System.in);

        // I
        System.out.println("---------------------");
        System.out.println("Mi calculadora básica");
        System.out.println("---------------------");
        System.out.println("Seleccionar una opcion:");
        System.out.println("1: Sumar");
        System.out.println("2: Restar");
        System.out.println("3: Multiplicar");
        System.out.println("4: Dividir");
        opcion = dato.nextInt();
        System.out.print("\n");
        System.out.println("Ingresar N° 1:");
        num1 = dato.nextDouble();
        System.out.println("Ingresar N° 2:");
        num2 = dato.nextDouble();
        System.out.print("\n");

        // P
        switch (opcion) {
            case 1:
                resultado = num1 + num2;
                break;
            case 2:
                resultado = num1 - num2;
                break;
            case 3:
                resultado = num1 * num2;
                break;
            case 4:
                resultado = num1 / num2;
                break;
            default:
                System.out.println("La opcion ingresada no es valida");
                return;
        }

        // S
        mensaje = "El resultado es " + resultado;
        System.out.println("Rpta: " + mensaje);
    }

    /**
     * Una tienda automotriz ofrece la venta de autos al crédito, de esta manera el
     * financiamiento se realiza de tres formas como lo indica la siguiente tabla:
     * Tipo de financiamiento Cuota inicial
     * 1 10%
     * 2 15%
     * 3 20%
     * Calcular el monto de la cuota inicial y el monto que va a financiar.
     */
    static void ejercicio04() {
        // D
        int opcion;
        double cuotaInicial, porcentaje = 0, montoFinanciamiento = 0;
        Scanner dato = new Scanner(System.in);

        // IP
        System.out.println("--------------");
        System.out.println("Venta de autos");
        System.out.println("--------------");
        System.out.println("Seleccionar tipo de financiamiento:");
        System.out.println("1: al 10%");
        System.out.println("2: al 15%");
        System.out.println("3: al 20%");
        opcion = dato.nextInt();
        System.out.print("\n");

        // Evaluar opción
        switch (opcion) {
            case 1:
                porcentaje = 0.10;
                break;
            case 2:
                porcentaje = 0.15;
                break;
            case 3:
                porcentaje = 0.20;
                break;
            default:
                System.out.println("La opcion ingresada no es valida");
                return;

        }

        System.out.println("Ingresar el monto a financiar:");
        montoFinanciamiento = dato.nextDouble();
        System.out.print("\n");

        // Calcular cuota
        cuotaInicial = montoFinanciamiento * porcentaje;
        montoFinanciamiento = montoFinanciamiento + cuotaInicial;

        // S
        System.out.println("Rpta: El monto total del financiamiento al " + porcentaje * 100 + "% es: $" + montoFinanciamiento);
        System.out.println("Rpta: La cuota inicial es: $" + cuotaInicial);
    }

    static void maquinaDispensadoraUTP() {
        // D
        int tipoPago, tipoProducto;
        double saldo, precioProducto, vuelto;
        boolean permisoParaComprar = false;
        Scanner dato = new Scanner(System.in);

        // I
        System.out.println("-------------------------------------");
        System.out.println("Maquina dispensadora de productos UTP");
        System.out.println("-------------------------------------");
        System.out.println("Selecionar tipo de pago:");
        System.out.println("1: monedas");
        System.out.println("2: billete");
        tipoPago = dato.nextInt();
        System.out.println("Ingresar saldo:");
        saldo = dato.nextDouble();
        System.out.print("\n");

        switch (tipoPago) {
            case 1:
                // Validar que solo acepte monedas
                if (saldo == 1 || saldo == 2 || saldo == 5) {
                    permisoParaComprar = true;
                } else {
                    System.out.println("No es una moneda");
                }
                break;
            case 2:
                // Validar que solo acepte billetes
                if (saldo == 10 || saldo == 20 || saldo == 50 || saldo == 100 || saldo == 200) {
                    permisoParaComprar = true;
                } else {
                    System.out.println("No es un billete");
                }
                break;
            default:
                System.out.println("No es una opcion valida");
                return;
        }

        // PS
        // Validar permiso para comprar productos
        if (permisoParaComprar) {
            System.out.println("Selecionar un producto:");
            System.out.println("1: Galleta a $1.50");
            System.out.println("2: Gaseosa a $2.50");
            System.out.println("3: Chocolate a $1.70");
            System.out.println("4: Caramelo a $0.50");
            System.out.println("5: Cerveza a $10.00");
            tipoProducto = dato.nextInt();
            System.out.print("\n");

            switch (tipoProducto) {
                case 1:
                    precioProducto = 1.50;
                    break;
                case 2:
                    precioProducto = 2.50;
                    break;
                case 3:
                    precioProducto = 1.70;
                    break;
                case 4:
                    precioProducto = 0.50;
                    break;
                case 5:
                    precioProducto = 10;
                    break;
                default:
                    System.out.println("No es una opcion valida");
                    return;
            }

            // Validar saldo disponible para comprar un producto
            if (saldo < precioProducto) {
                System.out.println("El saldo es insuficiente");
                return;
            }

            // Calcular
            vuelto = saldo - precioProducto;
            System.out.println("El monto total a pagar es: $" + precioProducto);
            System.out.println("Tu saldo restante es: $" + vuelto);
        }
    }
}
