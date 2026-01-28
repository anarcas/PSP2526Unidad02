/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package VC09_EJ3_hermanos_pollos;

import java.net.Socket;
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
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here

        // Declaración de variables
        Thread hiloCliente;
        Thread[] listaClientes = null;
        String nombreCliente;
        int numClientes = 0;
        Socket s;

        Scanner teclado = new Scanner(System.in);
        int opcion = 0;

        // Menú
        while (opcion != 2) {

            System.out.println("\nMenú:");
            System.out.println("1. Similar peticiones clientes");
            System.out.println("2. Terminar programa");
            System.out.print("Seleccione una opcion: ");
            try {
                opcion = teclado.nextInt();
            } catch (InputMismatchException e) {
                System.err.println("Solo se aceptan número enteros. Error: " + e);
                // Se limpia el buffer
                teclado.nextLine();
            }

            switch (opcion) {

                case 1:

                    System.out.print("¿Cuántos clientes desea simular? \n");

                    // Se solicitan el número de clientes a simular
                    do {
                        try {
                            numClientes = teclado.nextInt();
                            if (numClientes < 1) {
                                System.err.println("Error, se debe introducir un número entero positivo");
                            }
                        } catch (InputMismatchException e) {
                            System.err.println("Solo se aceptan número enteros. Error: " + e);
                            // Se limpia el buffer
                            teclado.nextLine();
                            // Se reinicia la variable numClientes para volver al bucle while
                            numClientes = 0;
                        }
                    } while (numClientes < 1);

                    // Iniciar la lista de clientes
                    listaClientes = new Thread[numClientes];

                    // Se inician los hilos clientes solicitados con su nombre
                    for (int i = 0; i < numClientes; i++) {
                        nombreCliente = String.format("Cliente%d", i + 1);
                        hiloCliente = new Thread(new HiloCliente(nombreCliente));
                        listaClientes[i] = hiloCliente;
                        hiloCliente.start();
                    }
 // Los hilos clientes se esperan
            for (int i = 0; i < listaClientes.length; i++) {
                listaClientes[i].join();
            }
                    break;

                case 2:

                    System.out.println("Fin del programa");

                    break;

                default:

                    System.out.println("Opción no permitida.");

            }

           
            System.out.println("\nSimulación de clientes terminada.");
        }

        // Cierre de recursos
        teclado.close();

    }

}
