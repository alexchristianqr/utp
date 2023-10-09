package semana09;

public class Libro extends MaterialBibliografico implements IPrestable {

    private String codigo;
    private String titulo;
    private int anio;
    private boolean prestado;

    public Libro(String codigo, String titulo, int anio) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.anio = anio;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    @Override
    public boolean isPrestable() {
        return prestado;
    }

    @Override
    public void prestar() {
        prestado = true;
    }

    @Override
    public void prestado() {
        prestado = prestado;
    }

    @Override
    public String mostrarInformacion() {
        return "";
    }

    @Override
    public void devolver() {
        prestado = false;
    }

}
