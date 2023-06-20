/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alexquispe.utp;

/**
 *
 * @author LAB-USR-AQ265-A0303
 */
class Persona {

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String nombre;
    public String apellidoMaterno;
    public String apellidoPaterno;
    public String sexo;
    public String edad;

    void comer() {
        System.out.println("La persona esta comiendo...");
    }

    void beber() {
        System.out.println("La persona esta bebiendo...");
    }

    void dormir() {
        System.out.println("La persona esta durmiendo...");
    }
}

class Perro {

    String nombre;
    String colorPelo;
    int cantidadOjos;

    Perro(String nombre, String colorPelo, int cantidadOjos) {
        this.nombre = nombre;
        this.colorPelo = colorPelo;
        this.cantidadOjos = cantidadOjos;
    }

    void ladrar() {
        System.out.println("Ladrando...");
    }

    void caminar() {
        System.out.println("Caminando...");
    }

    void comer() {
        System.out.println("Comiendo...");
    }

    public Perro() {
        this.colorPelo = "Marron";
    }
    
    
}

public class Semana13 {

    public static void main(String[] args) {

        // Crear objeto perro
        // Perro perro = new Perro("Fido", "Marron", 2);
        Perro perro = new Perro();
        perro.nombre = "Fido";
        perro.cantidadOjos = 2;
        perro.ladrar();
        perro.caminar();
        perro.comer();
        
        System.out.println("----------------------");
        System.out.println("Información del perro:");
        System.out.println("----------------------");
        System.out.println("Nombre: " + perro.nombre);
        System.out.println("Color: " + perro.colorPelo);
        System.out.println("Cantidad de ojos: " + perro.cantidadOjos);
        System.out.print("\n");

        // Crear objeto persona
        /*Persona estudiante = new Persona();

        estudiante.comer();
        estudiante.beber();
        estudiante.dormir();

        estudiante.setNombre("Alex");
        estudiante.setApellidoPaterno("Quispe");
        estudiante.setApellidoMaterno("Roque");

        String nombreCompleto = estudiante.getNombre() + " " + estudiante.getApellidoPaterno() + " " + estudiante.getApellidoMaterno();
        System.out.println("El nombre del estudiente es: " + nombreCompleto);*/
    }
}
