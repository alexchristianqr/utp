package semana11.colecciones;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ListaEnlazada {

    public static void main(String[] args) {

        // Listar con ArrayList
        List<Integer> listaEnteros = new ArrayList();

        listaEnteros.add(1);
        listaEnteros.add(2);
        listaEnteros.add(3);
        listaEnteros.add(4);
        listaEnteros.add(3, 18);

        System.out.println(listaEnteros);

        // Listar con Iterator
        Iterator it = listaEnteros.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

        // Listar cadena con ArrayList
        List<String> usuarios = new ArrayList();

        usuarios.add("Alex");
        usuarios.add("Ruth");
        usuarios.add("Alvaro");

        for (String usuario : usuarios) {
            System.out.println("El usuario es: " + usuario);
        }

        // Listar con LinkedList
        LinkedList<Object> links = new LinkedList();

        links.add(10);
        links.add(20);
        links.add(30);
        links.add("Java");
        links.add(true);
        links.add((2 * 5) / 2);

        System.out.println(links);

    }
}
