/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VC09_EJ2_farmacia;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author anaranjo
 */
public class HiloServidor implements Runnable {

    private final Socket s;
    private String nombreServidor;
    private String nombreCliente;
    private Farmacia farmacia;

    public HiloServidor(Socket socket, String nombre, Farmacia farmacia) {
        this.s = socket;
        this.nombreServidor = nombre;
        this.nombreCliente = null;
        this.farmacia = farmacia;
    }

    @Override
    public void run() {

        String linea;
        final String DESPEDIDA = "FIN";
        String medicamento;
        String cantMedicamento;
        String[] pedido = new String[2];
        String respuesta;

        try {

            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter pw = new PrintWriter(s.getOutputStream(), true);

            // El servidor recibe el nombre del cliente y le devuelve su nombre
            this.nombreCliente = br.readLine();
            pw.println(this.nombreServidor);

            // El servidor recibe el pedido del cliente y le responde
            while ((linea = br.readLine()) != null && !linea.equalsIgnoreCase(DESPEDIDA)) {
                pedido = linea.split(";");

                if (!farmacia.cerrar()) {
                    switch (Integer.parseInt(pedido[0])) {

                        case 1:
                            if (farmacia.stockMedicamento[0] - Integer.parseInt(pedido[1]) >= 0) {
                                farmacia.vender(Integer.parseInt(pedido[0]), Integer.parseInt(pedido[1]));
                                System.out.println(String.format("El %s ha realizado el pedido nº %d de %s de %s, quedan %d en stock", nombreCliente,Farmacia.numPedido, pedido[1], farmacia.nombreMedicamento[Integer.parseInt(pedido[0]) - 1], farmacia.stockMedicamento[0]));
                            } else {
                                respuesta = String.format("No hay stock suficiente del medicamento %s quedan %d unidades.", farmacia.nombreMedicamento[0], farmacia.stockMedicamento[0]);
                                System.out.println(respuesta);
                                pw.print(respuesta);
                            }
                            break;

                        case 2:
                            if (farmacia.stockMedicamento[1] - Integer.parseInt(pedido[1]) >= 0) {
                                farmacia.vender(Integer.parseInt(pedido[0]), Integer.parseInt(pedido[1]));
                                System.out.println(String.format("El %s ha realizado el pedido nº %d de %s de %s, quedan %d en stock", nombreCliente,Farmacia.numPedido, pedido[1], farmacia.nombreMedicamento[Integer.parseInt(pedido[0]) - 1], farmacia.stockMedicamento[1]));
                            } else {
                                respuesta = String.format("No hay stock suficiente del medicamento %s quedan %d unidades.", farmacia.nombreMedicamento[1], farmacia.stockMedicamento[1]);
                                System.out.println(respuesta);
                                pw.print(respuesta);
                            }
                            break;

                        case 3:
                            if (farmacia.stockMedicamento[2] - Integer.parseInt(pedido[1]) >= 0) {
                                farmacia.vender(Integer.parseInt(pedido[0]), Integer.parseInt(pedido[1]));
                                System.out.println(String.format("El %s ha realizado el pedido nº %d de %s de %s, quedan %d en stock", nombreCliente,Farmacia.numPedido, pedido[1], farmacia.nombreMedicamento[Integer.parseInt(pedido[0]) - 1], farmacia.stockMedicamento[2]));
                            } else {
                                respuesta = String.format("No hay stock suficiente del medicamento %s quedan %d unidades.", farmacia.nombreMedicamento[2], farmacia.stockMedicamento[2]);
                                System.out.println(respuesta);
                                pw.print(respuesta);
                            }
                            break;

                        default:

                            System.err.println("Pedido no permitido, no exite tal medicamento.");

                    }
                } else {
                    System.out.println(String.format("La farmacia se encuentra cerrada porque no tiene más medicamentos, el %s volverá otro día", nombreCliente));
                }
            }

            // Cierre de recursos
            s.close();
            br.close();
            pw.close();

        } catch (SocketException e) {
            System.err.println("Error de socket: " + e.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(VC09_EJ1_frasedesmotivadora.Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
