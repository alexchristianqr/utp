/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package semana04;

/**
 *
 * @author LAB-USR-PT116-E306
 */
public class Diario {

    private String nombre;
    private String fecha;

    public Diario(String nombre, String fecha) {
        this.nombre = nombre;
        this.fecha = fecha;
    }

    public void mostrarDatos(String className) {
        System.out.println("-- " + className + " --");
        System.out.println("Nombre: " + this.getNombre());
        System.out.println("Fecha de nacimiento: " + this.getFecha());
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

}
