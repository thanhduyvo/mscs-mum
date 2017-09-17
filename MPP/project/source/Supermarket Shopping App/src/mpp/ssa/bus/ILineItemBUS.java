package mpp.ssa.bus;

import mpp.ssa.domain.LineItem;
import java.util.List;

public interface ILineItemBUS {

    List<LineItem> getLineItemsByOrder(String orderId);
}