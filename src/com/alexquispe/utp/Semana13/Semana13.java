package com.alexquispe.utp.Semana13;


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
