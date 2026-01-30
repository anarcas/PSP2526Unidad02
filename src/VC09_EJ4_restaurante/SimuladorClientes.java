/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package VC09_EJ4_restaurante;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author anaranjo
 */
public class SimuladorClientes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        // Declaración de variables
        Scanner teclado = new Scanner(System.in);
        int opcion;
        int numClientes;
        Thread hiloCliente;
        Thread[] listaClientes;
        
        // Procesamiento
        try {
            do {
                // Menú
                System.out.println("Menú:");
                System.out.println("1. Lanzar clientes o solicitudes al restaurante.");
                System.out.println("2. Salir del programa.");
                System.out.print("Elige una opción: ");

                opcion = teclado.nextInt();

                switch (opcion) {

                    case 1:
                        // Se solicita al usuario el número de clientes
                        System.out.print("¿Cuántos clientes desea lanzar? ");
                        numClientes = teclado.nextInt();
                        // Se instancia una lista donde se almacenarán los hilos clientes
                        listaClientes = new Thread[numClientes];

                        // Bucle for para iniciar los hilos clientes
                        for (int i = 0; i < listaClientes.length; i++) {
                            hiloCliente = new Thread(new HiloCliente());
                            listaClientes[i] = hiloCliente;
                            hiloCliente.start();
                        }

                        // Los hilos clientes se esperan
                        for (int i = 0; i < listaClientes.length; i++) {
                            listaClientes[i].join();
                        }

                        break;

                    case 2:
                        System.out.println("Fin del programa.");
                        break;

                    default:
                        System.err.println("Opción no válida.");

                }
            } while (opcion != 2);
            
        } catch (InputMismatchException e) {
            System.err.println("El usuario ha introducido un valor incorrecto. Error: " + e);
            // Limpieza del buffer
            teclado.nextInt();
            // Se alimenta no admitido para volver al menú
            opcion = -1;
        } catch (InterruptedException e) {
            System.err.println("Hilo interrumpido inesperadamente. Error: " + e);
            Thread.currentThread().interrupt();
        }

    }
}
