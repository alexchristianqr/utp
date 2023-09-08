/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author LAB-USR-AQ265-A0405
 */
public class Empleado {

    protected String dni;
    protected String apellidos;
    protected String nombres;

    public Empleado(String dni, String apellidos, String nombres) {
        this.dni = dni;
        this.apellidos = apellidos;
        this.nombres = nombres;
    }

    public String mostrarInfo() {
        System.out.println("Datos del empleado");
        String msg = "";
        
        msg += "Nombres: " + nombres + "\t";
        msg += "Apellidos: " + apellidos + "\t";
        msg += "DNI: " + dni + "\t";

        return msg;
    }

}
