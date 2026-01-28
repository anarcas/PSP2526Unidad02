/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VC09_EJ3_hermanos_pollos;

/**
 * Clase recurso compartido
 *
 * @author anaranjo
 */
public class Polleria {

    private int pollos = 100;
    private int patatas = 100;
    private boolean ocupado;
    private boolean abierta;

    // Método constructor
    public Polleria() {
        this.ocupado = false;
        this.abierta = true;
    }

    public synchronized int getPollos() {
        return pollos;
    }

    public synchronized void setPollos(int pollos) {
        this.pollos = pollos;
    }

    public synchronized int getPatatas() {
        return patatas;
    }

    public synchronized void setPatatas(int patatas) {
        this.patatas = patatas;
    }

    public synchronized boolean isOcupado() {
        return ocupado;
    }

    public synchronized void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public synchronized boolean isAbierta() {
        return abierta;
    }

    public synchronized void setAbierta(boolean abierta) {
        this.abierta = abierta;
    }

    public synchronized void hacerCola(String nombre) throws InterruptedException {
         // Mientras la pollería se encuentre ocupada atendiendo un pedido --> espera, en caso contrario se ocupa
        while (this.isOcupado() && isAbierta()) {
            wait();
        }
        if (!this.isAbierta()) {
            // Si la pollería no está abierta se deja de hacer la cola
            System.err.println(String.format("El %s deja de hacer cola y se va...",nombre));
            return;
        }
        // Una vez liberada la pollería se vuelve a ocupar
        setOcupado(true);
    }

    public synchronized void siguienteCliente() {
        // Se habilita el turno cliente
        setOcupado(false);

        if (!isAbierta() && getPollos() <= 0) {
            // Se habilita el turno cliente
            setOcupado(false);
        }
        // Se avisa al siguiente cliente
        notifyAll();

    }

    public synchronized void cerrarPolleria() {
        setAbierta(false);
        notifyAll();
    }

}
