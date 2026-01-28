/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VC09_EJ2_farmacia;

/**
 *
 * @author anaranjo
 */
public class Farmacia {

    String [] nombreMedicamento= {"paracetamol","ibuprofeno","vitamina C"};
    int [] stockMedicamento = {10,10,10};
    String nombreFarmacia = "Viva";
    public static int numPedido=0;

    public synchronized void vender(int medicamento, int cantidad) {

        String mensaje;

        switch (medicamento) {

            case 1:
                stockMedicamento[0] -= cantidad;
                mensaje = String.format("El %s ha vendido %d %s y quedan %d en stock", Thread.currentThread().getName(), cantidad, nombreMedicamento[0], stockMedicamento[0]);
                System.out.println(mensaje);
                numPedido++;
                break;

            case 2:
                stockMedicamento[1] -= cantidad;
                mensaje = String.format("El %s ha vendido %d %s y quedan %d en stock", Thread.currentThread().getName(), cantidad, nombreMedicamento[1], stockMedicamento[1]);
                System.out.println(mensaje);
                numPedido++;
                break;

            case 3:
                stockMedicamento[2] -= cantidad;
                mensaje = String.format("El %s ha vendido %d %s y quedan %d en stock", Thread.currentThread().getName(), cantidad, nombreMedicamento[2], stockMedicamento[2]);
                System.out.println(mensaje);
                numPedido++;
                break;

            default:
                System.err.println(String.format("Medicamento inexistente en la farmacia %s", nombreFarmacia));

        }
    }
        public boolean cerrar(){
            if (stockMedicamento[0]==0 && stockMedicamento[1]==0 && stockMedicamento[2]==0){
                return true;
            }
        return false;
        }

    }


