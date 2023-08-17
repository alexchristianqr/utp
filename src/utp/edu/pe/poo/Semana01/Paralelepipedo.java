/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utp.edu.pe.poo.Semana01;

/**
 *
 * @author LAB-USR-PT116-E305
 */
public class Paralelepipedo {

    double largo;
    double ancho;
    double altura;

    public Paralelepipedo(double largo, double ancho, double altura) {
        this.largo = largo;
        this.ancho = ancho;
        this.altura = altura;
    }

    public double perimetro() {
        double respuesta = 2 * largo + 4 * ancho + 4 * altura;
        System.out.println("El perimetro es: " + respuesta);
        return respuesta;
    }

    public double area() {
        double respuesta = 2 * ((largo * ancho) + (largo * altura) + (ancho * altura));
        System.out.println("El area es: " + respuesta);
        return respuesta;
    }

    public double volumen() {
        double respuesta = (largo * ancho * altura);
        System.out.println("El volumen es: " + respuesta);
        return respuesta;
    }

    public String informacion() {
        String mensaje = "";
        mensaje += "\n";
        mensaje += "-- [Clase] --";
        mensaje += "\n";
        mensaje += "Paralelepipedo";
        mensaje += "\n\n";
        mensaje += "-- [Atributos] --";
        mensaje += "\n";
        mensaje += "Largo: " + largo;
        mensaje += "\n";
        mensaje += "Ancho: " + ancho;
        mensaje += "\n";
        mensaje += "Altura: " + altura;
        mensaje += "\n\n";
        mensaje += "-- [Metodos] --";
        mensaje += "\n";
        mensaje += "Perimetro(): " + perimetro();
        mensaje += "\n";
        mensaje += "Area(): " + area();
        mensaje += "\n";
        mensaje += "Volumen(): " + volumen();

        System.out.println(mensaje);

        return mensaje;
    }
}
