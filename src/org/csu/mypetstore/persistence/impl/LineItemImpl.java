package org.csu.mypetstore.persistence.impl;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.CartItem;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.domain.LineItem;
import org.csu.mypetstore.persistence.DBUtil;
import org.csu.mypetstore.persistence.LineItemDAO;
import org.csu.mypetstore.service.CartService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LineItemImpl implements LineItemDAO {
    private static final String GETLINEITEMBYORDER=" SELECT ORDERID, LINENUM AS lineNumber, ITEMID, QUANTITY, UNITPRICE FROM LINEITEM WHERE ORDERID = ?";
    private static final String INSERTLINEITEM="INSERT INTO LINEITEM (ORDERID, LINENUM, ITEMID, QUANTITY, UNITPRICE) VALUES (?, ?, ?, ?, ?)";
    private ItemDAOImpl itemDAO;
    @Override
    public List<LineItem> getLineItemsByOrderId(int orderId) {
        List<LineItem> lineItems=new ArrayList<>();
        try {
            Connection conn= DBUtil.getconn();
            PreparedStatement ps=conn.prepareStatement(GETLINEITEMBYORDER);
            ps.setInt(1,orderId);
            ResultSet rs=ps.executeQuery();
            itemDAO=new ItemDAOImpl();
            while (rs.next()){
                Item item=itemDAO.getItem(rs.getString(3));
                CartItem cartItem=new CartItem();
                cartItem.setItem(item);
                cartItem.setQuantity(rs.getInt(4));
                LineItem lineItem=new LineItem(rs.getInt(2),cartItem,rs.getInt(1));
                lineItems.add(lineItem);
            }
            DBUtil.closeAll(conn,ps,rs);
        }catch (Exception e){
            e.printStackTrace();
        }
        return lineItems;
    }

    @Override
    public void insertLineItem(LineItem lineItem) {
        try {
            Connection conn=DBUtil.getconn();
            PreparedStatement ps=conn.prepareStatement(INSERTLINEITEM);
            ps.setInt(1,lineItem.getOrderId());
            ps.setInt(2,lineItem.getLineNumber());
            ps.setString(3,lineItem.getItemId());
            ps.setInt(4,lineItem.getQuantity());
            ps.setBigDecimal(5,lineItem.getUnitPrice());
            ps.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
