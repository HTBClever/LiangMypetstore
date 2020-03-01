package org.csu.mypetstore.web;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Order;
import org.csu.mypetstore.service.CartService;
import org.csu.mypetstore.service.OrderService;
import org.csu.mypetstore.service.UserLogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ViewConfirmOrderServlet extends HttpServlet {
    private static final String ViewConfirmOrderForm="/WEB-INF/jsp/order/ConfirmOrder.jsp";
    private Order order;
    private Account account;
    private CartService cartService;
    private OrderService orderService;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        order = new Order();
        HttpSession session = request.getSession();
        account = (Account) session.getAttribute("account");
        cartService = new CartService(account);
        orderService = new OrderService();
        order.initOrder(account, cartService, orderService.getOrderNum());
        order.setShipToFirstName(request.getParameter("order.shipToFirstName"));
        order.setShipToLastName(request.getParameter("order.shipToLastName"));
        order.setShipAddress1(request.getParameter("order.shipAddress1"));
        order.setShipAddress2(request.getParameter("order.shipAddress2"));
        order.setShipCity(request.getParameter("order.shipCity"));
        order.setShipState(request.getParameter("order.shipState"));
        order.setShipZip(request.getParameter("order.shipZip"));
        order.setShipCountry(request.getParameter("order.shipCountry"));
        order.setBillToFirstName(request.getParameter("order.billToFirstName"));
        order.setBillToLastName(request.getParameter("order.billToLastName"));
        order.setBillAddress1(request.getParameter("order.billAddress1"));
        order.setBillAddress2(request.getParameter("order.billAddress2"));
        order.setBillZip(request.getParameter("order.billZip"));
        order.setBillCity(request.getParameter("order.billCity"));
        order.setBillState(request.getParameter("order.billState"));
        order.setBillCountry(request.getParameter("order.billCountry"));
        session.setAttribute("order", order);
        request.getRequestDispatcher(ViewConfirmOrderForm).forward(request,response);
    }
}
