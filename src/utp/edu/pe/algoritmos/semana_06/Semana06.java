package utp.edu.pe.algoritmos.semana_06;

import java.util.Scanner;

public class Semana06 {

    public static void main(String[] args) {
        int valor = -1;
        valor = preguntar(valor);

        System.out.println("essss" + valor);

        if (valor > 0) {
            int resultado = factorial(valor);
            System.out.println("El factorial de " + valor + " es: " + resultado);
        } else {
            System.out.println("Intentalo nuevamente...");
            valor = preguntar(valor);
            int resultado = factorial(valor);
            System.out.println("El factorial de " + valor + " es: " + resultado);
        }
    }

    public static int preguntar(int valor) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingresa un numero positivo");
        int respuesta = scanner.nextInt();

        System.out.println("valor es: " + respuesta);

        return respuesta;
    }

    public static int factorial(int n) {
        int resultado = 0;

        if (n == 0) {
            resultado = 1;
        } else {
            resultado = n * factorial(n - 1);
            System.out.println("El resultado en la recursividad es: " + resultado);
        }

        return resultado;
    }
}
