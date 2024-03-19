/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unicauca.pay.crudproducts.plugin.stripe;

import com.unicauca.pay.crudproducts.common.PayCrudProducts;
import com.unicauca.pay.crudproducts.common.interfaces.iPayPlugin;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author JUAN DAVID
 */
public class PluginStripe implements iPayPlugin{
    String filePath;
    @Override
    public boolean paySimulated(PayCrudProducts pago) {
        this.filePath = "datosCuentaStripe.txt"; // Nombre del archivo sin la ruta completa

        double saldo=obtenerSaldoCuenta(pago.getCodigoCuenta()); // Código de cuenta buscado
        if(saldo>pago.getTotalPago()){
            actualizarSaldo(pago.getCodigoCuenta(),saldo-pago.getTotalPago());
            return true;
        }else{
            return false;
        }
        
        
    }
    
    private double obtenerSaldoCuenta(String searchedCode){
        try (BufferedReader br = new BufferedReader(new FileReader(this.filePath))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";"); // Dividir la línea en partes usando el punto y coma como separador
                if (partes.length == 2) { // Asegurarse de que hay dos partes (código de cuenta y saldo)
                    String codigoCuenta = partes[0].trim(); // Código de cuenta (primera parte)
                    if (codigoCuenta.equals(searchedCode)) {
                        String saldo = partes[1].trim(); // Saldo asociado al código de cuenta (segunda parte)
                        return Double.parseDouble(saldo);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    void actualizarSaldo(String searchedCode, double nuevoSaldo){
        // Lee todo el contenido del archivo
        StringBuilder contenido = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                contenido.append(linea).append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Reemplaza el saldo antiguo con el nuevo saldo
        String contenidoActualizado = contenido.toString().replaceAll(searchedCode + ";.*", searchedCode + ";" + nuevoSaldo);

        // Escribe el contenido actualizado de vuelta al archivo
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write(contenidoActualizado);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
