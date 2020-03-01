package org.csu.mypetstore.web;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Order;
import org.csu.mypetstore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ViewOrderFromListServlet extends HttpServlet {
    private Account account;
    private OrderService orderService;
    private Order order;
    private static final String ViewORDERFromList="/WEB-INF/jsp/order/ViewOrder.jsp";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        String messageOfBuy=null;
        account= (Account) session.getAttribute("account");
        int orderId= Integer.parseInt(request.getParameter("orderId"));
        orderService=new OrderService();
        order=orderService.getOrder(orderId);
        order.setLineItems(orderService.getLineItemsByOrderId(orderId));
        session.setAttribute("order",order);
        session.setAttribute("messageOfBuy",messageOfBuy);
        request.getRequestDispatcher(ViewORDERFromList).forward(request,response);
    }
}
