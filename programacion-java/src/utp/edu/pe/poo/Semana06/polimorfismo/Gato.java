package utp.edu.pe.poo.Semana06.polimorfismo;

public class Gato extends Mascota {

    @Override
    public void mover() {
        patas = 4;

        System.out.println("El gato es un felino cariñoso y celoso");
        System.out.println("El gato tiene " + patas + " patas");

    }
}
