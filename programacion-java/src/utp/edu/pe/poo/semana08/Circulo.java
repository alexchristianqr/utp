package semana08;

public class Circulo implements IFigura {

    private double radio;

    @Override
    public double perimetro() {
        return 2 * Math.PI * radio;
    }

    @Override
    public double area() {
        return Math.PI * Math.pow(radio, 2);
    }

    public double getRadio() {
        return radio;
    }

    public void setRadio(double radio) {
        this.radio = radio;
    }

    public String mostrarDatos() {
        return "Radio es: " + area() + ", Perimetro es: " + perimetro();
    }

}
