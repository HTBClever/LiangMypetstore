package org.csu.mypetstore.web;

import org.csu.mypetstore.service.UserLogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignOutServlet extends HttpServlet {
    private static final String Main="/WEB-INF/jsp/catalog/Main.jsp";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        UserLogService recordLog =new UserLogService();
        recordLog.recordUserLog(request,response,Main + "退出账户，返回主页面");
        session.invalidate();
        request.getRequestDispatcher(Main).forward(request,response);
    }
}
