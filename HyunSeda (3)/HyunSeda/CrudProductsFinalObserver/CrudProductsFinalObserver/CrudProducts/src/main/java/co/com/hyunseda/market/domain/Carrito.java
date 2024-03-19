package co.com.hyunseda.market.domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JUAN DAVID
 */
public class Carrito {
    
    private Product productos;
    int totalArticulos;

    public Carrito(Product productos, int totalArticulos) {
        this.productos = productos;
        this.totalArticulos = totalArticulos;
    }

    public Product getProductos() {
        return productos;
    }

    public void setProductos(Product productos) {
        this.productos = productos;
    }

    public int getTotalArticulos() {
        return totalArticulos;
    }

    public void setTotalArticulos(int totalArticulos) {
        this.totalArticulos = totalArticulos;
    }
    
    
}
