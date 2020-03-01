package org.csu.mypetstore.web;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.CartItem;
import org.csu.mypetstore.service.CartService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Iterator;

public class ViewCartServlet extends HttpServlet {
    private static final String VIEW_CART="/WEB-INF/jsp/cart/Cart.jsp";
    private CartService cartService;
    private Account account;
    private Iterator<CartItem>cartItems;
    private int numberOfItems;
    private BigDecimal subTotal;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        account= (Account) session.getAttribute("account");
        cartService=new CartService(account);
        cartItems=cartService.getCartItems();
        numberOfItems=cartService.getNumberOfItems();
        subTotal=cartService.getSubTotal();
        String []cardTypes={"Visa","Mastercard","American Express"};
        session.setAttribute("cardTypes",cardTypes);
        session.setAttribute("cartItems",cartItems);
        session.setAttribute("numberOfItems",numberOfItems);
        session.setAttribute("subTotal",subTotal);
        request.getRequestDispatcher(VIEW_CART).forward(request,response);
    }
}
