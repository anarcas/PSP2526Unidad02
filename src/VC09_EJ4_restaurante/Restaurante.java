/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VC09_EJ4_restaurante;

/**
 *
 * @author anaranjo
 */
public class Restaurante {

    // Declaración variables
    private int numComensalesSab = 25;
    private int numComensalesDom = 25;
    private boolean reserva = false;
    private boolean cerrado = false;

    // Métodos getters y setters
    public int getNumComensalesSab() {
        return numComensalesSab;
    }

    public void setNumComensalesSab(int numComensalesSab) {
        this.numComensalesSab = numComensalesSab;
    }

    public int getNumComensalesDom() {
        return numComensalesDom;
    }

    public void setNumComensalesDom(int numComensalesDom) {
        this.numComensalesDom = numComensalesDom;
    }

    public boolean isReserva() {
        return reserva;
    }

    public void setReserva(boolean reserva) {
        this.reserva = reserva;
    }

    public boolean isCerrado() {
        return cerrado;
    }

    public void setCerrado(boolean cerrado) {
        this.cerrado = cerrado;
    }

    // Método sincronizado realizarReserva()
    public synchronized boolean realizarReserva(int dia, int numComensales, String nombreCliente) {
        switch (dia) {

            case 1:
                if ((this.getNumComensalesSab() - numComensales) >= 0 && !this.isCerrado()) {
                    this.setNumComensalesSab(this.getNumComensalesSab() - numComensales);
                    // Establecer reserva
                    System.out.println(this.establecerReserva(nombreCliente, dia, numComensales));
                    this.setReserva(true);
                } else {
                    // Rechazar reserva
                    System.out.println(this.rechazarReserva(nombreCliente, dia, numComensales));
                    this.setReserva(false);
                }
                break;

            case 2:
                if ((this.getNumComensalesDom() - numComensales) >= 0 && !this.isCerrado()) {
                    this.setNumComensalesDom(this.getNumComensalesDom() - numComensales);
                    // Establecer reserva
                    System.out.println(this.establecerReserva(nombreCliente, dia, numComensales));
                    this.setReserva(true);
                } else {
                    // Rechazar reserva
                    System.out.println(this.rechazarReserva(nombreCliente, dia, numComensales));
                    this.setReserva(false);
                }
                break;

            default:
                System.err.println("Día seleccionado no válido.");
        }

        // Actualización de variables
        System.out.println(String.format("\tPlazas restantes: Sábado = %d, Domingo = %d.", this.getNumComensalesSab(), this.getNumComensalesDom()));
        
        // Si el número de comensales restantes es inferior a 2 se cierran las reservas
        if (this.getNumComensalesSab()<2 && this.getNumComensalesDom()<2){
            this.setCerrado(true);
        }

        return this.isReserva();
    }

    // Método establecerReserva()
    public String establecerReserva(String nombre, int dia, int comensales) {
        return String.format("%s Reserva para %s de %d comensales.", nombre, dia == 1 ? "Sábado" : "Domingo", comensales);
    }

    // Método rechazarReserva()
    public String rechazarReserva(String nombre, int dia, int comensales) {
        return String.format("No hay plazas suficientes para esa reserva. %s - %s - %d comensales.",
                nombre,
                dia == 1 ? "Sábado" : "Domingo",
                comensales);

    }

}
