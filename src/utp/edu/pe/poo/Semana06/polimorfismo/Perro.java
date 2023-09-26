package utp.edu.pe.poo.Semana06.polimorfismo;

public class Perro extends Mascota {

    @Override
    public void mover() {
        patas = 4;
        System.out.println("El perro es juguetos y mueve la colita de alegría");
        System.out.println("El perro tiene " + patas + " patas");
    }
}
