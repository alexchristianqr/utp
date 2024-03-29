package utp.edu.pe.taller.Semana08;

import java.util.Scanner;

public class Semana08 {

    public static void main(String[] args) {
        ejercicio01();
    }

    public static void ejercicio01() {
        // DI
        Scanner dato = new Scanner(System.in);
        int totalclientes = 0;
        double ingresototal = 0, totalmonto = 0, totaldesc = 0, monto;
        String ingreso;

        // P
        do {
            System.out.print("¿Desea registrar un cliente? (S/N): ");
            ingreso = dato.next();

            if (ingreso.equalsIgnoreCase("S")) {
                System.out.print("Ingrese el monto de la compra: ");
                monto = dato.nextDouble();

                if (monto >= 0 && monto <= 50) {
                    totaldesc = monto * 0.0725;// 7.25%
                } else {
                    if (monto >= 51 && monto <= 100) {
                        totaldesc = monto * 0.065;// 6.5%
                    } else {
                        if (monto >= 101 && monto <= 150) {
                            totaldesc = monto * 0.0475;// 4.75%
                        } else {
                            if (monto >= 151) {
                                totaldesc = monto * 0.025;// 2.5%
                            }
                        }
                    }
                }

                totalclientes++;
                totalmonto = monto - totaldesc;
                ingresototal = ingresototal + totalmonto;
                System.out.println("El descuento del cliente #" + totalclientes + " es: $" + totaldesc);
                System.out.println("El monto final a pagar del cliente #" + totalclientes + " es: $" + totalmonto);
                System.out.print("\n");

            }
        } while (ingreso.equalsIgnoreCase("S"));

        // S
        System.out.print("\n");
        System.out.println("El ingreso total de ventas es: $" + ingresototal);
        System.out.println("El numero total de clientes atendidos es: " + totalclientes);
    }

    public static void ejercicio02() {
        // DI
        int totalPostulantes = 0, totalRango1 = 0, totalRango2 = 0, totalRango3 = 0, puntos;
        Scanner scanner = new Scanner(System.in);
        String registrarPostulante;
        System.out.println("Desea registrar un postulante S/N");
        registrarPostulante = scanner.next();

        // P
        while (registrarPostulante.equalsIgnoreCase("S")) {
            System.out.println("Ingresa la cantidad de puntos para este postulante:");
            puntos = scanner.nextInt();
            if (puntos >= 0 && puntos <= 49) {
                totalRango1 = totalRango1 + 1;
            } else {
                if (puntos >= 50 && puntos <= 79) {
                    totalRango2 = totalRango2 + 1;
                } else {
                    if (puntos >= 80 && puntos <= 100) {
                        totalRango3 = totalRango3 + 1;
                    }
                }
            }

            totalPostulantes++;

            System.out.println("Desea registrar un postulante S/N");
            registrarPostulante = scanner.next();
        }

        // S
        System.out.println("La cantidad de postulantes es: " + totalPostulantes);
        System.out.println("Total rango 0-49 es: " + totalRango1);
        System.out.println("Total rango 50-79 es: " + totalRango2);
        System.out.println("Total rango 80-100 es: " + totalRango3);
    }

    public static void ejercicio03() {
        // DI
        int cantAtletas = 0, cantSaltos, sumaSaltos = 0, totalCantSaltos = 0, contarSaltos = 0, promedioSalto = 0;
        String seguirIntentando = "S";
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingresa una cantidad atletas");
        cantAtletas = scanner.nextInt();

        // P
        // Iterar por cada atleta
        for (int i = 1; i <= cantAtletas; i++) {
            System.out.println("Ingresa una cantidad de saltos para el atleta #" + i);
            cantSaltos = scanner.nextInt();
            totalCantSaltos = totalCantSaltos + cantAtletas;

            System.out.println("Seguir intentando el ingreso de saltos para el atleta #" + i + " S/N");
            seguirIntentando = scanner.next();

            contarSaltos++;

            // Seguir intentando los saltos
            while (seguirIntentando.equalsIgnoreCase("S")) {
                System.out.println("Ingresa una cantidad de saltos para el atleta #" + i);
                cantSaltos = scanner.nextInt();
                totalCantSaltos = totalCantSaltos + cantAtletas;

                System.out.println("Seguir intentando el ingreso de saltos para el atleta #" + i + " S/N");
                seguirIntentando = scanner.next();

                contarSaltos++;
            }

            sumaSaltos = sumaSaltos + totalCantSaltos;
            promedioSalto = sumaSaltos / contarSaltos;
        }

        // S
        System.out.println("el promedio es: " + promedioSalto);
        System.out.println("La cantidad saltos es: " + sumaSaltos);
    }

    public static void ejercicio04() {
        // D
        int nump, edad;
        double pago, dsctoPorcentaje = 0, totalDscto = 0, totalPago = 0;
        Scanner scanner = new Scanner(System.in);

        // I
        System.out.println("Ingrese N personas:");
        nump = scanner.nextInt();

        // PS
        for (int i = 1; i <= nump; i++) {
            System.out.println("Ingrese edad de persona #" + i);
            edad = scanner.nextInt();

            if (edad < 10) {
                continue;
            }

            System.out.println("Ingrese pago de persona #" + i);
            pago = scanner.nextInt();

            if (edad >= 10 && edad <= 30) {
                dsctoPorcentaje = 0.02;// 2%
            } else {
                if (edad >= 31 && edad <= 50) {
                    dsctoPorcentaje = 0.03;// 3%
                } else {
                    if (edad >= 51) {
                        dsctoPorcentaje = 0.05;// 5%
                    } else {

                    }
                }
            }

            totalDscto = pago * dsctoPorcentaje;
            totalPago = pago - totalDscto;

            System.out.println("EL descuento es: " + totalDscto);
            System.out.println("EL pago con descuento es: " + totalPago);
        }
    }
}
