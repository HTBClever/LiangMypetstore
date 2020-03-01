package org.csu.mypetstore.web;

import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.service.CatelogService;
import org.csu.mypetstore.service.UserLogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ViewItemServlet extends HttpServlet {
    private String itemId;
    private static final String viewItem="/WEB-INF/jsp/catalog/Item.jsp";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        itemId=request.getParameter("itemId");
        CatelogService service=new CatelogService();
        Item item=service.getItem(itemId);
        Product product=service.getProduct(item.getProductId());
        HttpSession session=request.getSession();
        session.setAttribute("item",item);
        session.setAttribute("product",product);

        UserLogService recordLog =new UserLogService();
        recordLog.recordUserLog(request,response,viewItem + "查看商品" + itemId);

        request.getRequestDispatcher(viewItem).forward(request,response);
    }
}
