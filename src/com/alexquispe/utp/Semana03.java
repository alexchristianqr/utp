package com.alexquispe.utp;

import javax.swing.*;
import java.util.Scanner;

public class Semana03 {

    // Declarar como una variable global a nivel de clase
    static Scanner dato;

    // Ejecutar programa principal
    public static void main(String[] args) {

        // Instanciar o crear objeto
        dato = new Scanner(System.in);

        // Ejecutar método
        ejercicio13();
    }

    // ESTRUCTURA CONDICIONAL SIMPLE

    public static void ejercicio01() {
        // D
        int horasTrabajadas, horasTrabajadasExtra;
        double salarioSemanal, salarioJornal, salarioExtra, precioHora, precioHoraExtra;

        // I
        precioHora = 38;
        precioHoraExtra = 43;
        salarioSemanal = 0;

        System.out.println("Ingresar horas trabajadas:");
        horasTrabajadas = dato.nextInt();

        // P
        System.out.println("Horas trabajadas: " + horasTrabajadas);
        if (horasTrabajadas <= 40) {

            salarioSemanal = horasTrabajadas * precioHora;
            System.out.println("Salario jornal (" + horasTrabajadas + " * " + precioHora + ") : " + salarioSemanal);

        } else {
            if (horasTrabajadas > 40) {

                horasTrabajadasExtra = horasTrabajadas - 40;// Diferencia de horas
                salarioExtra = horasTrabajadasExtra * precioHoraExtra;// Saldo por horas extras
                salarioJornal = 40 * precioHora; // Saldo por 40 horas
                System.out.println("Salario jornal (40 * " + precioHora + ") : " + salarioJornal);
                System.out.println("Horas trabajadas extra: " + horasTrabajadasExtra);
                System.out.println("Salario extra (" + horasTrabajadasExtra + " * " + precioHoraExtra + ") : " + salarioExtra);
                salarioSemanal = salarioJornal + salarioExtra;

            }
        }

        // S
        System.out.println("El salario semanal a pagar es: " + salarioSemanal);
    }

    public static void ejercicio02() {
        // D
        int a, b, c, d, e;

        // I
        System.out.println("Ingresar numero para a:");
        a = dato.nextInt();
        System.out.println("Ingresar numero para b:");
        b = dato.nextInt();
        System.out.println("Ingresar numero para c:");
        c = dato.nextInt();
        System.out.println("Ingresar numero para d:");
        d = dato.nextInt();
        System.out.println("Ingresar numero para e:");
        e = dato.nextInt();

        // PS
        if (a > b && a > c && a > d && a > e) {
            System.out.println("El numero mayor es a: " + a);
        } else if (b > a && b > c && b > d && d > e) {
            System.out.println("El numero mayor es b: " + b);
        } else if (c > a && c > b && c > d && c > e) {
            System.out.println("El numero mayor es c: " + c);
        } else if (d > a && d > b && d > c && d > e) {
            System.out.println("El numero mayor es d: " + d);
        } else if (e > a && e > b && e > c && e > d) {
            System.out.println("El numero mayor es e: " + e);
        } else {
            System.out.println("No existe numero mayor");
        }
    }

    public static void ejercicio03() {
        // D
        double tarifa, total;
        int tipoPrenda, cantidad;

        // I
        tarifa = 0;
        System.out.println("Ingresar tipo de prenda");
        System.out.println("1: Pantalon");
        System.out.println("2: Saco");
        System.out.println("3: Abrigo");
        tipoPrenda = dato.nextInt();

        System.out.println("Ingresar cantidad de unidades:");
        cantidad = dato.nextInt();

        // P
        if (tipoPrenda == 1) {
            tarifa = 12;
        } else if (tipoPrenda == 2) {
            tarifa = 32;
        } else if (tipoPrenda == 3) {
            tarifa = 42;
        } else {
            System.out.println("No existe el tipo de prenda");
        }
        total = cantidad * tarifa;

        // S
        System.out.println("El sueldo total a pagar es: " + total);
    }

    public static void ejercicio04() {
        // D
        int t, d;
        float v;

        // I
        v = 0;
        String sd = JOptionPane.showInputDialog("Ingrese Distancia:");
        String st = JOptionPane.showInputDialog("Ingrese Tiempo:");

        // P
        d = Integer.parseInt(sd);
        t = Integer.parseInt(st);
        if (t > 0) {
            v = (float) (d) / t;
        }

        // S
        System.out.println("La velocidad (m/s) es: " + v);

    }

    public static void ejercicio05() {
        // D
        int t, d;
        float v;

        // I
        String sd = JOptionPane.showInputDialog("Ingrese Distancia:");
        String st = JOptionPane.showInputDialog("Ingrese Tiempo:");

        // PS
        d = Integer.parseInt(sd);
        t = Integer.parseInt(st);
        if (t > 0 && d > 0) {
            v = (float) (d) / t;
            System.out.println("LA VELOCIDAD (m/s) ES: " + v);
        } else {
            JOptionPane.showMessageDialog(new JComponent() {
            }, "Los datos ingresados deben ser diferentes de cero");
        }

    }

    // ESTRUCTURA CONDICIONAL COMPUESTA

    public static void ejercicio06() {
        // D
        int a, b;

        // I
        System.out.println("Ingresar numero para a:");
        a = dato.nextInt();
        System.out.println("Ingresar numero para b:");
        b = dato.nextInt();

        // PS
        if (a > b) {
            System.out.println("El numero mayor es a: " + a);
        } else {
            if (b > a) {
                System.out.println("El numero mayor es b: " + b);
            }
        }
    }

    public static void ejercicio07() {
        // D
        int num, cifra1, cifra2, suma, producto;

        // I
        System.out.print("Ingrese un número entero de dos cifras:");
        num = dato.nextInt();

        // PS
        cifra1 = num / 10;// Extraer parte 1
        cifra2 = num % 10;// Extraer parte 2

        // Pares modulo == 0
        if (cifra1 % 2 == 0 && cifra2 % 2 == 0) {
            suma = cifra1 + cifra2;
            System.out.println("La suma de las cifras pares es: " + suma);
        }
        // Impares modulo != 0
        else if (cifra1 % 2 != 0 && cifra2 % 2 != 0) {
            producto = cifra1 * cifra2;
            System.out.println("El producto de las cifras impares es: " + producto);
        } else {
            System.out.println("El número ingresado no cumple con las condiciones requeridas.");
        }
    }

    public static void ejercicio08() {
        // D
        int num;

        // I
        System.out.print("Ingrese un número entero:");
        num = dato.nextInt();

        // PS
        if (Math.floorMod(num, 7) == 0) {
            System.out.println("El número es múltiplo de 7.");
        } else {
            System.out.println("El número no es múltiplo de 7.");
        }
    }

    public static void ejercicio09() {
        // D
        int num, cifras;

        // I
        System.out.print("Ingrese un número entero:");
        num = dato.nextInt();

        // PS
        if (num < 31524) {

            System.out.println("log base 10: " + Math.log10(num));
            cifras = (int) Math.log10(num) + 1;// 1.25 + 1 = (int) 2
            System.out.println("El número tiene " + cifras + " cifras.");

        } else {
            System.out.println("El número es mayor a 31524");
        }
    }

    public static void ejercicio10() {
        // D
        int num, cifra1, cifra2, numIntercambiado;

        // I
        System.out.print("Ingrese un número entero de dos cifras:");
        num = dato.nextInt();

        // P
        // Ejemplo num = 12
        cifra1 = num / 10;// num/10 = (int)1.2 = 1
        cifra2 = num % 10;// num/10 = 1.2*2 = (int)2.4 = 2
        numIntercambiado = cifra2 * 10 + cifra1;// 2*10 = 20+1 = 21

        // S
        System.out.println("El número ingresado con sus cifras intercambiadas es: " + numIntercambiado);
    }

    public static void ejercicio11() {
        // D
        String codigo, nombre;
        double sueldoBasico, bonifAniosServicio, bonifHijos, sueldoBruto, sueldoNeto, descuento;
        int anioIngreso, numHijos, aniosServicio;

        // I
        System.out.print("Ingrese el código del trabajador: ");
        codigo = dato.nextLine();
        System.out.print("Ingrese el nombre del trabajador: ");
        nombre = dato.nextLine();
        System.out.print("Ingrese el sueldo básico del trabajador: ");
        sueldoBasico = dato.nextDouble();
        System.out.print("Ingrese el año de ingreso a la empresa del trabajador: ");
        anioIngreso = dato.nextInt();
        System.out.print("Ingrese el número de hijos del trabajador: ");
        numHijos = dato.nextInt();

        // P
        // Calcular bonificaciones por años de servicio
        aniosServicio = 2023 - anioIngreso;
        if (aniosServicio > 8) {
            bonifAniosServicio = aniosServicio * 10;
        } else {
            bonifAniosServicio = aniosServicio * 4;
        }

        // Calcular bonificaciones por hijos
        if (numHijos < 4) {
            bonifHijos = numHijos * 8;
        } else {
            bonifHijos = 20;
        }

        // Calcular sueldo bruto
        sueldoBruto = sueldoBasico + bonifAniosServicio + bonifHijos;

        // Calcular descuento
        if (sueldoBruto > 1500) {
            descuento = sueldoBruto * 0.015;
        } else {
            descuento = sueldoBruto * 0.005;
        }

        // Calcular sueldo neto
        sueldoNeto = sueldoBruto - descuento;

        // S
        System.out.println("Bonificación por años de servicio: " + bonifAniosServicio);
        System.out.println("Bonificación por hijos: " + bonifHijos);
        System.out.println("Sueldo bruto: " + sueldoBruto);
        System.out.println("Descuento: " + descuento);
        System.out.println("Sueldo neto: " + sueldoNeto);
    }

    public static void ejercicio12() {
        // D
        int num;

        // I
        System.out.print("Ingrese un número entero:");
        num = dato.nextInt();

        // PS
        if (num > 0) {
            System.out.println("El número ingresado es positivo.");
        } else if (num == 0) {
            System.out.println("El número ingresado es cero.");
        } else {
            System.out.println("El número ingresado es negativo.");
        }
    }

    public static void ejercicio13() {
        // D
        double a, b, c, discriminante, raiz, raiz1, raiz2;

        // I
        // Ejemplo f(x) = Ax^2 + Bx + C
        System.out.print("Ingrese el valor de A: ");
        a = dato.nextDouble();
        System.out.print("Ingrese el valor de B: ");
        b = dato.nextDouble();
        System.out.print("Ingrese el valor de C: ");
        c = dato.nextDouble();

        // PS
        discriminante = (b * b) - (4 * (a * c));

        if (discriminante > 0) {

            raiz1 = (-b + Math.sqrt(discriminante)) / (2 * a);
            raiz2 = (-b - Math.sqrt(discriminante)) / (2 * a);
            System.out.println("Las raíces reales son " + raiz1 + " y " + raiz2);

        } else if (discriminante == 0) {

            raiz = -b / (2 * a);
            System.out.println("La raíz real doble es " + raiz);

        } else {
            System.out.println("La ecuación no tiene raíces reales.");
        }
    }
}
