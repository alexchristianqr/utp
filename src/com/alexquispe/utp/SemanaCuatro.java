/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.alexquispe.utp;

/**
 *
 * @author LAB-USR-AQ265-A0303
 */
public class SemanaCuatro {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
         // D
        int dia;
        String nombreDia;

        // I
        System.out.println("Ingresa un dia de la semana:");
        System.out.println("----------------------------");
        System.out.println("1: Lunes");
        System.out.println("2: Martes");
        System.out.println("3: Miercoles");
        System.out.println("4: Jueves");
        System.out.println("5: Viernes");
        System.out.println("6: Sabado");
        System.out.println("7: Domingo");
        System.out.print("\n");
        Scanner dato = new Scanner(System.in);
        dia = dato.nextInt();
        
        // P
        switch (dia) {
            case 1:
                nombreDia = "El dia es LUNES";
                break;
            case 2:
                nombreDia = "El dia es MARTES";
                break;
            case 3:
                nombreDia = "El dia es MIERCOLES";
                break;
            case 4:
                nombreDia = "El dia es JUEVES";
                break;
            case 5:
                nombreDia = "El dia es VIERNES";
                break;
            case 6:
                nombreDia = "El dia es SABADO";
                break;
            case 7:
                nombreDia = "El dia es DOMINGO";
                break;
            default:
                nombreDia = "No existe ese dia de la semana";
        }

        // S
        System.out.println("Rpta: " + nombreDia);
        
    }
    
}
