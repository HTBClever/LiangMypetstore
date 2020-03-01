package org.csu.mypetstore.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ViewNewAccountServlet extends HttpServlet {
    private static final String ViewNewAccountForm="/WEB-INF/jsp/account/NewAccountForm.jsp";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        String yanZhengMessage=null;
        session.setAttribute("yanZhengMessage",yanZhengMessage);
        request.getRequestDispatcher(ViewNewAccountForm).forward(request,response);
    }
}
