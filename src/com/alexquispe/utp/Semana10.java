package com.alexquispe.utp;

import java.util.Scanner;

public class Semana10 {

    public static void main(String[] args) {
        ejercicio05();
    }

    // While
    public static void ejercicio01() {
        Scanner scanner = new Scanner(System.in);

        double pesoManzanaMaximo = 0;
        double pesoPapayasTotal = 0;
        int cantidadPapayas = 0;
        int cantidadSandias = 0;
        int sandiasMenosDe2Punto5 = 0;

        System.out.println("Ingrese el peso de las frutas (0 para salir):");

        double pesoFruta = scanner.nextDouble();
        while (pesoFruta != 0) {
            if (pesoFruta > pesoManzanaMaximo && pesoFruta > 0) {
                pesoManzanaMaximo = pesoFruta;
            }

            if (pesoFruta > 0) {
                if (pesoFruta < 2.5) {
                    sandiasMenosDe2Punto5++;
                }
                cantidadSandias++;
            } else if (pesoFruta < 0) {
                pesoPapayasTotal += pesoFruta;
                cantidadPapayas++;
            }

            pesoFruta = scanner.nextDouble();
        }

        double promedioPapayas = cantidadPapayas > 0 ? pesoPapayasTotal / cantidadPapayas : 0;
        double porcentajeSandiasMenosDe2Punto5 = (double) sandiasMenosDe2Punto5 / cantidadSandias * 100;

        System.out.println("El peso máximo de la manzana es: " + pesoManzanaMaximo);
        System.out.println("El promedio de pesos de las papayas es: " + promedioPapayas);
        System.out.println("El porcentaje de sandías que pesaron menos de 2.5 kilogramos es: " + porcentajeSandiasMenosDe2Punto5 + "%");

        scanner.close();
    }

    public static void ejercicio04() {

        double salto;
        double mejorSalto = 0;
        String deportista = "";
        String mejorDeportista = "";

        int intentos;
        double miSalto;
        double miMejorSalto;

        Scanner dato = new Scanner(System.in);
        do {
            System.out.print("Ingresar el nombre del deportista: ");
            deportista = dato.next();

            intentos = 1;
            miMejorSalto = 0;

            while (intentos <= 5) {
                System.out.print("Ingresar el salto del deportista " + deportista + " - Intento #" + intentos + " es: ");
                miSalto = dato.nextDouble();

                if (miSalto > miMejorSalto) {
                    miMejorSalto = miSalto;
                }

                intentos++;
            }

            salto = miMejorSalto;


            System.out.println("Para " + deportista + " su mejor salto fue: " + salto);

            if (salto > mejorSalto) {
                mejorSalto = salto;
                mejorDeportista = deportista;
            }
        } while (salto > 5);

        System.out.println("El mejor deportista es: " + mejorDeportista + " y su salto es: " + mejorSalto);
    }

    public static void ejercicio05() {
        int totalProductos;
        int tipoProducto;
        int procProducto;
        double precioProducto;
        Scanner dato = new Scanner(System.in);

        double mayorPrecioProductoNac = 0;
        double menorPrecioProductoImp = 0;
        double totalGananciaComida = 0;
        double totalGananciaVestido = 0;

        System.out.print("Ingresar el total de productos: ");
        totalProductos = dato.nextInt();

        for (int i = 0; i < totalProductos; i++) {
            System.out.println("Selecciona el tipo de producto:");
            System.out.println("1: Comida");
            System.out.println("2: Cestido");
            tipoProducto = dato.nextInt();

            System.out.println("Selecciona el tipo de procedencia:");
            System.out.println("1: Nacional");
            System.out.println("2: Importado");
            procProducto = dato.nextInt();

            System.out.println("Ingresa el precio del producto:");
            precioProducto = dato.nextDouble();

            if (procProducto == 1) {
                if (precioProducto > mayorPrecioProductoNac) {
                    mayorPrecioProductoNac = precioProducto;
                }
            } else if (procProducto == 2) {
                if (precioProducto < menorPrecioProductoImp) {
                    menorPrecioProductoImp = precioProducto;
                }
            }

            if (tipoProducto == 1) {
                totalGananciaComida = totalGananciaComida + precioProducto;
            } else if (tipoProducto == 2) {
                totalGananciaVestido = totalGananciaVestido + precioProducto;

            }

        }

        System.out.println("El mayor precio producto nacional es: " + mayorPrecioProductoNac);
        System.out.println("El menor precio producto importados es: " + menorPrecioProductoImp);
        System.out.println("La ganancia total en comidas es: " + totalGananciaComida);
        System.out.println("La ganancia total en vestidos es: " + totalGananciaVestido);
    }

    public static void ejercicio06() {

    }
}
