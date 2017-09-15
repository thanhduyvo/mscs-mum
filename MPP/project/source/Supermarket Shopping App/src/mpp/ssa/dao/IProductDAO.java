package mpp.ssa.dao;

import java.sql.SQLException;
import java.util.List;

public interface IProductDAO {

    ProductDO getProduct(String id) throws SQLException;

    List<ProductDO> getAllProducts() throws SQLException;

    List<ProductDO> getProductsByCategory(String categoryId) throws SQLException;
}