package mpp.ssa.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDAO implements IProductCategoryDAO {

    private DbConnection dbConnection;

    public ProductCategoryDAO() {
        dbConnection = new DbConnection();
    }

    private ProductCategoryDO extractProductCategoryFromResultSet(ResultSet rs) throws SQLException {
        ProductCategoryDO productCategory = new ProductCategoryDO();
        productCategory.setId( rs.getString("id"));
        productCategory.setCategoryName( rs.getString("categoryName"));
        return productCategory;
    }

    @Override
    public List<ProductCategoryDO> getAllProductCategories() throws SQLException {
        Connection connection = dbConnection.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ProductCategory");
            List<ProductCategoryDO> productCategories = new ArrayList<ProductCategoryDO>();
            while(rs.next())
            {
                ProductCategoryDO productCategory = extractProductCategoryFromResultSet(rs);
                productCategories.add(productCategory);
            }
            return productCategories;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}