package org.csu.mypetstore.persistence.impl;

import com.sun.org.apache.xpath.internal.operations.Or;
import org.csu.mypetstore.domain.Order;
import org.csu.mypetstore.persistence.DBUtil;
import org.csu.mypetstore.persistence.OrderDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    private static final String GETORDERBYUERNAME="SELECT BILLADDR1 AS billAddress1, BILLADDR2 AS billAddress2, BILLCITY, BILLCOUNTRY, BILLSTATE, BILLTOFIRSTNAME, BILLTOLASTNAME, BILLZIP, SHIPADDR1 AS shipAddress1, SHIPADDR2 AS shipAddress2, SHIPCITY, SHIPCOUNTRY, SHIPSTATE, SHIPTOFIRSTNAME, SHIPTOLASTNAME, SHIPZIP, CARDTYPE, COURIER, CREDITCARD, EXPRDATE AS expiryDate, LOCALE, ORDERDATE, ORDERS.ORDERID, TOTALPRICE, USERID AS username, STATUS FROM ORDERS, ORDERSTATUS WHERE ORDERS.USERID = ? AND ORDERS.ORDERID = ORDERSTATUS.ORDERID ORDER BY ORDERDATE";
    private static final String GETORDER="SELECT BILLADDR1 AS billAddress1, BILLADDR2 AS billAddress2, BILLCITY, BILLCOUNTRY, BILLSTATE, BILLTOFIRSTNAME, BILLTOLASTNAME, BILLZIP, SHIPADDR1 AS shipAddress1, SHIPADDR2 AS shipAddress2, SHIPCITY, SHIPCOUNTRY, SHIPSTATE, SHIPTOFIRSTNAME, SHIPTOLASTNAME, SHIPZIP, CARDTYPE, COURIER, CREDITCARD, EXPRDATE AS expiryDate, LOCALE, ORDERDATE, ORDERS.ORDERID, TOTALPRICE, USERID AS username, STATUS FROM ORDERS, ORDERSTATUS WHERE ORDERS.ORDERID = ? AND ORDERS.ORDERID = ORDERSTATUS.ORDERID";
    private static final String INSERTORDER="INSERT INTO ORDERS (ORDERID, USERID, ORDERDATE, SHIPADDR1, SHIPADDR2, SHIPCITY, SHIPSTATE,SHIPZIP, SHIPCOUNTRY, BILLADDR1, BILLADDR2, BILLCITY, BILLSTATE, BILLZIP, BILLCOUNTRY,COURIER, TOTALPRICE, BILLTOFIRSTNAME, BILLTOLASTNAME, SHIPTOFIRSTNAME, SHIPTOLASTNAME,CREDITCARD, EXPRDATE, CARDTYPE, LOCALE) VALUES(?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?)";
    private static final String INSERTORDERSTATUS="INSERT INTO ORDERSTATUS (ORDERID, LINENUM, TIMESTAMP, STATUS) VALUES (?, ?, ?, ?)";
    @Override
    public List<Order> getOrdersByUsername(String username) {
        List<Order> orders=new ArrayList<>();
        try{
            Connection conn= DBUtil.getconn();
            PreparedStatement ps=conn.prepareStatement(GETORDERBYUERNAME);
            ps.setString(1,username);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                Order order=new Order();
                order.setBillAddress1(rs.getString(1));
                order.setBillAddress2(rs.getString(2));
                order.setBillCity(rs.getString(3));
                order.setBillCountry(rs.getString(4));
                order.setBillState(rs.getString(5));
                order.setBillToFirstName(rs.getString(6));
                order.setBillToLastName(rs.getString(7));
                order.setBillZip(rs.getString(8));
                order.setShipAddress1(rs.getString(9));
                order.setShipAddress2(rs.getString(10));
                order.setShipCity(rs.getString(11));
                order.setShipCountry(rs.getString(12));
                order.setShipState(rs.getString(13));
                order.setShipToFirstName(rs.getString(14));
                order.setShipToLastName(rs.getString(15));
                order.setShipZip(rs.getString(16));
                order.setCardType(rs.getString(17));
                order.setCourier(rs.getString(18));
                order.setCreditCard(rs.getString(19));
                order.setExpiryDate(rs.getString(20));
                order.setLocale(rs.getString(21));
                order.setOrderDate(rs.getString(22));
                order.setOrderId(rs.getInt(23));
                order.setTotalPrice(rs.getBigDecimal(24));
                order.setUsername(rs.getString(25));
                order.setStatus(rs.getString(26));
                orders.add(order);
            }
            DBUtil.closeAll(conn,ps,rs);
        }catch (Exception e){
            e.printStackTrace();
        }

        return orders;
    }

    @Override
    public Order getOrder(int orderId) {
        Order order=null;
        try {
            Connection conn=DBUtil.getconn();
            PreparedStatement ps=conn.prepareStatement(GETORDER);
            ps.setInt(1,orderId);
            ResultSet rs=ps.executeQuery();
            if (rs.next()){
                order=new Order();
                order.setBillAddress1(rs.getString(1));
                order.setBillAddress2(rs.getString(2));
                order.setBillCity(rs.getString(3));
                order.setBillCountry(rs.getString(4));
                order.setBillState(rs.getString(5));
                order.setBillToFirstName(rs.getString(6));
                order.setBillToLastName(rs.getString(7));
                order.setBillZip(rs.getString(8));
                order.setShipAddress1(rs.getString(9));
                order.setShipAddress2(rs.getString(10));
                order.setShipCity(rs.getString(11));
                order.setShipCountry(rs.getString(12));
                order.setShipState(rs.getString(13));
                order.setShipToFirstName(rs.getString(14));
                order.setShipToLastName(rs.getString(15));
                order.setShipZip(rs.getString(16));
                order.setCardType(rs.getString(17));
                order.setCourier(rs.getString(18));
                order.setCreditCard(rs.getString(19));
                order.setExpiryDate(rs.getString(20));
                order.setLocale(rs.getString(21));
                order.setOrderDate(rs.getString(22));
                order.setOrderId(rs.getInt(23));
                order.setTotalPrice(rs.getBigDecimal(24));
                order.setUsername(rs.getString(25));
                order.setStatus(rs.getString(26));
            }
            DBUtil.closeAll(conn,ps,rs);
        }catch (Exception e){
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public void insertOrder(Order order) {
        try {
            Connection conn=DBUtil.getconn();
            PreparedStatement ps=conn.prepareStatement(INSERTORDER);
            ps.setInt(1,order.getOrderId());
            ps.setString(2,order.getUsername());
            ps.setString(3, order.getOrderDate());
            ps.setString(4,order.getShipAddress1());
            ps.setString(5,order.getShipAddress2());
            ps.setString(6,order.getShipCity());
            ps.setString(7,order.getShipState());
            ps.setString(8,order.getShipZip());
            ps.setString(9,order.getShipCountry());
            ps.setString(10,order.getBillAddress1());
            ps.setString(11,order.getBillAddress2());
            ps.setString(12,order.getBillCity());
            ps.setString(13,order.getBillState());
            ps.setString(14,order.getBillZip());
            ps.setString(15,order.getBillCountry());
            ps.setString(16,order.getCourier());
            ps.setBigDecimal(17,order.getTotalPrice());
            ps.setString(18,order.getBillToFirstName());
            ps.setString(19,order.getBillToLastName());
            ps.setString(20,order.getShipToFirstName());
            ps.setString(21,order.getShipToLastName());
            ps.setString(22,order.getCreditCard());
            ps.setString(23,order.getExpiryDate());
            ps.setString(24,order.getCardType());
            ps.setString(25,order.getLocale());

            ps.executeUpdate();
            ResultSet rs=null;
            DBUtil.closeAll(conn,ps,rs);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void insertOrderStatus(Order order) {
        try {
            Connection conn=DBUtil.getconn();
            PreparedStatement ps=conn.prepareStatement(INSERTORDERSTATUS);
            ps.setInt(1,order.getOrderId());
            ps.setString(4,order.getStatus());
            ps.setString(3,order.getOrderDate());
            ps.setInt(2,order.getLineItems().size());
            ps.executeUpdate();
            ResultSet rs=null;
            DBUtil.closeAll(conn,ps,rs);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public int getOrderNum(){
        int num=0;
        try {
            Connection conn=DBUtil.getconn();
            PreparedStatement ps=conn.prepareStatement("SELECT COUNT(*) FROM orders");
            ResultSet rs=ps.executeQuery();
            if (rs.next()) {
                num = rs.getInt(1) + 1;
            }
            DBUtil.closeAll(conn,ps,rs);
        }catch (Exception e){
            e.printStackTrace();
        }
        return num;
    }
}
