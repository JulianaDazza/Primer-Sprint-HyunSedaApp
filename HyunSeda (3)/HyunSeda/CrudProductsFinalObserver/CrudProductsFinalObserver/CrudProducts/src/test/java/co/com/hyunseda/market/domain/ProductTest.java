package co.com.hyunseda.market.domain;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
  * @author Libardo, Julio
 */

public class ProductTest
{
    ProductTest(){
    }
    
    @Test
    public void noParametrizedConstructor()
    {
        Long expectedId = 0L;
        String expectedName = null;
        String expectedDescription = null;
        double expectedPrice = 0;
        
        Product testProduct = new Product();
        
        assertEquals(testProduct.getProductId(),expectedId);
        assertEquals(testProduct.getName(), expectedName);
        assertEquals(testProduct.getDescription(),expectedDescription);
        assertEquals(testProduct.getPrice(),expectedPrice);
    }
    @Test
    public void ParametrizedConstructor()
    {
        Long expectedId = 15L;
        String expectedName = "Ruana Verde";
        String expectedDescription = "";
        double expectedPrice = 15.0f;
        
        Product testProduct = new Product(15,"Ruana Verde","",15.0f);
        
        assertEquals(testProduct.getProductId(),expectedId);
        assertEquals(testProduct.getName(), expectedName);
        assertEquals(testProduct.getDescription(),expectedDescription);
        assertEquals(testProduct.getPrice(),expectedPrice);
    }
}