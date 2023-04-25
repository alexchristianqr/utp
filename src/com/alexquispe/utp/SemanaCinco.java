package com.alexquispe.utp;

import java.util.Scanner;

public class SemanaCinco {


    public static void main(String[] args) {
        ejercicio02();
    }

    /**
     * SANTA ELENA, una empresa de transporte tiene los siguientes precios por persona de Lima a:
     * ++++++++++++++++++++++++++++++++++++++
     * Destino                Precio (soles)
     * ++++++++++++++++++++++++++++++++++++++
     * Trujillo                 +   55
     * Madre de Dios            +   115
     * Arequipa                 +   70
     * Moquegua                 +   125
     * +++++++++++++++++++++++++++++++++++++++
     * Tener en cuenta que, si el destino no es ninguno de ellos, el precio debe ser 0.
     * Además recibe un descuento promocional del 18.5% si la persona tiene entre 25 y 35 años de edad.
     * Mostrar el precio final a pagar por persona usando IF y ELSE (IF anidados)
     */
    public static void ejercicio01() {
        // D
        int destino, edad;
        double precioPasajeInicial, precioPasajeFinal, precioDscto, valorPjeDscto;
        Scanner scanner = new Scanner(System.in);

        // I
        precioDscto = 0;
        valorPjeDscto = 0.185;// 18.5%
        System.out.println("-- Tours SANTA ELENA --");
        System.out.println("-----------------------");
        System.out.println("Ingresa tu edad:");
        edad = scanner.nextInt();
        System.out.println("Nota: La promoción del 18.5% de descuento aplica solo para el rango de 25-35 años de edad.");
        System.out.println("Selecciona un destino:");
        System.out.println("1: Trujillo a $55");
        System.out.println("2: Madre de Dios a $115");
        System.out.println("3: Arequipa a $70");
        System.out.println("4: Moquegua a $125");
        destino = scanner.nextInt();

        // P
        // Validar el rango de edad que le correponde el descuento
        if (edad >= 25 && edad <= 35) {
            switch (destino) {
                case 1:
                    precioPasajeInicial = 55;
                    precioDscto = precioPasajeInicial * valorPjeDscto;
                    precioPasajeFinal = precioPasajeInicial - precioDscto;
                    break;
                case 2:
                    precioPasajeInicial = 115;
                    precioDscto = precioPasajeInicial * valorPjeDscto;
                    precioPasajeFinal = precioPasajeInicial - precioDscto;
                    break;
                case 3:
                    precioPasajeInicial = 70;
                    precioDscto = precioPasajeInicial * valorPjeDscto;
                    precioPasajeFinal = precioPasajeInicial - precioDscto;
                    break;
                case 4:
                    precioPasajeInicial = 125;
                    precioDscto = precioPasajeInicial * valorPjeDscto;
                    precioPasajeFinal = precioPasajeInicial - precioDscto;
                    break;
                default:
                    precioPasajeFinal = 0;
            }
        } else {
            precioDscto = 0;
            switch (destino) {
                case 1:
                    precioPasajeInicial = 55;
                    precioPasajeFinal = precioPasajeInicial - precioDscto;
                    break;
                case 2:
                    precioPasajeInicial = 115;
                    precioPasajeFinal = precioPasajeInicial - precioDscto;
                    break;
                case 3:
                    precioPasajeInicial = 70;
                    precioPasajeFinal = precioPasajeInicial - precioDscto;
                    break;
                case 4:
                    precioPasajeInicial = 125;
                    precioPasajeFinal = precioPasajeInicial - precioDscto;
                    break;
                default:
                    precioPasajeFinal = 0;
            }
        }

        // S
        System.out.println("Tu edad es: " + edad + " años");
        System.out.println("El precio final del pasaje es: $" + precioPasajeFinal);
        System.out.println("Descuento promocional del " + valorPjeDscto * 100 + "% aplicado es: -$" + precioDscto);
    }

    public static void ejercicio02() {
        // D
        int tipoVehiculo, diasInternado;
        double costoDiario, costoFinal, valorDscto, totalDscto;

        // I
        valorDscto = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("-- DEPOSITO VEHICULAR --");
        System.out.println("------------------------");
        System.out.println("Selecciona un tipo de vehiculo:");
        System.out.println("1: Moto");
        System.out.println("2: Auto");
        System.out.println("3: Camioneta");
        System.out.println("4: Camion");
        tipoVehiculo = scanner.nextInt();
        System.out.println("Ingrese la cantidad de dias de internamiento:");
        diasInternado = scanner.nextInt();

        // P
        switch (tipoVehiculo) {
            case 1:
                costoDiario = 27.5;
                break;
            case 2:
                costoDiario = 35.5;
                break;
            case 3:
                costoDiario = 60.5;
                break;
            case 4:
                costoDiario = 75.5;
                break;
            default:
                System.out.println("No existe este tipo de vehiculo");
                return;
        }

        if (diasInternado >= 1 && diasInternado <= 5) {
            valorDscto = 0.055; // 5.50%
        } else {
            if (diasInternado >= 6 && diasInternado <= 10) {
                valorDscto = 0.085;// 8.50%
            } else {
                if (diasInternado > 10) {
                    valorDscto = 0.1075;//10.75%
                }
            }
        }

        // Calcular descuento
        totalDscto = costoDiario * valorDscto;
        costoFinal = costoDiario - totalDscto;

        // S
        System.out.println("El costo final es: " + costoFinal);
    }
    
    public static void ejercicio03() {
        // D
        int numeroRollo75 = 75;
        int numeroRollo300 = 300;
        int numeroRollo500 = 500;
        int totalRollos = (numeroRollo75 + numeroRollo300 + numeroRollo500);
        double cantidadAlambre;

        // I
        Scanner scanner = new Scanner(System.in);
        System.out.println("-- DISTRIBUIDOR --");
        System.out.println("Ingrese la cantidad de metros de alambre");
        cantidadAlambre = scanner.nextDouble();

        // PS 75, 300 y 500
        if (cantidadAlambre > 1 && cantidadAlambre <= numeroRollo75) {
            System.out.println("a: Se vendio " + cantidadAlambre + " m");
        } else {
            if (cantidadAlambre > numeroRollo75 && cantidadAlambre <= numeroRollo300) {
                System.out.println("b: Se vendio " + cantidadAlambre + " m");
            } else {
                if (cantidadAlambre > numeroRollo300 && cantidadAlambre <= numeroRollo500) {
                    System.out.println("c: Se vendio " + cantidadAlambre + " m");
                } else {
                    if (cantidadAlambre <= totalRollos) {
                        System.out.println("d: Se vendio " + cantidadAlambre + " m");
                    } else {
                        System.out.println("No alcanza metraje de rollos de alambre");
                    }
                }
            }
        }
    }
}
