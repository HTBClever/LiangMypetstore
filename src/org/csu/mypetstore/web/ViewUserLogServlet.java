package org.csu.mypetstore.web;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.UserLog;
import org.csu.mypetstore.service.UserLogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class ViewUserLogServlet extends HttpServlet {

    private static final String userLogPage = "/WEB-INF/jsp/log/UserLog.jsp";
    private Account account;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserLogService userLogService = new UserLogService();
        HttpSession session= request.getSession();
        account= (Account) session.getAttribute("account");
        ArrayList<UserLog> userLogList =userLogService.getALLUserLog(account);


        session.setAttribute("userLogList",userLogList);

        request.getRequestDispatcher(userLogPage).forward(request,response);
    }
}
