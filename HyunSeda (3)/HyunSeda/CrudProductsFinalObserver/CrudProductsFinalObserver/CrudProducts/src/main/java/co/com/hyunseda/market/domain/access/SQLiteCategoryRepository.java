package co.com.hyunseda.market.domain.access;

import co.com.hyunseda.market.domain.Category;
import co.com.hyunseda.market.domain.Product;
import co.com.hyunseda.market.service.ProductService;
//import co.com.hyunseda.market.domain.access.ICategoryRepository;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ACER
 */
public class SQLiteCategoryRepository implements ICategoryRepository{
    
    private Connection conn;
    
    public SQLiteCategoryRepository() {
        initDatabase();
    }

    @Override
     public Category findCategoryId(String categoryName){
        
        try {

                String sql1 = "SELECT categoryId, categoryName FROM categories "
                        + "WHERE categoryName = ?";

                PreparedStatement pstmt1 = conn.prepareStatement(sql1);
                pstmt1.setString(1, categoryName);

                ResultSet res = pstmt1.executeQuery();

                if (res.next()) {
                    Category cat = new Category();
                    cat.setCategoryId(res.getInt("categoryId"));
                    cat.setCategoryName(res.getString("categoryName"));
                    return cat;
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
    public boolean save(Category category) {
        String sql = "INSERT INTO categories (categoryId,categoryName) VALUES (?,?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, category.getCategoryId());
            pstmt.setString(2, category.getCategoryName());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            // Manejar la excepción adecuadamente (log, relanzar, etc.)
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    @Override
    public List<Category> list() {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM categories";

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Category category = new Category();
                category.setCategoryId(rs.getInt("categoryId"));
                category.setCategoryName(rs.getString("name"));
                categories.add(category);
            }
        } catch (SQLException ex) {
            // Manejar la excepción adecuadamente (log, relanzar, etc.)
             Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return categories;
    }

    @Override
    public boolean edit(long categoryId, Category category) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(Long categoryId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Category findById(Long categoryId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Category findByName(String categoryName) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    private void initDatabase() {
    // SQL statement for creating a new table
        String sql1 = "CREATE TABLE IF NOT EXISTS categories (\n"
                + "	categoryId integer PRIMARY KEY,\n"
                + "	categoryName text NOT NULL\n"
                + ");";
        try {
            this.connect();
            Statement stmt = conn.createStatement();
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
