package mpp.ssa.bus;

import java.util.*;
import mpp.ssa.domain.*;

public interface IProductBUS {

    Product getProductDetails(String id);

    List<Product> getAllProducts();

    List<Product> getProductsByCategory(String categoryId);
}
