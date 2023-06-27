/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package semana14;

/**
 *
 * @author LAB-USR-AQ265-A0303
 */
public class Pasajero {

    private double peso;
    private String destino;
    private char esFrecuente;
    private char opcionPrimera;
    private char opcionRefrigerio;
    private char opcionSeguro;

    public char getOpcionPrimera() {
        return opcionPrimera;
    }

    public void setOpcionPrimera(char opcionPrimera) {
        this.opcionPrimera = opcionPrimera;
    }

    public char getOpcionRefrigerio() {
        return opcionRefrigerio;
    }

    public void setOpcionRefrigerio(char opcionRefrigerio) {
        this.opcionRefrigerio = opcionRefrigerio;
    }

    public char getOpcionSeguro() {
        return opcionSeguro;
    }

    public void setOpcionSeguro(char opcionSeguro) {
        this.opcionSeguro = opcionSeguro;
    }

    public char getEsFrecuente() {
        return esFrecuente;
    }

    public void setEsFrecuente(char esFrecuente) {
        this.esFrecuente = esFrecuente;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    double incremento = 0;

    public void calcularIncremento() {
        if (peso >= 0 && peso <= 20) {
            incremento = 0.00;
        } else {
            if (peso >= 21 && peso <= 40) {
                incremento = 0.05;
            } else {
                if (peso >= 41 && peso <= 60) {
                    incremento = 0.10;
                } else {
                    incremento = 0.15;
                }
            }
        }
    }

    public double getIncremento() {
        return incremento;
    }

    public void setIncremento(double incremento) {
        this.incremento = incremento;
    }

}
