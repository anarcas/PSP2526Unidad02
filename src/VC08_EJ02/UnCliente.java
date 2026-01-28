/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VC08_EJ02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author anaranjo
 */
public class UnCliente {

    public static void main(String[] args) throws IOException, InterruptedException {
        
        Socket s = new Socket("localhost", 12349);

        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintWriter pw = new PrintWriter(s.getOutputStream(), true);

        pw.println("Hola");
        
        for (int i = 0; i < 10; i++) {
            System.out.println("El servidor dice: " + br.readLine());
            pw.println("Hola");
        }

        System.out.println("Adios");
        pw.println("Adios");
        br.readLine();

        br.close();
        pw.close();
        s.close();
    }
}
