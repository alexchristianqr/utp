/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utp.edu.pe.poo.Semana03.ejercicio03;

public class Alumno {
    //ATRIBUTOS
    private int codigo;
    private String nombre;
    private String carrera;
    private double promedio;
    
    //CONSTRUCTOR

    public Alumno(int codigo, String nombre, String carrera, double promedio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.carrera = carrera;
        this.promedio = promedio;
    }
    
    //MÉTODOS
    //método pago mensual de pensión
    public double pagoMensualPension(){
        if(carrera.equalsIgnoreCase("ingeniería"))
           return 1500; 
        if(carrera.equalsIgnoreCase("administración"))
           return 1000;
        if(carrera.equalsIgnoreCase("psicología"))
           return 1000;
        if(carrera.equalsIgnoreCase("medicina"))
           return 2500; 
        else
            return 0;     
    }
    //Método descuento sobre el pago mensual
    public double descuento(){
        if(0<=promedio && promedio<=10)
        return 0*pagoMensualPension(); 
        if(11<=promedio && promedio<=14)
        return 0.05*pagoMensualPension(); 
        if(15<=promedio && promedio<=18)
        return 0.10*pagoMensualPension(); 
        if(18<=promedio)
        return 0.50*pagoMensualPension(); 
        else
            return 0;     
    }
    //Método mostrar descuento
    //Método descuento sobre el pago mensual
    public double mostrarPorDescuento(){
        if(0<=promedio && promedio<=10)
        return 0; 
        if(11<=promedio && promedio<=14)
        return 5; 
        if(15<=promedio && promedio<=18)
        return 10; 
        if(18<=promedio)
        return 50; 
        else
            return 0;     
    }
    //Método pago total
     public double pagoTotal(){
    
        return pagoMensualPension()-descuento();
    }
    
    
    //MÉTODOS GET Y SET

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }
    
    
    
}
