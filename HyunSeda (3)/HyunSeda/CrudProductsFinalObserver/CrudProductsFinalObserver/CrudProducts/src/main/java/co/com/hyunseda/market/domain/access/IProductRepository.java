package co.com.hyunseda.market.domain.access;

import co.com.hyunseda.market.domain.Category;
import co.com.hyunseda.market.domain.Product;
import java.util.List;

/**
 *
 * @author ACER
 */
public interface IProductRepository {
    
    boolean save(Product newProduct);
    boolean saveProductCategory(Product newProduct, Category category);
    List<Product> list();
    List<Product> findByName(String name);
    List<Product> findByCategory(Category category); // Nuevo método
    boolean edit(long id, Product product);
    boolean delete(Long id);
    Product findById(Long id);
    Product findPorName(String prmName);
    
}
