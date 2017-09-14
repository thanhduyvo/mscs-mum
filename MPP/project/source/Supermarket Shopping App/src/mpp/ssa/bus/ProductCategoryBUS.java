package mpp.ssa.bus;

import mpp.ssa.dao.ProductCategoryDAO;
import mpp.ssa.dao.ProductCategoryDO;
import mpp.ssa.dao.ProductDO;
import mpp.ssa.dao.UserDAO;
import mpp.ssa.domain.Product;
import mpp.ssa.domain.ProductCategory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryBUS implements IProductCategoryBUS {

    private ProductCategoryDAO productCategoryDAO;

    private ProductCategoryBUS() {
        productCategoryDAO = new ProductCategoryDAO();
    }

    private static ProductCategoryBUS productCategoryBUS;

    public static ProductCategoryBUS getProductCategoryBUS() {
        if(productCategoryBUS == null) {
            productCategoryBUS = new ProductCategoryBUS();
        }

        return productCategoryBUS;
    }

    @Override
    public List<ProductCategory> getAllProductCategories() {

        try {
            List<ProductCategoryDO> productCategoryDOList = productCategoryDAO.getAllProductCategories();
            if(productCategoryDOList != null) {
                List<ProductCategory> productCategories = new ArrayList<ProductCategory>();
                productCategories.add(new ProductCategory(0, "All"));
                for(ProductCategoryDO productCategoryDO : productCategoryDOList) {
                    productCategories.add(new ProductCategory(productCategoryDO.getId(), productCategoryDO.getCategoryName()));
                }
                return productCategories;
            }
        } catch (SQLException ex) {
        }

        return null;
    }
}