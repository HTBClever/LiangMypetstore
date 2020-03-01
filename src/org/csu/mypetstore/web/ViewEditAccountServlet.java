package org.csu.mypetstore.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ViewEditAccountServlet extends HttpServlet {
    private static final String VIEWEDITACCOUNT="/WEB-INF/jsp/account/EditAccountForm.jsp";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(VIEWEDITACCOUNT).forward(request,response);
    }
}
