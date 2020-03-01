package org.csu.mypetstore.web;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.CartItem;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.service.CartService;
import org.csu.mypetstore.service.UserLogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Iterator;

public class RemoveItemServlet extends HttpServlet {
    private static final String VIEW_CART="/WEB-INF/jsp/cart/Cart.jsp";
    private static final String Error="/WEB-INF/jsp/common/Error.jsp";
//    private Cart cart;
    private CartService cartService;
    private String cartItem;
    private Account account;
    private Iterator<CartItem> cartItems;
    private int numberOfItems;
    private BigDecimal subTotal;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        cartItem=request.getParameter("cartItem");
        HttpSession session=request.getSession();
        account= (Account) session.getAttribute("account");
        cartService=new CartService(account);
        Item item=cartService.removeItemById(account,cartItem);
        if (item==null){
            session.setAttribute("message","Attempted to remove null CartItem from Cart.");
            request.getRequestDispatcher(Error).forward(request,response);
        }
        else{
            cartService=new CartService(account);
            cartItems=cartService.getCartItems();
            numberOfItems=cartService.getNumberOfItems();
            subTotal=cartService.getSubTotal();
            session.setAttribute("cartItems",cartItems);
            session.setAttribute("numberOfItems",numberOfItems);
            session.setAttribute("subTotal",subTotal);

            UserLogService recordLog =new UserLogService();
            recordLog.recordUserLog(request,response,VIEW_CART + "删除商品" + cartItem);

            request.getRequestDispatcher(VIEW_CART).forward(request,response);
        }
    }
}
