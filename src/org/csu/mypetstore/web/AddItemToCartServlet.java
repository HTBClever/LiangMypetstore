package org.csu.mypetstore.web;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.CartItem;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.service.CartService;
import org.csu.mypetstore.service.CatelogService;
import org.csu.mypetstore.service.UserLogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Iterator;

public class AddItemToCartServlet extends HttpServlet {
    private static final String VIEW_CART="/WEB-INF/jsp/cart/Cart.jsp";
    private static final String CHECK_OUT="/WEB-INF/jsp/cart/Check.jsp";

    private String workingItemId;
//    private Cart cart;
    private CartService cartService;
    private CatelogService catelogService;
    private Account account;
    private Iterator<CartItem> cartItems;
    private int numberOfItems;
    private BigDecimal subTotal;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        workingItemId=request.getParameter("workingItemId");
        HttpSession session=request.getSession();
        account= (Account) session.getAttribute("account");
        cartService=new CartService(account);

//        cart=(Cart)session.getAttribute("cart");
//        if (cart==null){
//            cart=new Cart();
//        }
        if (cartService.containsItemId(workingItemId)){
            cartService.incrementQuantityByItemId(account,workingItemId);
        }
        else {
            catelogService=new CatelogService();
            boolean isInstock=catelogService.isItemInStock(workingItemId);
            Item item=catelogService.getItem(workingItemId);
            cartService.addItem(account,item,isInstock);
        }

        UserLogService recordLog =new UserLogService();
        recordLog.recordUserLog(request,response,"增加订单" + workingItemId);

        cartItems=cartService.getCartItems();
        numberOfItems=cartService.getNumberOfItems();
        subTotal=cartService.getSubTotal();
        session.setAttribute("cartItems",cartItems);
        session.setAttribute("numberOfItems",numberOfItems);
        session.setAttribute("subTotal",subTotal);
        request.getRequestDispatcher(VIEW_CART).forward(request,response);
    }
}
