package utp.edu.pe.poo.Semana04.Tarea04;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author LAB-USR-AQ265-A0405
 */
public class Main {
    public static void main(String[] args) {
        EmpleadoPermanente empleadoPermanente = new EmpleadoPermanente("12345678", "Garcia Perez", "Alex", 50000, "AFP");
        String mensaje = empleadoPermanente.mostrarInfo();
        
        System.out.println(mensaje);
    }
}
