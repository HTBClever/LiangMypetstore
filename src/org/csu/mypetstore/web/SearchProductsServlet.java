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

public class SearchProductsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        CatelogService service=new CatelogService();
        String keyWord=request.getParameter("keyWord");
        keyWord=new String(keyWord.getBytes("iso-8859-1"), "utf-8");
        List<Product> productList=service.searchProductList(keyWord);
        //返回结果
        String res="";
        for (int i=0;i<productList.size();i++){
            if (i>0)
                res+=","+productList.get(i).getName();
            else
                res+=productList.get(i).getName();
        }
        response.getWriter().write(res);
//        response.setContentType("text/plain");
//        PrintWriter out=response.getWriter();
//        String name="";
//        for (int i=0;i<productList.size();i++){
//            if (i!=productList.size()-1)
//                name+=productList.get(i).getName()+" ";
//            else
//                name+=productList.get(i).getName();
//            System.out.println(productList.get(i).getName());
//        }
//        out.print(name);
//        out.flush();
//        out.close();

        //获取搜索框输入的内容

        //向server层调用相应的业务





    }
}
