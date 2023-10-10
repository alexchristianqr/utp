package semana08;

public class Serie implements ISerie {

    private int inicio;
    private int valor;

    public Serie() {
        this.inicio = 0;
        this.valor = 0;
    }

    @Override
    public int siguiente() {
        valor += 2;
        return valor;
    }

    @Override
    public void reiniciar() {
        valor = inicio;
    }

    @Override
    public void comenzar(int valor) {
        this.inicio = valor;
        this.valor = valor;
    }

}
