package org.csu.mypetstore.persistence.impl;

import org.csu.mypetstore.domain.CartItem;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.persistence.DBUtil;
import org.csu.mypetstore.persistence.ItemDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ItemDAOImpl implements ItemDAO {
    private static final String UPDATE_INVENTORY="UPDATE INVENTORY SET QTY = QTY - ? WHERE ITEMID = ?";
    private static final String GET_INVENTORY_QUANLITY="SELECT QTY AS value FROM INVENTORY WHERE ITEMID = ?";
    private static final String GET_ITEMLIST_BY_PRODUCT="SELECT I.ITEMID, LISTPRICE, UNITCOST, SUPPLIER AS supplierId, I.PRODUCTID AS \"product.productId\", NAME AS \"product.name\", DESCN AS \"product.description\", CATEGORY AS \"product.categoryId\", STATUS, ATTR1 AS attribute1, ATTR2 AS attribute2, ATTR3 AS attribute3, ATTR4 AS attribute4, ATTR5 AS attribute5 FROM ITEM I, PRODUCT P WHERE P.PRODUCTID = I.PRODUCTID AND I.PRODUCTID = ?";
    private static final String GET_ITEM="select I.ITEMID, LISTPRICE, UNITCOST, SUPPLIER AS supplierId, I.PRODUCTID AS \"product.productId\", NAME AS \"product.name\", DESCN AS \"product.description\", CATEGORY AS \"product.categoryId\", STATUS, ATTR1 AS attribute1, ATTR2 AS attribute2, ATTR3 AS attribute3, ATTR4 AS attribute4, ATTR5 AS attribute5, QTY AS quantity from ITEM I, INVENTORY V, PRODUCT P where P.PRODUCTID = I.PRODUCTID and I.ITEMID = V.ITEMID and I.ITEMID = ?";
    @Override
    public void updateInventoryQuantity(List<CartItem> cartItems) {

        try {
            Connection conn= DBUtil.getconn();
            PreparedStatement ps=null;
            for (CartItem cartItem:cartItems) {
                ps= conn.prepareStatement(UPDATE_INVENTORY);
                ps.setInt(1, cartItem.getQuantity());
                ps.setString(2, cartItem.getItem().getItemId());
                ps.executeUpdate();
            }
            ResultSet rs=null;
            DBUtil.closeAll(conn,ps,rs);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getInventoryQuantity(String itemId) {
        int quantity=0;
        try {
            Connection conn=DBUtil.getconn();
            PreparedStatement ps=conn.prepareStatement(GET_INVENTORY_QUANLITY);
            ps.setString(1,itemId);
            ResultSet rs=ps.executeQuery();
            if (rs.next())
                quantity=rs.getInt(1);
            DBUtil.closeAll(conn,ps,rs);
        }catch (Exception e){
            e.printStackTrace();
        }
        return quantity;
    }

    @Override
    public List<Item> getItemListByProduct(String productId) {
        List<Item> itemList=new ArrayList<>();
        try {
            Connection conn=DBUtil.getconn();
            PreparedStatement ps=conn.prepareStatement(GET_ITEMLIST_BY_PRODUCT);
            ps.setString(1,productId);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                Item item=new Item();
                item.setItemId(rs.getString(1));
                item.setListPrice(rs.getBigDecimal(2));
                item.setUnitCost(rs.getBigDecimal(3));
                item.setSupplierId(rs.getInt(4));
                item.setProductId(rs.getString(5));
                Product product=new Product();
                product.setProductId(rs.getString(5));
                product.setName(rs.getString(6));
                product.setDescription(rs.getString(7));
                product.setCategoryId(rs.getString(8));
                item.setProduct(product);
                item.setStatus(rs.getString(9));
                item.setAttribute1(rs.getString(10));
                item.setAttribute2(rs.getString(11));
                item.setAttribute3(rs.getString(12));
                item.setAttribute4(rs.getString(13));
                item.setAttribute5(rs.getString(14));
                itemList.add(item);
            }
            DBUtil.closeAll(conn,ps,rs);
        }catch (Exception e){
            e.printStackTrace();
        }
        return itemList;
    }

    @Override
    public Item getItem(String itemId) {
        Item item=null;
        try {
            Connection conn=DBUtil.getconn();
            PreparedStatement ps=conn.prepareStatement(GET_ITEM);
            ps.setString(1,itemId);
            ResultSet rs=ps.executeQuery();
            if (rs.next()){
                item=new Item();
                item.setItemId(rs.getString(1));
                item.setListPrice(rs.getBigDecimal(2));
                item.setUnitCost(rs.getBigDecimal(3));
                item.setSupplierId(rs.getInt(4));
                item.setProductId(rs.getString(5));
                Product product=new Product();
                product.setProductId(rs.getString(5));
                product.setName(rs.getString(6));
                product.setDescription(rs.getString(7));
                product.setCategoryId(rs.getString(8));
                item.setProduct(product);
                item.setStatus(rs.getString(9));
                item.setAttribute1(rs.getString(10));
                item.setAttribute2(rs.getString(11));
                item.setAttribute3(rs.getString(12));
                item.setAttribute4(rs.getString(13));
                item.setAttribute5(rs.getString(14));
                item.setQuantity(rs.getInt(15));
            }
            DBUtil.closeAll(conn,ps,rs);
        }catch (Exception e){
            e.printStackTrace();
        }
        return item;
    }

}
