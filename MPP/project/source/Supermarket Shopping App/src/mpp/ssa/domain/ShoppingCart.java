package mpp.ssa.domain;

import java.util.*;

/**
 * 
 */
public class ShoppingCart {

    /**
     * Default constructor
     */
    public ShoppingCart() {
        setLineItemList(new ArrayList<LineItem>());
    }

    private List<LineItem> lineItemList;

    private int cardId;

    public List<LineItem> getLineItemList() {
        return lineItemList;
    }

    public void setLineItemList(List<LineItem> lineItemList) {
        this.lineItemList = lineItemList;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }
}