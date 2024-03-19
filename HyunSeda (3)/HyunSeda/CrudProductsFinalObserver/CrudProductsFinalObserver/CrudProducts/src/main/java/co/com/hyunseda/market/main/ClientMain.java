package co.com.hyunseda.market.main;

import co.com.hyunseda.market.domain.Category;
import co.com.hyunseda.market.domain.Product;
import co.com.hyunseda.market.domain.access.Factory;
import co.com.hyunseda.market.presentation.GUIAddProduct;
import co.com.hyunseda.market.service.ProductService;
import co.com.hyunseda.market.domain.access.SQLiteCategoryRepository;
import co.com.hyunseda.market.domain.access.SQLiteProductRepository;
import co.com.hyunseda.market.domain.access.IProductRepository;
import co.com.hyunseda.market.domain.access.ICategoryRepository;
import co.com.hyunseda.market.presentation.GUIMainMenu;
import co.com.hyunseda.market.presentation.GUIUserMen;
import co.com.hyunseda.market.service.CarritoService;
import co.com.hyunseda.market.service.CategoryService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Libardo Pantoja
 */
public class ClientMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {  
        // Le decimos a la fábrica que nos de el repositorio por defecto
        IProductRepository productR = Factory.getInstance().getProductRepository("sqlite");
        ICategoryRepository categoryR = Factory.getInstance().getCategoryRepository("sqlite");
        
        CategoryService categoryS = new CategoryService(categoryR);
        ProductService productS = new ProductService(productR, categoryS);
        

        Category newCategory = new Category(1, "Chales");
        categoryS.saveCategory(newCategory);
        newCategory = new Category(2, "Ruanas");
        categoryS.saveCategory(newCategory);
        newCategory = new Category(3, "Ponchos");
        categoryS.saveCategory(newCategory);
        newCategory = new Category(4, "Bufandas");
        categoryS.saveCategory(newCategory);
        newCategory = new Category(5, "Aretes");
        categoryS.saveCategory(newCategory);
        newCategory = new Category(6, "Pañoletas");
        categoryS.saveCategory(newCategory);
        
        List<String> categoryNames = new ArrayList();
        categoryNames.add("Aretes");
        categoryNames.add("Ponchos");
        
        productS.saveProduct(1,"Jugo","hermoso jugo", 1100, categoryNames);
        productS.saveProduct(2,"Aretes Rojos", "Aretes completamente rojos", 25000, categoryNames);
        productS.saveProduct(3,"Poncho verde", "Poncho carnavalero unisex", 125000, categoryNames);
        productS.saveProduct(4,"Aretes Verdes", "Aretes completamente verdes", 25000, categoryNames);
        GUIUserMen userForm = new GUIUserMen(productS,categoryS);
        userForm.setVisible(true);
        //GUIMainMenu menuForm = new GUIMainMenu(productS,categoryS);
        //menuForm.setVisible(true);
        
        /*Product newProduct = new Product(1, "Product One", 10d);
        service.saveProduct(newProduct);

        newProduct = new Product(2, "Product Two", 50);
        service.saveProduct(newProduct);

        for (Product p : service.listProducts()) {
            System.out.println(p);
        }*/
          
        
    }
    
}
