/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utp.edu.pe.poo.Semana03.ejercicio02;

/**
 *
 * @author alex
 */
public class Banco {

    private String nombreCliente;
    private String numeroCuenta;
    private boolean deudaPendiente;
    private int tipoTarjeta;
    private double limiteCredito;
    private double totalAumento;
    private double nuevoLimiteCredito;

    public Banco(String nombreCliente, String numeroCuenta, boolean deudaPendiente, int tipoTarjeta, double limiteCredito) {
        this.nombreCliente = nombreCliente;
        this.numeroCuenta = numeroCuenta;
        this.deudaPendiente = deudaPendiente;
        this.tipoTarjeta = tipoTarjeta;
        this.limiteCredito = limiteCredito;
    }

    public String obtenerInformacion() {
        String mensaje = "";
        mensaje += "[Principal]";
        mensaje += "\n";
        mensaje += "Cliente: " + nombreCliente;
        mensaje += "\n";
        mensaje += "Numero de cuenta: " + numeroCuenta;
        mensaje += "\n";
        mensaje += "Limite de credito: " + limiteCredito;
        mensaje += "\n";
        mensaje += "Tipo de tarjeta: " + tipoTarjeta;
        mensaje += "\n";
        mensaje += "\n";
        mensaje += "[Detalle]";
        mensaje += "\n";
        if (isDeudaPendiente()) {
            mensaje += "Deuda pendiente: Si";
            mensaje += "\n";
            mensaje += "Total aumento: " + totalAumento + " (Por tener una deuda pendiente, solo aplica el 50% de aumento.)";
        } else {
            mensaje += "Deuda pendiente: No";
            mensaje += "\n";
            mensaje += "Total aumento: " + totalAumento;
        }
        mensaje += "\n";
        mensaje += "Nuevo limite de crédito: " + nuevoLimiteCredito;

        System.out.println(mensaje);

        return mensaje;
    }

    public void aumentarLimite() {
        double aumento;
        double porcentaje;
        boolean tieneDeuda = isDeudaPendiente();// Devuelve falso o verdadero

        switch (tipoTarjeta) {
            case 1:
                porcentaje = 0.25;
                if (tieneDeuda) {
                    aumento = (porcentaje / 2);
                } else {
                    aumento = porcentaje;
                }
                break;
            case 2:
                porcentaje = 0.35;
                if (tieneDeuda) {
                    aumento = (porcentaje / 2);
                } else {
                    aumento = porcentaje;
                }

                break;
            case 3:
                porcentaje = 0.40;
                if (tieneDeuda) {
                    aumento = 0;
                } else {
                    aumento = porcentaje;
                }

                break;
            default:
                porcentaje = 0.50;
                if (tieneDeuda) {
                    aumento = 0;
                } else {
                    aumento = porcentaje;
                }
        }

        totalAumento = (limiteCredito * aumento);// Se divide entre 2 si hay deuda pendiente
        nuevoLimiteCredito = (limiteCredito + totalAumento);

    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public boolean isDeudaPendiente() {
        return deudaPendiente;
    }

    public void setDeudaPendiente(boolean deudaPendiente) {
        this.deudaPendiente = deudaPendiente;
    }

    public int getTipoTarjeta() {
        return tipoTarjeta;
    }

    public void setTipoTarjeta(int tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
    }

    public double getLimiteCredito() {
        return limiteCredito;
    }

    public void setLimiteCredito(double limiteCredito) {
        this.limiteCredito = limiteCredito;
    }

    public double getTotalAumento() {
        return totalAumento;
    }

    public void setTotalAumento(double totalAumento) {
        this.totalAumento = totalAumento;
    }

    public double getNuevoLimiteCredito() {
        return nuevoLimiteCredito;
    }

    public void setNuevoLimiteCredito(double nuevoLimiteCredito) {
        this.nuevoLimiteCredito = nuevoLimiteCredito;
    }

}
