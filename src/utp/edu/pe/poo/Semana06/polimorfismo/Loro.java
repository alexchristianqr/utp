package utp.edu.pe.poo.Semana06.polimorfismo;

public class Loro extends Mascota {

    @Override
    public void mover() {
        patas = 2;
        System.out.println("El loro es parlanchin y grosero");
        System.out.println("El loro tiene " + patas + " patas");
    }
}
