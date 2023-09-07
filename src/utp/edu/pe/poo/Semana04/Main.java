/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package utp.edu.pe.poo.Semana04;

/**
 *
 * @author LAB-USR-PT116-E306
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        Fecha fecha = new Fecha();
        fecha.setDia(10);
        fecha.setMes(8);
        fecha.setAnio(1995);

        Empleado empleado = new Empleado("Alex", fecha, 2500, 72482060);
        empleado.mostrarDatos();

        // -- 
        
        Periodico periodico = new Periodico("El Comercio", "06/09/2023");
        periodico.mostrarDatos("Periodico");
        Revista revista = new Revista("Somos", "05/09/2023");
        revista.mostrarDatos("Revista");

    }

}
