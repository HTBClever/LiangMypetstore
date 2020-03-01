package org.csu.mypetstore.web;

import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.service.CatelogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ViewSearchProductServlet extends HttpServlet {
    private String keyWord;
    private static final String ViewSearch="/WEB-INF/jsp/catalog/SearchProducts.jsp";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        keyWord=request.getParameter("keyWord");
        CatelogService service=new CatelogService();
        List<Product> productList=service.searchProductList(keyWord);
        HttpSession session=request.getSession();
        session.setAttribute("productList",productList);
        request.getRequestDispatcher(ViewSearch).forward(request,response);
    }
}
