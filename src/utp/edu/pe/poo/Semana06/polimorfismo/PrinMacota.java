package utp.edu.pe.poo.Semana06.polimorfismo;

public class PrinMacota {
    public static void muevete(Mascota m) {
        m.mover();
    }

    public static void main(String[] args) {

        Mascota mascota = new Mascota();
        Perro milo = new Perro();
        Gato bolaDeNieve = new Gato();
        Loro luis = new Loro();

        mascota.mover();

        muevete(milo);
        muevete(bolaDeNieve);
        muevete(luis);


    }
}
