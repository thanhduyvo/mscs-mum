package mpp.ssa.domain;

/**
 * 
 */
public class Product {

    /**
     * Default constructor
     */
    public Product() {
        setProductCategory(new ProductCategory());
    }

    public Product(int productId, String productName, double unitCost) {
        setProductCategory(new ProductCategory());
        setProductId(productId);
        setProductName(productName);
        setUnitCost(unitCost);
    }

    private ProductCategory productCategory;

    private int productId;

    private String productName;

    private double unitCost;

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(double unitCost) {
        this.unitCost = unitCost;
    }
}