
import java.util.Scanner;

public class Semana06 {

    public static void main(String[] args) {
        // Inicializamos las variables a utilizar
        int[] miarray = {60, 12, 20, 8, 35, 6, 10, 11};

        int indiceIzq = 0;
        int indiceDer = miarray.length - 1;
        ordenarPorQuicksort(miarray, indiceIzq, indiceDer);

        for (int i = 0; i < miarray.length; i++) {
            System.out.println("El valor es: " + miarray[i]);
        }

//        Scanner dato = new Scanner(System.in);
//
//        // Obtener tamaño de los elementos del arreglo
//        System.out.println("Ingresar un valor para definir el tamano del arreglo:");
//        miarray = new int[dato.nextInt()];
//
//        // Inicializar menu principal
//        mostrarMenu(miarray);
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
                ordenarPorInserción(miarray);
                mostrarMenu(miarray);
                break;
            case 4:
                System.out.println("Ingresar el valor a buscar:");
                int valorBuscar = dato.nextInt();
                int posicionEncontrada = busquedaBinaria(miarray, valorBuscar);

                System.out.println("es: " + posicionEncontrada);

                // Validar valor encontrado
                if (posicionEncontrada >= 0) {
                    System.out.println("El valor encontrado es: " + miarray[posicionEncontrada] + ", en el arreglo miarray[" + posicionEncontrada + "]");
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

    public static void ordenarPorInserción(int[] miarray) {

        int elementoActual, j;

        // Iterar filas
        for (int i = 1; i < miarray.length; i++) {
            elementoActual = miarray[i];// Elemento en el arreglo
            j = i - 1;// Posición inicial = 0

            // Iterar columnas
            while (j >= 0 && miarray[j] > elementoActual) {
                miarray[j + 1] = miarray[j];
                j--;
            }

            miarray[j + 1] = elementoActual;
        }
    }

    public static void ordenarPorSeleccion(int[] miarray) {

        // Iterar filas
        for (int i = 0; i < (miarray.length - 1); i++) {

            int min = i;// Posición menor

            // Iterar columnas
            for (int j = (i + 1); j < miarray.length; j++) {
                if (miarray[j] < miarray[min]) {
                    min = j;// Actualizar posición menor
                }
            }

            // Validar posición actual sea diferente a la posicion menor encontrada
            if (i != min) {
                int temp = miarray[i];// Guardar temporalmente el valor elemento actual
                miarray[i] = miarray[min];// Guardar valor elemento menor
                miarray[min] = temp;// Guardar temporal
            }
        }
    }

    public static void ordenarPorBurbuja(int[] miarray) {
        // Iterar filas
        for (int i = 0; i < miarray.length - 1; i++) {

            // Iterar columnas
            for (int j = (i + 1); j < miarray.length; j++) {
                if (miarray[i] > miarray[j]) {
                    int temp = miarray[i];
                    miarray[i] = miarray[j];
                    miarray[j] = temp;
                }
            }
        }
    }

    public static int busquedaBinaria(int[] miarray, int dato) {
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

    public static int busquedaBinaria2(int[] miarray, int dato) {
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

    public static void intercambiar(int[] miarray, int i, int j) {
        int temp;

        if (i < j) {
            temp = miarray[i];
            miarray[i] = miarray[j];
            miarray[j] = temp;
        }

    }

    public static void ordenarPorQuicksort(int[] miarray, int indiceIzq, int indiceDer) {
        int pivote, i, j, temp;

        // Se toma como pivote el primer elemento en la posición 0
        pivote = miarray[indiceIzq];

        // Se definen los indices de izquierda y derecha
        i = indiceIzq;
        j = indiceDer;

        // Validar que i sea menor a j
        while (i < j) {
            // Mientras el elemento actual en i sea menor o igual al pivote; i sea menor a j
            while (miarray[i] <= pivote && i < j) {
                i++;
            }

            // Mientras el elemento actual en j sea mayor al pivote
            while (miarray[j] > pivote) {
                j--;
            }

            // Validar que i sea menor a j
            if (i < j) {
                temp = miarray[i];
                miarray[i] = miarray[j];
                miarray[j] = temp;
            }
        }

        miarray[indiceIzq] = miarray[j];
        miarray[j] = pivote;

        int derecha = j - 1;// Obtener el ultimo indice recorrido en j y restarle -1
        if (indiceIzq < derecha) {
            ordenarPorQuicksort(miarray, indiceIzq, derecha);
        }

        int izquierda = j + 1;// Obtener el ultimo indice recorrido en j y sumarle +1
        if (izquierda < indiceDer) {
            ordenarPorQuicksort(miarray, izquierda, indiceDer);
        }

    }

}
