package semana11;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Semana11 {

    public static void main(String[] args) {

        List<Producto> productos = new ArrayList<>();

        productos.add(new Producto(1, "Escoba", "Hude", 14.90));
        productos.add(new Producto(2, "Tubo de agua", "Pavco", 14.90));

        Iterator it = productos.iterator();

        while (it.hasNext()) {
            System.out.println(it.next());
        }

        for (Producto producto : productos) {
            System.out.println("El producto es: \n" + producto.mostrarInfo());
        }
    }
}
