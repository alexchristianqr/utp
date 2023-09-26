package utp.edu.pe.poo.Semana06.polimorfismo;

public class Destino {
    //atributos
    private String destino;
    private double costo;

    public Destino(String destino) {
        this.destino = destino;
        if (destino.equalsIgnoreCase("Arequipa"))
            costo = 150;
        else if (destino.equalsIgnoreCase("Iquito"))
            costo = 250;
        else if (destino.equalsIgnoreCase("Huancayo"))
            costo = 100;
        else
            costo = 0;
    }

    public String getDestino() {
        return destino;
    }

    public double getCosto() {
        return costo;
    }

    //método mostrar costo

    //public 
}
