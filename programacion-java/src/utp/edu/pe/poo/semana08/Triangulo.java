/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package semana08;

public class Triangulo implements IFigura {

    double a, b, c;

    @Override
    public double perimetro() {
        double p = (a + b + c) / 2;

        if (a > 0 && b > 0 && c > 0) {
            if (p > a && p > b && p > c) {
                return a + b + c;
            }
        }

        return 0;
    }

    @Override
    public double area() {
        double p = perimetro() / 2.0;
        if (perimetro() > 0) {
            return Math.sqrt(p * (p - a) * (p - b) * p - c);
        }
        return 0;
    }

}
