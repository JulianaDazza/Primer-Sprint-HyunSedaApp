package co.com.hyunseda.market.domain;

import java.util.List;

/**
 *
 * @author Libardo Pantoja, Julio Hurtado
 */
public class Product {

    private int productId;

    private String name;
    
    private String description;
    
    private double price;
    
    private double stock;
    
    private List<Category> category;
    
    private Category objCategory;
    
    private Location location;
    
    private User user;

    public Product(int productId, String name, String description, double price) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Product() {
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
    }

    public Category getObjCategory() {
        return objCategory;
    }

    public void setObjCategory(Category objCategory) {
        this.objCategory = objCategory;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = 1;
    }
}
