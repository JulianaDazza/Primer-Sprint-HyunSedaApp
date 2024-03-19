/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.unicauca.pay.crudproducts.common.interfaces;

import com.unicauca.pay.crudproducts.common.PayCrudProducts;

/**
 *
 * @author JUAN DAVID
 */
public interface iPayPlugin {
    /**
     * Establece el costo de envío en dólares.
     *
     * @param pago envío
     * @return costo del envío
     */
    boolean paySimulated(PayCrudProducts pago);
}
