package co.com.hyunseda.market.domain;

/**
 *
 * @author Libardo Pantoja, Julio A. Hurtado
 */
public class Category {
    private int categoryId;
    private String categoryName;

    public Category(int categoryId, String name) {
        this.categoryId = categoryId;
        this.categoryName = name;
    }

    public Category() {
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String name) {
        this.categoryName = name;
    }
}
