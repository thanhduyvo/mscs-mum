package mpp.ssa.domain;

/**
 * 
 */
public class ProductCategory {

    /**
     * Default constructor
     */
    public ProductCategory() {
    }

    private int categoryId;

    private String categoryName;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}