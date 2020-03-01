package org.csu.mypetstore.web;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.CartItem;
import org.csu.mypetstore.domain.LineItem;
import org.csu.mypetstore.domain.Order;
import org.csu.mypetstore.service.CartService;
import org.csu.mypetstore.service.CatelogService;
import org.csu.mypetstore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ViewOrderServlet extends HttpServlet {
    private Account account;
    private Order order;
    private OrderService orderService;
    private CartService cartService;
    private CatelogService catelogService;
    private static final String ViewORDER="/WEB-INF/jsp/order/ViewOrder.jsp";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        String messageOfBuy="Thank you, your order has been submitted.";
        session.setAttribute("messageOfBuy",messageOfBuy);
        account= (Account) session.getAttribute("account");
        order= (Order) session.getAttribute("order");
        orderService=new OrderService();
        cartService=new CartService(account);
        orderService.insertOrder(order);
        orderService.insertOrderStatus(order);
        List<CartItem> cartItems=cartService.getCartItemList();
        for (CartItem cartItem:cartItems) {
            LineItem lineItem=new LineItem(cartItems.size(),cartItem,order.getOrderId());
            orderService.insertLineItem(lineItem);
        }
        catelogService =new CatelogService();
        catelogService.updateInventoryQuantity(cartService.getCartItemList());
        cartService.clearCart(account);
        request.getRequestDispatcher(ViewORDER).forward(request,response);
    }
}
