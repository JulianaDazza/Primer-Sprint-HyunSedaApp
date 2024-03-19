/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unicauca.pay.crudproducts.business;

import com.unicauca.pay.crudproducts.common.PayCrudProducts;
import com.unicauca.pay.crudproducts.common.interfaces.iPayPlugin;
import com.unicauca.pay.crudproducts.manager.PayManager;

/**
 *
 * @author JUAN DAVID
 */
public class PayService {
    public boolean realizarPaySimulated(PayCrudProducts pagoData) throws Exception {

        String metodoPago = pagoData.getPlataformaPago();
        PayManager manager = PayManager.getInstance();
        iPayPlugin plugin = manager.getDeliveryPlugin(metodoPago);

        if (plugin == null) {
            throw new Exception("No hay un plugin disponible para el metodo de pago indicado: " + metodoPago);
        }

        return plugin.paySimulated(pagoData);
    }
}
