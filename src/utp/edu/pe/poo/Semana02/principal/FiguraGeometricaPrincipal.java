/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal;

import encapsulamiento.CirculoEncapsulado;
import encapsulamiento.RectanguloEncapsulado;

/**
 *
 * @author LAB-USR-PT116-E306
 */
public class FiguraGeometricaPrincipal {
    
    public static void main(String[] args) {
        
        // Rectangulo
        RectanguloEncapsulado rectanguloEncapsulado = new RectanguloEncapsulado(2, 2);
        rectanguloEncapsulado.area();
        rectanguloEncapsulado.perimetro();
        
        // Circulo
        CirculoEncapsulado circuloEncapsulado = new CirculoEncapsulado(10);
        circuloEncapsulado.area();
    }
    
}
