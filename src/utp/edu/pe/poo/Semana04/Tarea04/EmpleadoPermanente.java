package utp.edu.pe.poo.Semana04.Tarea04;

import java.text.DecimalFormat;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author LAB-USR-AQ265-A0405
 */
public class EmpleadoPermanente extends Empleado {

    double sueldoBase;
    String afiliacion;

    public EmpleadoPermanente(String dni, String apellidos, String nombres, double sueldoBase, String afiliacion) {
        super(dni, apellidos, nombres);
        this.sueldoBase = sueldoBase;
        this.afiliacion = afiliacion;
    }

    public double descuentos() {
        if (afiliacion.equalsIgnoreCase("AFP")) {
            return sueldoBase * 0.15;
        } else {
            return sueldoBase * 0.11;
        }
    }

    public double ingresos() {
        return sueldoBase;
    }

    public double sueldo() {
        return ingresos() - descuentos();
    }

    public String mostrarInfo() {
        DecimalFormat decimalFormat = new DecimalFormat("###0.00");
        String msg = "";

        msg += super.mostrarInfo();
        msg += "Ingresos: " + decimalFormat.format(ingresos()) + "\t";
        msg += "Descuentos: $" + decimalFormat.format(descuentos()) + "\t";
        msg += "Sueldo Neto: $" + decimalFormat.format(sueldo()) + "\n";

        return msg;
    }

}
