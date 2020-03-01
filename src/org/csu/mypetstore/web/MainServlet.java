package org.csu.mypetstore.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

public class MainServlet extends HttpServlet {
    private static final String Main="/WEB-INF/jsp/catalog/Main.jsp";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String []cardTypes=new String[3];
        cardTypes[0]="Visa";
        cardTypes[1]="Mastercard";
        cardTypes[2]="American Express";
        HttpSession session=request.getSession();
        session.setAttribute("cardTypes",cardTypes);
        request.getRequestDispatcher(Main).forward(request,response);
    }
}
