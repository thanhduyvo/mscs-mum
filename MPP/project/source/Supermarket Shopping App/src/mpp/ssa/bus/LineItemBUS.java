package mpp.ssa.bus;

import mpp.ssa.dao.LineItemDAO;
import mpp.ssa.dao.LineItemDO;
import mpp.ssa.domain.LineItem;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LineItemBUS implements ILineItemBUS {

    private LineItemDAO lineItemDAO;

    public LineItemBUS() {
        lineItemDAO = new LineItemDAO();
    }

    private static LineItemBUS lineItemBUS;

    public static LineItemBUS getLineItemBUS() {
        if(lineItemBUS == null) {
            lineItemBUS = new LineItemBUS();
        }

        return lineItemBUS;
    }

    @Override
    public List<LineItem> getLineItemsByOrder(String orderId) {
        try {
            List<LineItemDO> lineItemDOList = lineItemDAO.getLineItemsByOrder(orderId);
            if(lineItemDOList != null) {
                List<LineItem> lineItems = new ArrayList<LineItem>();
                for(LineItemDO lineItemDO : lineItemDOList) {
                    lineItems.add(new LineItem(
                            lineItemDO.getId(),
                            lineItemDO.getProductName(),
                            lineItemDO.getQuantity(),
                            lineItemDO.getUnitCost(),
                            lineItemDO.getSubtotal()));
                }
                return lineItems;
            }
        } catch (SQLException ex) {
        }

        return null;
    }
}