package org.csu.mypetstore.web;

import org.csu.mypetstore.domain.Category;
import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.service.CatelogService;
import org.csu.mypetstore.service.UserLogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ViewCategoryServlet extends HttpServlet {

    private static final String viewCategory="/WEB-INF/jsp/catalog/Category.jsp";
    private String categoryId;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        categoryId=request.getParameter("categoryId");
        CatelogService catelogService=new CatelogService();
        Category category=catelogService.getCategory(categoryId);
        List<Product> productList=catelogService.getProductListByCategory(categoryId);
        HttpSession session= request.getSession();
        session.setAttribute("category",category);
        session.setAttribute("productList",productList);

        UserLogService recordLog =new UserLogService();
        recordLog.recordUserLog(request,response,viewCategory + "查看大类" + categoryId);

        request.getRequestDispatcher(viewCategory).forward(request,response);
    }
}
