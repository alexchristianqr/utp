package semana12;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // Ejemplo 01
        System.out.println("-- EJEMPLO 01 --");
        CGenerica<String, Integer> cGenerica = new CGenerica("Alex", 30);
        System.out.println("1. " + cGenerica.getObjPricipal());
        System.out.println("2. " + cGenerica.getObjSecondary());

        // Ejemplo 02
        System.out.println("-- EJEMPLO 02 --");
        CGenerica<PrincipalGenerica, SecondaryGenerica> cGenerica1 = new CGenerica(new PrincipalGenerica(), new SecondaryGenerica());

        // Ejemplo 03
        System.out.println("-- EJEMPLO 03 --");
        List<Artefacto> artefactos = new ArrayList<>();
        artefactos.add(new Artefacto(1, "Prod 001", "Nike", 13.98));
        artefactos.add(new Artefacto(2, "Prod 002", "Owen", 98.40));
        artefactos.add(new Artefacto(3, "Prod 003", "Chiki", 34.40));

        Iterator it = artefactos.iterator();

        while (it.hasNext()) {
            System.out.println("El artefacto es: " + it.next());

        }

//       Collections.sort(artefactos);
    }

}
