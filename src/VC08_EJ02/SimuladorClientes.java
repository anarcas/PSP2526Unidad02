/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package VC08_EJ02;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author anaranjo
 */
public class SimuladorClientes {

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        List<Thread> lista = new ArrayList<>();
        int opcion = 0;

        while (opcion != 2) {
            System.out.println("\nMenú:");
            System.out.println("1. Simular peticiones clientes");
            System.out.println("2. Terminar programa");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            
            if (opcion == 1) {
                System.out.print("¿Cuántos clientes desea simular?: ");
                int numCliente = scanner.nextInt();
                
                for (int i = 0; i < numCliente; i++) {
                    lista.add(new Thread(new Cliente()));
                    lista.get(i).start();
                }

            } else if (opcion == 2) {
                System.out.println("Programa terminado.");
            } else {
                System.out.println("Opción no válida.");
            }

            for (Thread hilo : lista) {
                hilo.join();
            }
        }
    }
}
