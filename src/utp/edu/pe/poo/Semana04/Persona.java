/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utp.edu.pe.poo.Semana04;

/**
 *
 * @author LAB-USR-PT116-E306
 */
public class Persona {

    String nombre;
    Fecha fechaNacimiento;
    double sueldo;
    int dni;

    public Persona(String nombre, Fecha fechaNacimiento, double sueldo, int dni) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.sueldo = sueldo;
        this.dni = dni;
    }

    public void mostrarDatos() {
        String formatFecha = this.fechaNacimiento.getDia() + "/" + this.fechaNacimiento.getMes() + "/" + this.fechaNacimiento.getAnio();
        System.out.println("-- Empleado --");
        System.out.println("Nombre: " + this.nombre);
        System.out.println("Dni: " + this.dni);
        System.out.println("Fecha de nacimiento: " + formatFecha);
        System.out.println("Sueldo: " + this.sueldo);
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Fecha getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Fecha fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

}
