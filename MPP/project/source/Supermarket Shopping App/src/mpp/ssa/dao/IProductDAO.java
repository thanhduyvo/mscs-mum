package mpp.ssa.dao;

import java.sql.SQLException;
import java.util.List;

public interface IProductDAO {

    ProductDO getProduct(int id) throws SQLException;

    List<ProductDO> getAllProducts() throws SQLException;

    List<ProductDO> getProductsByCategory(int categoryId) throws SQLException;
}