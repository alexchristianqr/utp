package utp.edu.pe.poo.Semana06.polimorfismo;

public class Categoria extends Destino {
    private char clase;
    private double costoClase;

    //cosntructor
    public Categoria(char clase, String destino) {
        super(destino);
        this.clase = clase;
        if (clase == 'A')
            costoClase = 1.3 * getCosto();
        else if (clase == 'B')
            costoClase = getCosto();
        else
            costoClase = 0;
    }

    public char getClase() {
        return clase;
    }

    public double getCostoClase() {
        return costoClase;
    }


}
