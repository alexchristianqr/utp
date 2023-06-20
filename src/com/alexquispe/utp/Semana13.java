/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alexquispe.utp;

/**
 *
 * @author LAB-USR-AQ265-A0303
 */

class Persona{

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
    
    void comer(){
        System.out.println("La persona esta comiendo...");
    }
    
    void beber(){
        System.out.println("La persona esta bebiendo...");
    }
    
    void dormir(){
        System.out.println("La persona esta durmiendo...");
    }
}

public class Semana13 {
    public static void main(String[] args) {
        Persona estudiante = new Persona();
        
        estudiante.comer();
        estudiante.beber();
        estudiante.dormir();
        
        estudiante.setNombre("Juan");
        estudiante.setApellidoPaterno("Gonzales");
        estudiante.setApellidoMaterno("Prada");
        
        String nombreCompleto = estudiante.getNombre()+" "+estudiante.getApellidoPaterno()+" "+estudiante.getApellidoMaterno();
        System.out.println("El nombre del estudiente es: "+nombreCompleto);
    }
}
