package mpp.ssa.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by 986033 on 9/13/2017.
 */
public interface IProductCategoryDAO {

    List<ProductCategoryDO> getAllProductCategories() throws SQLException;
}
