package com.alexquispe.utp;

import java.util.Scanner;

public class Semana10 {

    public static void main(String[] args) {
        ejercicio07();
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
        String mejorAlumnoPHP = "";
        String mejorAlumnoAndroid = "";
        double mejorNotaPHP = 0;
        double mejorNotaAndroid = 0;

        int cantidadAlumnos = 0;
        int cursoElectivo;
        double nota = 0;
        String alumno = "";

        Scanner dato = new Scanner(System.in);
        System.out.println("Ingrese una cantidad de Estudiantes:");
        cantidadAlumnos = dato.nextInt();

        for (int i = 1; i <= cantidadAlumnos; i++) {

            System.out.println("Ingresar nombre estudiante:");
            alumno = dato.next();

            System.out.println("Seleccionar curso electivo:");
            System.out.println("1: PHP");
            System.out.println("2: Android");
            cursoElectivo = dato.nextInt();

            do {
                if (nota < 0 || nota > 20) {
                    System.out.println("La nota ingresada es inválida!!!");
                }
                System.out.println("Ingresar nota:");
                nota = dato.nextDouble();
            } while (nota < 0 || nota > 20);

            if (cursoElectivo == 1) {
                if (nota > mejorNotaPHP) {
                    mejorNotaPHP = nota;
                    mejorAlumnoPHP = alumno;
                }
            } else if (cursoElectivo == 2) {
                if (nota > mejorNotaAndroid) {
                    mejorNotaAndroid = nota;
                    mejorAlumnoAndroid = alumno;
                }
            }

        }
        System.out.println("Alumno " + mejorAlumnoPHP + " con mejor nota en PHP: " + mejorNotaPHP);
        System.out.println("Alumno " + mejorAlumnoAndroid + " con mejor nota en Android: " + mejorNotaAndroid);
    }

    public static void ejercicio07() {
        int[] notas;
        int tamano;
        int totalDeficientes = 0;
        int totalIrregulares = 0;
        int totalRegulares = 0;
        int totalBuenos = 0;
        int totalExcelentes = 0;

        Scanner dato = new Scanner(System.in);
        System.out.println("Ingresa una cantidad de alumnos:");
        tamano = dato.nextInt();

        notas = new int[tamano];

        for (int i = 0; i < notas.length; i++) {
            //numeros[i] = (int) (Math.random() * 10+1);// De 1-9
            //numeros[i] = (int) (Math.random() * 10+0);// De 0-9
            notas[i] = (int) (Math.random() * 21 + 0);// De 0-20
            System.out.println("La nota es: " + notas[i]);

            if (notas[i] >= 0 && notas[i] <= 5) {
                totalDeficientes++;
            } else {
                if (notas[i] >= 6 && notas[i] <= 11) {
                    totalIrregulares++;
                } else {
                    if (notas[i] >= 12 && notas[i] <= 14) {
                        totalRegulares++;
                    } else {
                        if (notas[i] >= 15 && notas[i] <= 17) {
                            totalBuenos++;
                        } else {
                            if (notas[i] >= 18 && notas[i] <= 20) {
                                totalExcelentes++;
                            }
                        }
                    }
                }
            }
        }

        System.out.println("El total de alumnos Deficientes es:" + totalDeficientes);
        System.out.println("El total de alumnos Irregulares es:" + totalIrregulares);
        System.out.println("El total de alumnos Regulares es:" + totalRegulares);
        System.out.println("El total de alumnos Buenos es:" + totalBuenos);
        System.out.println("El total de alumnos Excelentes es:" + totalExcelentes);
    }

    // Practica calificada 02
    public static void pregunta01() {

        String continuar = "S";
        double dato;
        int max;
        int index;
        int condicion;
        double nota = 0;
        int totalIngresados = 0, totalInterno = 0, totalExterno = 0, totalAlumnos = 0;
        double totalAprob = 0, totalDesap = 0;

        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingresar alumno nuevo S/N: ");
        continuar = scanner.next();

        // PS
        while (continuar.equalsIgnoreCase("S")) {

            totalAlumnos++;
            
            System.out.println("Condicion del alumno");
            System.out.println("1: Ingresante");
            System.out.println("2: Traslado Interno");
            System.out.println("3: Traslado Externo");
            condicion = scanner.nextInt();

            do {
                if (nota < 0 || nota > 20) {
                    System.out.println("La nota ingresada es inválida!!!");
                }
                System.out.println("Ingresar nota del alumno:");
                nota = scanner.nextDouble();
            } while (nota < 0 || nota > 20);

            if (condicion == 1) {
                totalIngresados++;
            } else {
                if (condicion == 2) {
                    totalInterno++;
                } else {
                    if (condicion == 3) {
                        totalExterno++;
                    }
                }
            }

            

            if (nota >= 11) {
                totalAprob++;
            } else {
                totalDesap++;
            }

            System.out.print("Ingresar alumno nuevo S/N: ");
            continuar = scanner.next();

        }

        System.out.println("Total alumnos ingresados: " + totalIngresados);
        System.out.println("Total alumnos internos: " + totalInterno);
        System.out.println("Total alumnos externos: " + totalExterno);
        System.out.println("Promedio aprobados: " + totalAlumnos / totalAprob);
        System.out.println("Promedio desaprobados: " + totalAlumnos / totalDesap);
    }

    public static void pregunta02() {

        double peso;
        int condicion;
        int menores50 = 0, entre55y70 = 0, mayores70 = 0;
        double mayorPesoNacional = 0, menorPesoNacionalizado = 0;
        Scanner dato = new Scanner(System.in);

        do {
            System.out.println("Ingrese la condicion del deportista:");
            System.out.println("1: Nacional");
            System.out.println("2: Nacionalizado");
            condicion = dato.nextInt();

            System.out.println("Ingrese el peso:");
            peso = dato.nextDouble();

            if (peso < 55) {
                menores50++;
            } else {
                if (peso >= 55 && peso <= 70) {
                    entre55y70++;
                } else {
                    if (peso > 70) {
                        mayores70++;
                    }
                }
            }

            if (condicion == 1) {
                if (peso > mayorPesoNacional) {
                    mayorPesoNacional = peso;
                }
            } else if (condicion == 2) {
                if (peso < menorPesoNacionalizado) {
                    menorPesoNacionalizado = peso;
                }
            }

        } while (peso >=0);

        System.out.println("Deportistas < 55kg: " + menores50);
        System.out.println("Deportistas entre 55 y 70kg: " + entre55y70);
        System.out.println("Deportistas maypres de 70kg: " + mayores70);
        System.out.println("Mayor peso nacional: " + mayorPesoNacional);
        System.out.println("Menor peso nacionalizado: " + menorPesoNacionalizado);
    }


}
