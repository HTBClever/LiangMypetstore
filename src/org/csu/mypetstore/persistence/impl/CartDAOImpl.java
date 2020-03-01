package org.csu.mypetstore.persistence.impl;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.CartItem;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.persistence.CartDAO;
import org.csu.mypetstore.persistence.DBUtil;
import org.csu.mypetstore.persistence.ItemDAO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class CartDAOImpl implements CartDAO {
    private final Map<String, CartItem> itemMap = Collections.synchronizedMap(new HashMap<String, CartItem>());
    private final List<CartItem> itemList = new ArrayList<CartItem>();
    private static final String ADDCART="insert into cartitem (username, itemId, quantity) values (?, ?, ?)";
    private static final String ALLCART="select itemId, quantity from cartitem where username=?";
    private static final String ADDQUANTITTY="update cartitem set quantity=? where username=? and itemId=?";
    private static final String DELECTITEM="DELETE FROM cartitem where username=? and itemId=?";
    private static final String EDITQUANTITY="update cartitem set quantity=? where username=? and itemId=?";
    private static final String CLEARCART="DELETE FROM cartitem where username=?";
    public CartDAOImpl(Account account){
        try {
            Connection conn=DBUtil.getconn();
            PreparedStatement ps=conn.prepareStatement(ALLCART);
            ps.setString(1,account.getUsername());
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                Item item=new Item();
                CartItem cartItem=new CartItem();
                ItemDAO itemDAO=new ItemDAOImpl();
                item=itemDAO.getItem(rs.getString(1));
                item.setQuantity(rs.getInt(2));
                cartItem.setItem(item);
                cartItem.setInStock(itemDAO.getInventoryQuantity(item.getItemId()) > rs.getInt(2));
                cartItem.setQuantity(rs.getInt(2));
                itemMap.put(item.getItemId(), cartItem);
                itemList.add(cartItem);
            }
            DBUtil.closeAll(conn,ps,rs);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public Iterator<CartItem> getCartItems() {
        return itemList.iterator();
    }

    @Override
    public List<CartItem> getCartItemList() {
        return itemList;
    }

    @Override
    public int getNumberOfItems() {
        return itemList.size();
    }

    @Override
    public Iterator<CartItem> getAllCartItems() {
        return itemList.iterator();
    }

    @Override
    public boolean containsItemId(String itemId) {
        return itemMap.containsKey(itemId);
    }

    @Override
    public void addItem(Account account,Item item, boolean isInStock) {
        CartItem cartItem = (CartItem) itemMap.get(item.getItemId());
        if (cartItem == null) {
            cartItem = new CartItem();
            cartItem.setItem(item);
            cartItem.setQuantity(0);
            cartItem.setInStock(isInStock);
            itemMap.put(item.getItemId(), cartItem);
            itemList.add(cartItem);
            try {
                Connection conn= DBUtil.getconn();
                PreparedStatement ps=conn.prepareStatement(ADDCART);
                ps.setString(1,account.getUsername());
                ps.setString(2,item.getItemId());
                ps.setInt(3,cartItem.getQuantity());
                ps.executeUpdate();
                ResultSet rs=null;
                DBUtil.closeAll(conn,ps,rs);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        incrementQuantityByItemId(account,item.getItemId());
    }

    @Override
    public Item removeItemById(Account account, String itemId) {
        CartItem cartItem = (CartItem) itemMap.remove(itemId);
        if (cartItem == null) {
            return null;
        } else {
            try {
                Connection conn=DBUtil.getconn();
                PreparedStatement ps=conn.prepareStatement(DELECTITEM);
                ps.setString(1,account.getUsername());
                ps.setString(2,itemId);
                ps.executeUpdate();
                ResultSet rs=null;
                DBUtil.closeAll(conn,ps,rs);
            }catch (Exception e){
                e.printStackTrace();
            }
            return cartItem.getItem();
        }
    }

    @Override
    public void incrementQuantityByItemId(Account account,String itemId) {
        CartItem cartItem = (CartItem) itemMap.get(itemId);
        cartItem.incrementQuantity();
        try {
            Connection conn=DBUtil.getconn();
            PreparedStatement ps=conn.prepareStatement(ADDQUANTITTY);
            ps.setInt(1,cartItem.getQuantity());
            ps.setString(2,account.getUsername());
            ps.setString(3,itemId);
            ps.executeUpdate();
            ResultSet rs=null;
            DBUtil.closeAll(conn,ps,rs);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void setQuantityByItemId(Account account,String itemId, int quantity) {
        CartItem cartItem = (CartItem) itemMap.get(itemId);
        cartItem.setQuantity(quantity);
        try {
            Connection conn=DBUtil.getconn();
            PreparedStatement ps=conn.prepareStatement(EDITQUANTITY);
            ps.setInt(1,cartItem.getQuantity());
            ps.setString(2,account.getUsername());
            ps.setString(3,itemId);
            ps.executeUpdate();
            ResultSet rs=null;
            DBUtil.closeAll(conn,ps,rs);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void clearCart(Account account) {
        itemMap.clear();
            try {
                Connection conn=DBUtil.getconn();
                PreparedStatement ps=conn.prepareStatement(CLEARCART);
                ps.setString(1,account.getUsername());
                ps.executeUpdate();
                ResultSet rs=null;
                DBUtil.closeAll(conn,ps,rs);
            }catch (Exception e){
                e.printStackTrace();
            }
            itemList.clear();
    }

    @Override
    public Map<String, CartItem> getItemMap() {
        return itemMap;
    }

    @Override
    public BigDecimal getSubTotal() {
        BigDecimal subTotal = new BigDecimal("0");
        Iterator<CartItem> items = getAllCartItems();
        while (items.hasNext()) {
            CartItem cartItem = (CartItem) items.next();
            Item item = cartItem.getItem();
            BigDecimal listPrice = item.getListPrice();
            BigDecimal quantity = new BigDecimal(String.valueOf(cartItem.getQuantity()));
            subTotal = subTotal.add(listPrice.multiply(quantity));
        }
        return subTotal;
    }
}
