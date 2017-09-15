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

    public ProductCategory(String categoryId, String categoryName ) {
        setCategoryId(categoryId);
        setCategoryName(categoryName);
    }

    private String categoryId;

    private String categoryName;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}