package semana02;

import java.util.Scanner;

public class Semana02 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Inicializamos las variables a utilizar
        int[] miarray;
        Scanner dato = new Scanner(System.in);

        // Obtener tamaño de los elementos del arreglo
        System.out.println("Ingresar un valor para delimitar el arreglo:");
        miarray = new int[dato.nextInt()];

        mostrarMenu(miarray);

    }

    public static void mostrarMenu(int[] miarray) {
        Scanner dato = new Scanner(System.in);
        int opcion;

        System.out.println("-- Mi menu principal -- ");
        System.out.println("1: Cargar");
        System.out.println("2: mostrar");
        System.out.println("3: ordenar");
        System.out.println("4: Salir");
        System.out.println("-- * --");
        opcion = dato.nextInt();

        switch (opcion) {
            case 1:
                cargar(miarray);
                mostrarMenu(miarray);
                break;
            case 2:
                mostrar(miarray);
                mostrarMenu(miarray);
                break;
            case 3:
                ordenar(miarray);
                mostrarMenu(miarray);
                break;
            case 4:
                dato.close();
                break;
            default:
                throw new AssertionError();
        }
    }

    public static void cargar(int[] miarray) {
        Scanner dato = new Scanner(System.in);

        // Obtener valores para guardar en cada posicion del arreglo
        for (int i = 0; i < miarray.length; i++) {
            System.out.println("Ingresar un valor para el arreglo en la posicion: " + i);
            miarray[i] = dato.nextInt();
        }
    }

    public static void mostrar(int[] miarray) {
        // Mostrar arreglo
        for (int i = 0; i < miarray.length; i++) {
            System.out.println("El valor es: " + miarray[i]);
        }
    }

    public static void ordenar(int[] lista) {

        int aux, j, x;
        /*
        aux: para trasladar
        j: para comparar
        x: para contar movimientos
        */
        String mensaje;

        for (int i = 1; i < lista.length; i++) {
            aux = lista[i];// Valor posición actual en el arreglo
            j = i - 1;// Valor posición anterior
            x = 0;// Inicializar a cero

            while (j >= 0 && lista[j] > aux) {
                lista[j + 1] = lista[j];

                // 
                j--;

                // Contar los movimientos
                x++;
            }

            mensaje = "Cantidad de movimientos: " + x;
            System.out.println(mensaje);

            lista[j + 1] = aux;
        }

    }

}
