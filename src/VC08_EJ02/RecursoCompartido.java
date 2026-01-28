/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VC08_EJ02;

/**
 *
 * @author anaranjo
 */
public class RecursoCompartido {

    private int contadorClientes = 0;

    public synchronized int incrementarContador() {
        return contadorClientes++;
    }
}
