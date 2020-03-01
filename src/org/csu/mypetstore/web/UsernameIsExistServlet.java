package org.csu.mypetstore.web;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UsernameIsExistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AccountService accountService=new AccountService();
        String username=request.getParameter("username");
        Account account=accountService.getAccount(username);
        boolean result;
        if (account==null) {
            result = false;
        }else {
            result=true;
        }
        response.setContentType("text/plain");
        PrintWriter out=response.getWriter();
        if (username==null||username==""){
            out.print("username is null");
        }
        else if (result){
            out.print("Exist");
        }else {
            out.print("Not Exist");
        }
        out.flush();
        out.close();
    }
}
