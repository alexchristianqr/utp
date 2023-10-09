/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package semana09.ejercicio;

/**
 *
 * @author LAB-USR-AQ265-A0302
 */
public abstract class TTrabajador implements IComparable {
    
    private String codigo;
    private String nombre;
    private boolean genero;
    
    public abstract double pagoNeto();
    
}
