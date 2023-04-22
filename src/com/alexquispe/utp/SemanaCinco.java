package com.alexquispe.utp;

import java.util.Scanner;

public class SemanaCinco {


    public static void main(String[] args) {
        ejercicio01();
    }

    /**
     * SANTA ELENA, una empresa de transporte tiene los siguientes precios por persona de Lima a:
     * <p>
     * ++++++++++++++++++++++++++++++++++++++
     * Destino                Precio (soles)
     * ++++++++++++++++++++++++++++++++++++++
     * Trujillo                 +   55
     * Madre de Dios            +   115
     * Arequipa                 +   70
     * Moquegua                 +   125
     * +++++++++++++++++++++++++++++++++++++++
     * <p>
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
}
