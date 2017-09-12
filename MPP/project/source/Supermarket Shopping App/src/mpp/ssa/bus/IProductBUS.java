package mpp.ssa.bus;

import java.util.*;
import mpp.ssa.domain.*;

public interface IProductBUS {

    Product getProductDetails(int id);

    List<Product> getProductsByCategory(String category);
}
