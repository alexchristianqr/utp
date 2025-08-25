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
public class EmpleadoVendedor extends Empleado {

    double montoVendido;
    double tasaComision;

    public EmpleadoVendedor(String dni, String apellidos, String nombres, double montoVendido, double tasaComision) {
        super(dni, apellidos, nombres);
        this.montoVendido = montoVendido;
        this.tasaComision = tasaComision;
    }

    public double ingresos() {
        return montoVendido * tasaComision;
    }

    public double bonificaciones() {
        if (montoVendido < 1000) {
            return 0;
        } else if (montoVendido < 5000) {
            return ingresos() * 0.05;
        } else {
            return ingresos() * 0.10;
        }
    }

    public double descuentos() {
        if (ingresos() < 1000) {
            return ingresos() * 0.11;
        } else {
            return ingresos() * 0.15;
        }
    }

    public double sueldo() {
        return ingresos() + bonificaciones() - descuentos();
    }

    public String mostrarInfo() {
        DecimalFormat decimalFormat = new DecimalFormat("###0.00");
        String msg = "";

        msg += super.mostrarInfo();
        msg += "Ingresos: " + decimalFormat.format(ingresos()) + "\t";
        msg += "Bonificaciones: $" + decimalFormat.format(bonificaciones()) + "\t";
        msg += "Descuentos: $" + decimalFormat.format(descuentos()) + "\t";
        msg += "Sueldo Neto: $" + decimalFormat.format(sueldo()) + "\n";

        return msg;
    }

}
