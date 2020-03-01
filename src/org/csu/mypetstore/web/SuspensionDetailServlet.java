package org.csu.mypetstore.web;

import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.service.CatelogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class SuspensionDetailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categoryId = request.getParameter("categoryId");
        CatelogService catelogService = new CatelogService();

        List<Product> productList = catelogService.getProductListByCategory(categoryId);
        String resp = "<table><tr><th>Product ID<th>      <th>Name<th>\n";
        int i = 0;
        while(i < productList.size()){
            Product product = productList.get(i);
            resp +="<tr><td>" + product.getProductId() + "<td> <td>     " + product.getName() + "<td><tr>" +"\n";
            i++;
        }
        resp += "<table>";
        response.setContentType("text/xml");
        PrintWriter out = response.getWriter();
        out.write(resp);

    }
}
