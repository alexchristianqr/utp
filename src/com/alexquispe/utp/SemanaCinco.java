package com.alexquispe.utp;

import java.util.Scanner;

public class SemanaCinco {

    public static void main(String[] args) {
        pregunta04();
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
        int rolloN75 = 75, rolloN300 = 300, rolloN500 = 500;
        int totalRollos500, totalRollos300, totalRollos75;
        int cantidadAlambre, restoRollo = 0;

        // I
        Scanner scanner = new Scanner(System.in);
        System.out.println("-- DISTRIBUIDOR FERRETERO --");
        System.out.println("Ingrese la cantidad de metros de alambre");
        cantidadAlambre = scanner.nextInt();

        // PS 75, 300 y 500
        totalRollos500 = cantidadAlambre / rolloN500;
        restoRollo = cantidadAlambre % rolloN500;

        totalRollos300 = restoRollo / rolloN300;
        restoRollo = restoRollo % rolloN300;

        totalRollos75 = restoRollo / rolloN75;
        restoRollo = restoRollo % rolloN75;

        System.out.println("El total de 500m es: " + totalRollos500);
        System.out.println("El total de 300m es: " + totalRollos300);
        System.out.println("El total de 75m es: " + totalRollos75);
        System.out.println("El total de metros restante es: " + restoRollo);

    }

    public static void ejercicio04() {
        // D
        int tipoMascota, cantidadMascotas;
        double costoMascota, costoTotal, costoFinal, valorDscto, totalDscto;

        // I
        totalDscto = 0;
        valorDscto = 1;
        Scanner scanner = new Scanner(System.in);
        System.out.println("-- VETERINARIA PETSHOP LIMA --");
        System.out.println("------------------------");
        System.out.println("Selecciona un tipo de mascota:");
        System.out.println("1: Perro a S/45.50");
        System.out.println("2: Gato a S/28.50");
        System.out.println("3: Conejo a S/20.50");
        System.out.println("4: Otro a S/85.50");
        tipoMascota = scanner.nextInt();
        System.out.println("Ingrese la cantidad de mascotas:");
        cantidadMascotas = scanner.nextInt();

        // P
        switch (tipoMascota) {
            case 1:
                costoMascota = 45.50;
                break;
            case 2:
                costoMascota = 28.5;
                break;
            case 3:
                costoMascota = 20.50;
                break;
            default:
                costoMascota = 85.5;
                break;
        }

        costoTotal = (costoMascota * cantidadMascotas);

        if (cantidadMascotas > 4) {
            valorDscto = 0.225; // 22.5%
            totalDscto = (costoTotal * valorDscto);
        }

        // Calcular descuento
        costoFinal = (costoTotal - totalDscto) / 3.823; // Soles a USD

        // S
        System.out.println("El costo final en USD es: $" + Math.round(costoFinal * 100.0) / 100.0);
        System.out.println("El descuento en USD es: $" + Math.round((totalDscto / 3.823) * 100.0) / 100.0);
    }

    // Practica Calificada 01

    public static void pregunta01() {
        // D
        int color;
        double dscto = 0, totalDscto = 0, totalCompra = 0, totalCompraUSD = 0, montoCompra = 0;

        // I
        Scanner scanner = new Scanner(System.in);
        System.out.println("-- MARKET ZAGA --");
        System.out.println("Ingrese un color");
        System.out.println("1: Azul");
        System.out.println("2: Amarillo");
        System.out.println("3: Verde");
        System.out.println("4: Naranja");
        System.out.println("5: Rojo");
        color = scanner.nextInt();

        // P
        switch (color) {
            case 1:// azul
                dscto = 0.125;// 12.5%
                break;
            case 2:// amarillo
                dscto = 0.45;// 45%
                break;
            case 3:// verde
                dscto = 0.255;// 25.5%
                break;
            case 4:// naranja
                dscto = 0.355;// 35.5%
                break;

            case 5:// rojo
                dscto = 0;// 0%
                break;
            default:
                System.out.println("No se admite este tipo de color");
                return;
        }

        System.out.println("Ingresar monto");
        montoCompra = scanner.nextDouble();

        totalDscto = montoCompra * dscto;
        totalCompra = montoCompra - totalDscto;
        totalCompraUSD = (totalCompra / 3.60);// a USD

        // S
        System.out.println("El cliente debera pagar");
        System.out.println("Monto en USD: " + totalCompraUSD);
        System.out.println("Monto en Soles: " + totalCompra);
    }

    public static void pregunta02() {
        // D
        int cantidadPanetones, marca;
        double precioUnit = 0, dscto = 0, totalDscto = 0, totalCompra = 0, montoCompra = 0;

        // I
        Scanner scanner = new Scanner(System.in);
        System.out.println("-- CAMPAÑA NAVIDEÑA --");
        System.out.println("Ingrese una marca de Paneton:");
        System.out.println("1: Costa");
        System.out.println("2: Bimbo");
        System.out.println("3: Todinno");
        System.out.println("4: Motta");
        System.out.println("5: Union");
        marca = scanner.nextInt();
        System.out.println("Ingresar una cantidad de panetones a comprar:");
        cantidadPanetones = scanner.nextInt();

        // P
        switch (marca) {
            case 1:
                precioUnit = 15;
                break;
            case 2:
                precioUnit = 17;
                break;
            case 3:
                precioUnit = 20;
                break;
            case 4:
                precioUnit = 19;
                break;

            case 5:
                precioUnit = 16;
                break;
            default:
                System.out.println("No se vende este paneton");
                return;
        }

        montoCompra = precioUnit * cantidadPanetones;

        if (cantidadPanetones > 13) {
            dscto = 0.1875;// 18.75%
            totalDscto = montoCompra * dscto;
        }

        totalCompra = montoCompra - totalDscto;
        totalCompra = Math.round(totalCompra * 100.0) / 100.0;

        // S
        System.out.println("El monto total a pagar en soles es: S/" + totalCompra);
    }

    public static void pregunta03() {
        // D
        int rollo375 = 375, rollo120 = 120, rollo25 = 25, sobranteRollo, cantidadAlambres;
        int totalRollo375, totalRollo120, totalRollo25;
        double montoCompra = 0, totalCompra = 0, totalDscto = 0, dscto = 0;

        // I
        Scanner scanner = new Scanner(System.in);
        System.out.println("-- ELECTRO SAC --");
        System.out.println("Ingrese una longitud de metros de alambre:");
        cantidadAlambres = scanner.nextInt();

        // P
        // Para 375
        totalRollo375 = cantidadAlambres / rollo375;
        sobranteRollo = cantidadAlambres % rollo375;

        // Para 120
        totalRollo120 = sobranteRollo / rollo120;
        sobranteRollo = sobranteRollo % rollo120;

        // Para 25
        totalRollo25 = sobranteRollo / rollo25;
        sobranteRollo = sobranteRollo % rollo25;

        montoCompra = cantidadAlambres * 3.25;

        if (sobranteRollo > 3) {
            dscto = 0.10;
            totalDscto = montoCompra * dscto;
        }

        totalCompra = montoCompra - totalDscto;
        totalCompra = Math.round(totalCompra * 100.0) / 100.0;

        // S
        System.out.println("Total rollos 375: " + totalRollo375);
        System.out.println("Total rollos 120: " + totalRollo120);
        System.out.println("Total rollos 25: " + totalRollo25);
        System.out.println("Total sobrantes: " + sobranteRollo);
        System.out.println("El monto total a pagar en soles es: S/" + totalCompra);
    }

    public static void pregunta04() {
        // D
        double salarioTrab;
        int aniosAntig;
        double aumento = 0, bonif = 0, sueldoNeto, totalAumento;

        // I
        Scanner scanner = new Scanner(System.in);
        System.out.println("-- Summa Training --");
        System.out.println("Ingrese su salario contratado:");
        salarioTrab = scanner.nextDouble();
        System.out.println("Ingrese sus anios de antiguedad:");
        aniosAntig = scanner.nextInt();

        // P
        if (salarioTrab > 0 && salarioTrab <= 1350) {
            aumento = 0.24;// 24%
        } else {
            if (salarioTrab >= 1351 && salarioTrab <= 2800) {
                aumento = 0.16;// 16%
            } else {
                if (salarioTrab >= 2801 && salarioTrab <= 4000) {
                    aumento = 0.08;// 8%
                } else {
                    if (salarioTrab >= 4001) {
                        aumento = 0.03;// 3%
                    }
                }
            }
        }

        if (aniosAntig >= 1 && aniosAntig <= 5) {
            bonif = 135;
        } else {
            if (aniosAntig >= 6) {
                bonif = 185;
            }
        }

        totalAumento = (salarioTrab * aumento);
        sueldoNeto = (salarioTrab + totalAumento + bonif);

        // S
        System.out.println("Aumento: S/" + totalAumento);
        System.out.println("Bonificacion por anios: S/" + bonif);
        System.out.println("El salario neto del trabajador en soles es: S/" + sueldoNeto);
    }
}
