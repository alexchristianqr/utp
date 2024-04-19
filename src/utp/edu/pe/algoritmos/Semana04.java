package semana04;

import java.util.Scanner;

public class Semana04 {

    public static void main(String[] args) {
        // Inicializamos las variables a utilizar
        int[] miarray;
        Scanner dato = new Scanner(System.in);

        // Obtener tamaño de los elementos del arreglo
        System.out.println("Ingresar un valor para definir el tamano del arreglo:");
        miarray = new int[dato.nextInt()];

        // Inicializar menu principal
        mostrarMenu(miarray);
    }

    public static void mostrarMenu(int[] miarray) {
        Scanner dato = new Scanner(System.in);
        int opcion;

        System.out.println("-- Mi Menu Principal -- ");
        System.out.println("1: Cargar valores");
        System.out.println("2: Mostrar valores");
        System.out.println("3: Ordenar valores");
        System.out.println("4: Buscar");
        System.out.println("5: Salir");
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
                System.out.println("Ingresar el valor a buscar:");
                int valorBuscar = dato.nextInt();
                int posicionEncontrada = busquedaBinaria(miarray, valorBuscar);
                
                System.out.println("es: "+ posicionEncontrada);

                // Validar valor encontrado
                if (posicionEncontrada >= 0) {
                    System.out.println("El valor encontrado es: " + miarray[posicionEncontrada] + ", en el arreglo miarray["+posicionEncontrada+"]");
                } else {
                    System.out.println("Valor no encontrado");
                }

                mostrarMenu(miarray);
                break;
            case 5:
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
            System.out.println("El valor es: " + miarray[i] + "\t");
        }
    }

    public static void ordenar(int miarray[]) {

        int aux, j, x;
        /*
            aux: para trasladar
            j: para comparar
            x: para contar movimientos
         */
        String mensaje = "";

        for (int i = 1; i < miarray.length; i++) {
            aux = miarray[i];// Valor posición actual en el arreglo
            j = i - 1;// Valor posición anterior
            // x = 0;// Inicializar a cero

            while (j >= 0 && miarray[j] > aux) {
                miarray[j + 1] = miarray[j];

                // 
                j--;

                // Contar los movimientos
                // x++;
            }

            // mensaje = "Cantidad de movimientos: " + x;
            miarray[j + 1] = aux;
        }

        // System.out.println(mensaje);
    }

    public static int busquedaBinaria(int miarray[], int dato) {
        int inicio = 0;
        int fin = miarray.length - 1;// Total elementos -1 = Total de posiciones
        int posicion;

        while (inicio <= fin) {
            posicion = (inicio + fin) / 2;

            if (miarray[posicion] == dato) {
                return posicion;
            } else if (miarray[posicion] < dato) {
                inicio = posicion + 1;
            } else {
                fin = posicion - 1;
            }
        }
        return -1;

    }
    
    public static int busquedaBinaria2(int miarray[], int dato) {
        int inicio = 0;
        int fin = miarray.length - 1;// Total elementos -1 = Total de posiciones
        int posicion;

        while (inicio <= fin) {
            posicion = (inicio + fin) / 2;

            if (miarray[posicion] == dato) {
                return posicion;
            } else if (miarray[posicion] < dato) {
                inicio = posicion + 1;
            } else {
                fin = posicion - 1;
            }
        }
        return -1;

    }
}
