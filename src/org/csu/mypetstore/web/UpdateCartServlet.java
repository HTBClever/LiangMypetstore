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
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Iterator;

public class UpdateCartServlet extends HttpServlet {

    private static final String VIEW_CART="/WEB-INF/jsp/cart/Cart.jsp";
    private CartService cartService;
    private Account account;
    private int numberOfItems;
    private BigDecimal subTotal;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String itemIdQuantity = request.getParameter("itemQuantityId");
        String itemId= itemIdQuantity.replace("Quantity","");
        HttpSession session=request.getSession();
        account= (Account) session.getAttribute("account");
        CartService cartService = new CartService(account);
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        cartService.setQuantityByItemId(account,itemId,quantity);
        BigDecimal totalMoney =  cartService.getSubTotal();
        response.setContentType("text/xml");
        PrintWriter out = response.getWriter();
        out.write(totalMoney.toString());

    }
}
