package org.csu.mypetstore.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ViewSignOnServlet extends HttpServlet {

    public static final long serialVersionUID = 3038623696184546092L;
    private static final String ViewSIGNON = "/WEB-INF/jsp/account/SignonForm.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        String yanZhengMessage=null;
        session.setAttribute("yanZhengMessage",yanZhengMessage);
        request.getRequestDispatcher(ViewSIGNON).forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }


}
