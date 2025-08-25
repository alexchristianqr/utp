/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utp.edu.pe.poo.Semana02.sobrecarga;

/**
 *
 * @author LAB-USR-PT116-E306
 */
public class Arbol {

    public Arbol() {
        System.out.println("Tipo: Arbol de manzano");
    }
    
    public Arbol(String description){
        System.out.println(description);
    }
    
    public static void main(String[] args) {
        Arbol arbol = new Arbol();
        Arbol arbol1 = new Arbol("Descripcion: Sembrado en 2003");
        Arbol arbol2 = new Arbol("Donado: Por Arboleda.org");
        
    }
    
    
}
