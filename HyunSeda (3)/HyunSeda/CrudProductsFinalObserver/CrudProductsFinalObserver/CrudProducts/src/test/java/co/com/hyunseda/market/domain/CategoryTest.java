package co.com.hyunseda.market.domain;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
  * @author Libardo, Julio
 */

public class CategoryTest
{
    CategoryTest(){
    }
    
    @Test
    public void noParametrizedConstructorTest()
    {
        Category objCategory = new Category();
        Long testCategoryId = 0L;
        String testCategoryName = null;
        
        assertEquals(objCategory.getCategoryId(),testCategoryId);
        assertEquals(objCategory.getCategoryName(),testCategoryName);
    }
    
    @Test
    public void ParametrizedConstructorTest()
    {
        int testCategoryId = 1;
        String testCategoryName = "Aretes";
        Category objCategory = new Category(testCategoryId,testCategoryName);
      
        
        assertEquals(objCategory.getCategoryId(),testCategoryId);
        assertEquals(objCategory.getCategoryName(),testCategoryName);
    }
    
}