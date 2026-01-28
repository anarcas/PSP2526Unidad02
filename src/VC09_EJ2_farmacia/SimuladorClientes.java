/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package VC09_EJ2_farmacia;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author anaranjo
 */
public class SimuladorClientes {

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        Scanner teclado = new Scanner(System.in);
        int opcion = 0;
        int numCliente;
        Thread hiloCliente;
        String nombreCliente;
        List<Thread> hilosCliente = new ArrayList<>();

        while (opcion != 2) {
            System.out.println("\nMenú:");
            System.out.println("1. Simular peticiones clientes");
            System.out.println("2. Terminar programa");
            System.out.print("Seleccione una opción: ");

            opcion = teclado.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("¿Cuántos clientes desea simular?: ");
                    numCliente = teclado.nextInt();

                    for (int i = 0; i < numCliente; i++) {
                        nombreCliente = String.format("Cliente %d", i + 1);
                        hiloCliente = new Thread(new Cliente(nombreCliente), nombreCliente);
                        hilosCliente.add(hiloCliente);
                        hiloCliente.start();
                    }

                    break;

                case 2:
                    System.out.println("Programa terminado.");

                    break;

                default:
                    System.out.println("Opción no válida.");
            }
            
            // Los hilos se esperan antes de finalizar la simulación de clientes
            for (Thread hilo : hilosCliente) {
                hilo.join();
            }
            System.out.println("Simulación de clientes terminada.");
            // Se limpia la lista para reutilizarla
            hilosCliente.clear();
        }

        System.out.println("Programa finalizado por el usuario.");
        
        // Cierre de recursos
        teclado.close();
        
    }
    
}
