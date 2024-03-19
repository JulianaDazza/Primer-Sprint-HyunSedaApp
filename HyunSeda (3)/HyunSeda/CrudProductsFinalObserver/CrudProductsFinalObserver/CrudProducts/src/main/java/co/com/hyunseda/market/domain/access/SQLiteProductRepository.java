package co.com.hyunseda.market.domain.access;

import co.com.hyunseda.market.domain.Category;
import co.com.hyunseda.market.domain.Product;
import co.com.hyunseda.market.service.ProductService;
import co.com.hyunseda.market.service.CarritoService;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ACER
 */
public class SQLiteProductRepository implements IProductRepository {
    
    private Connection conn;

    public SQLiteProductRepository() {
        initDatabase();
    }
    
    
    @Override
    public boolean save(Product newProduct) {
   
        /*guardar el producto en la tabla prodctos y la tabla intermedia*/
        
        try {
            if (newProduct == null || newProduct.getName().isEmpty()|| newProduct.getDescription().isEmpty() || newProduct.getPrice() <= 0) {
                return false;
            }
                
            String sql = "INSERT INTO products (productId,productName, productDescription, productPrice) VALUES (?,?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, newProduct.getProductId());
            pstmt.setString(2, newProduct.getName());
            pstmt.setString(3, newProduct.getDescription());
            pstmt.setDouble(4, newProduct.getPrice());
            pstmt.executeUpdate();
            
            /*Consultar el ultimo id insertado y lo captura para buscarlo
            Luego que ese id se mande a la tabla intermedia producto_categoria.*/
            
            return true;
        } catch (SQLException ex) {
            //ex.printStackTrace();
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    @Override
    public boolean saveProductCategory(Product newProduct, Category category) {
        try {
            /*if (newProduct == null || newProduct.getName().isEmpty()|| newProduct.getDescription().isEmpty() || newProduct.getPrice() <= 0) {
                return false;   
            }*/
            
            String sql1 = "INSERT INTO products_category (productId,categoryId) VALUES (?, ?)";
            PreparedStatement pstmt1 = conn.prepareStatement(sql1);
            pstmt1.setLong(1, newProduct.getProductId());
            pstmt1.setLong(2, category.getCategoryId());
            pstmt1.executeUpdate();
            
            /*Otro string sql que busca cual fue el id que le toca a ese producto.*/

            return true;
        } catch (SQLException ex) {
            //ex.printStackTrace();
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
            }
    }
    
    
    @Override
    public List<Product> list() {
    List<Product> products = new ArrayList<>();
        try {

            String sql = "SELECT * FROM products";
            //this.connect();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Product newProduct = new Product();
                newProduct.setProductId(rs.getInt("productId"));
                newProduct.setName(rs.getString("productName"));
                newProduct.setPrice(rs.getInt("productPrice"));
                newProduct.setDescription(rs.getString("productDescription"));
                

                products.add(newProduct);
            }
            //this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }

    @Override
    public boolean edit(long id, Product product) {
    try {
            //Validate product
            if (id <= 0 || product == null) {
                return false;
            }
            //this.connect();

            String sql = "UPDATE  products "
                    + "SET productName=?, productDescription=?, productPrice=? "
                    + "WHERE productId = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, product.getName());
            pstmt.setString(2, product.getDescription());
            pstmt.setDouble(3, product.getPrice());
            pstmt.setLong(4, id);
            pstmt.executeUpdate();
            //this.disconnect();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
    try {
            //Validate product
            if (id <= 0) {
                return false;
            }
            //this.connect();

            String sql = "DELETE FROM products "
                    + "WHERE productId = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
            //this.disconnect();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Product findById(Long id) {
        try {

                String sql = "SELECT * FROM products  "
                        + "WHERE productId = ?";

                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setLong(1, id);

                ResultSet res = pstmt.executeQuery();

                if (res.next()) {
                    Product prod = new Product();
                    prod.setProductId(res.getInt("productId"));
                    prod.setName(res.getString("productName"));
                    prod.setDescription(res.getString("productDescription"));
                    prod.setPrice(res.getDouble("productPrice"));
                    return prod;
                } else {
                    return null;
                }
                //this.disconnect();

            } catch (SQLException ex) {
                Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }

    
    
    @Override
    public Product findPorName(String prmName)
    {
        Product retProduct = new Product();
        String sql = "SELECT * FROM products WHERE productName LIKE ?";
        try(PreparedStatement prmStm = conn.prepareStatement(sql))
        {
            prmStm.setString(1, prmName);
            ResultSet rs = prmStm.executeQuery();

            
            if(rs.next())
            {
                retProduct.setProductId(rs.getInt("productId"));
                retProduct.setName(rs.getString("productName"));
                retProduct.setDescription(rs.getString("productDescription"));
                retProduct.setPrice(rs.getDouble("productPrice"));
            }

        }catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retProduct;
    }

    @Override
    public List<Product> findByName(String name) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE productName LIKE ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + name + "%");
            ResultSet rs = pstmt.executeQuery();
            
          while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("productId"));
                product.setName(rs.getString("productName"));
                product.setDescription(rs.getString("productDescription"));
                product.setPrice(rs.getDouble("productPrice"));
                products.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return products;
    }

    @Override
    public List<Product> findByCategory(Category category) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products_category WHERE categoryId = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, category.getCategoryId());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("productId"));
                product.setName(rs.getString("productName"));
                product.setDescription(rs.getString("productDescription"));
                product.setPrice(rs.getInt("productPrice"));
                products.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return products;
    }
    private void initDatabase() {

        String sql = "CREATE TABLE IF NOT EXISTS products (\n"
                + "	productId integer PRIMARY KEY,\n"
                + "	productName text NOT NULL,\n"
                + "	productDescription text NULL,\n"
                + " productPrice real NOT NULL\n"
                + ");";

        // SQL statement for creating a new table

        String sql1  = "CREATE TABLE IF NOT EXISTS products_category (\n"
                + "    productId INTEGER,\n"
                + "    categoryId INTEGER,\n"
                + "    FOREIGN KEY (productId) REFERENCES products(productId),\n"
                + "    FOREIGN KEY (categoryId) REFERENCES categories(categoryId),\n"
                + "    PRIMARY KEY (productId, categoryId)\n"
                + ");";
        try { 
            this.connect();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            stmt.execute(sql1);
            //this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void connect() {
    // SQLite connection string
        //String url = "jdbc:sqlite:./myDatabase.db"; //Para Linux/Mac
        //String url = "jdbc:sqlite:C:/sqlite/db/myDatabase.db"; //Para Windows
        String url = "jdbc:sqlite::memory:";

        try {
            conn = DriverManager.getConnection(url);

        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void disconnect() {
        
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
