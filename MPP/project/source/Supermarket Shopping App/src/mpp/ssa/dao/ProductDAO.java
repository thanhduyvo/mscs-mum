package mpp.ssa.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements IProductDAO {

    private DbConnection dbConnection;

    public ProductDAO() {
        dbConnection = new DbConnection();
    }

    private ProductDO extractProductFromResultSet(ResultSet rs) throws SQLException {
        ProductDO product = new ProductDO();
        product.setId( rs.getInt("id"));
        product.setProductCategoryId( rs.getInt("productCategoryId"));
        product.setProductName( rs.getString("productName"));
        product.setUnitCost(rs.getDouble("unitCost"));
        return product;
    }

    @Override
    public ProductDO getProduct(int id) throws SQLException {
        Connection connection = dbConnection.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Product WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                return extractProductFromResultSet(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            connection.close();
        }

        return null;
    }

    @Override
    public List<ProductDO> getAllProducts() throws SQLException {
        Connection connection = dbConnection.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Product");
            List<ProductDO> products = new ArrayList<ProductDO>();
            while(rs.next())
            {
                ProductDO product = extractProductFromResultSet(rs);
                products.add(product);
            }
            return products;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ProductDO> getProductsByCategory(int categoryId) throws SQLException {
        Connection connection = dbConnection.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Product WHERE productCategoryId=?");
            ps.setInt(1, categoryId);
            ResultSet rs = ps.executeQuery();
            List<ProductDO> products = new ArrayList<ProductDO>();
            while(rs.next())
            {
                ProductDO product = extractProductFromResultSet(rs);
                products.add(product);
            }
            return products;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}