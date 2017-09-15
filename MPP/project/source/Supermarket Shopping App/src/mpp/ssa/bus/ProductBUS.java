package mpp.ssa.bus;

import mpp.ssa.dao.ProductCategoryDO;
import mpp.ssa.dao.ProductDAO;
import mpp.ssa.dao.ProductDO;
import mpp.ssa.domain.Product;
import mpp.ssa.domain.ProductCategory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductBUS implements IProductBUS {

    private ProductDAO productDAO;

    private ProductBUS() {
        productDAO = new ProductDAO();
    }

    private static ProductBUS productBUS;

    public static ProductBUS getProductBUS() {
        if(productBUS == null) {
            productBUS = new ProductBUS();
        }

        return productBUS;
    }

    @Override
    public Product getProductDetails(String id) {

        try {
            ProductDO product = productDAO.getProduct(id);
            if(product != null) {
                return new Product(product.getId(), product.getProductName(), product.getUnitCost());
            }
        } catch (SQLException ex) {
        }

        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        try {
            List<ProductDO> productDOList = productDAO.getAllProducts();
            if(productDOList != null) {
                List<Product> products = new ArrayList<Product>();
                for(ProductDO productDO : productDOList) {
                    products.add(new Product(productDO.getId(), productDO.getProductName(), productDO.getUnitCost()));
                }
                return products;
            }
        } catch (SQLException ex) {
        }

        return null;
    }

    @Override
    public List<Product> getProductsByCategory(String categoryId) {
        try {
            List<ProductDO> productDOList = productDAO.getProductsByCategory(categoryId);
            if(productDOList != null) {
                List<Product> products = new ArrayList<Product>();
                for(ProductDO productDO : productDOList) {
                    products.add(new Product(productDO.getId(), productDO.getProductName(), productDO.getUnitCost()));
                }
                return products;
            }
        } catch (SQLException ex) {
        }

        return null;
    }
}