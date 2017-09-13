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

    public ProductCategoryBUS() {
        productCategoryDAO = new ProductCategoryDAO();
    }

    @Override
    public List<ProductCategory> getAllProductCategories() {

        try {
            List<ProductCategoryDO> productCategoryDOList = productCategoryDAO.getAllProductCategories();
            if(productCategoryDOList != null) {
                List<ProductCategory> productCategories = new ArrayList<ProductCategory>();
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