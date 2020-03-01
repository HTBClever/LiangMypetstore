package org.csu.mypetstore.service;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.CartItem;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.persistence.CartDAO;
import org.csu.mypetstore.persistence.impl.CartDAOImpl;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CartService {
    private CartDAO cartDAO;
    public CartService(Account account){
        cartDAO=new CartDAOImpl(account);
    }
    public Iterator<CartItem> getCartItems(){
        return cartDAO.getCartItems();
    }
    public List<CartItem> getCartItemList(){
        return cartDAO.getCartItemList();
    }
    public int getNumberOfItems(){
        return cartDAO.getNumberOfItems();
    }
    public Iterator<CartItem> getAllCartItems(){
        return cartDAO.getAllCartItems();
    }
    public boolean containsItemId(String itemId){
        return cartDAO.containsItemId(itemId);
    }
    public void addItem(Account account, Item item, boolean isInStock){
        cartDAO.addItem(account,item,isInStock);
    }
    public Item removeItemById(Account account, String itemId){
        return cartDAO.removeItemById(account,itemId);
    }
    public void incrementQuantityByItemId(Account account,String itemId){
        cartDAO.incrementQuantityByItemId(account,itemId);
    }
    public void setQuantityByItemId(Account account,String itemId, int quantity){
        cartDAO.setQuantityByItemId(account,itemId,quantity);
    }
    public BigDecimal getSubTotal(){
        return cartDAO.getSubTotal();
    }
    public void clearCart(Account account){
        cartDAO.clearCart(account);
    }
    public Map<String, CartItem> getItemMap(){
        return cartDAO.getItemMap();
    }
}
