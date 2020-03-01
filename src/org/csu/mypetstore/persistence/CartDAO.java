package org.csu.mypetstore.persistence;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.CartItem;
import org.csu.mypetstore.domain.Item;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public interface CartDAO {

     Iterator<CartItem> getCartItems();
     List<CartItem> getCartItemList();
     int getNumberOfItems();
    Iterator<CartItem> getAllCartItems();
    boolean containsItemId(String itemId);
    void addItem(Account account, Item item, boolean isInStock);
    Item removeItemById(Account account,String itemId);
    void incrementQuantityByItemId(Account account,String itemId);
    void setQuantityByItemId(Account account,String itemId, int quantity);
    void clearCart(Account account);
    Map<String, CartItem> getItemMap();
    BigDecimal getSubTotal();
}
