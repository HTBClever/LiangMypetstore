package org.csu.mypetstore.service;

import org.csu.mypetstore.domain.LineItem;
import org.csu.mypetstore.domain.Order;
import org.csu.mypetstore.persistence.LineItemDAO;
import org.csu.mypetstore.persistence.OrderDAO;
import org.csu.mypetstore.persistence.impl.LineItemImpl;
import org.csu.mypetstore.persistence.impl.OrderDAOImpl;

import java.util.List;

public class OrderService {
    private OrderDAO orderDAO;
    private LineItemDAO lineItemDAO;
    public OrderService(){
        orderDAO=new OrderDAOImpl();
        lineItemDAO=new LineItemImpl();
    }
    public List<Order> getOrdersByUsername(String username){
        return orderDAO.getOrdersByUsername(username);
    }
    public Order getOrder(int orderId){
        return orderDAO.getOrder(orderId);
    }
    public void insertOrder(Order order){
        orderDAO.insertOrder(order);
    }
    public void insertOrderStatus(Order order){
        orderDAO.insertOrderStatus(order);
    }
    public List<LineItem> getLineItemsByOrderId(int orderId){
        return lineItemDAO.getLineItemsByOrderId(orderId);
    }
    public void insertLineItem(LineItem lineItem){
        lineItemDAO.insertLineItem(lineItem);
    }
    public int getOrderNum(){
        return orderDAO.getOrderNum();
    }
}
