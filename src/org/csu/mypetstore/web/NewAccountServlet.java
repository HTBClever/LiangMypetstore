package org.csu.mypetstore.web;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class NewAccountServlet extends HttpServlet {
    private Account account;
    private AccountService service;
    private static final String Main="/WEB-INF/jsp/catalog/Main.jsp";
    private static final String VIEW_NEWACOUNT="/WEB-INF/jsp/account/NewAccountForm.jsp";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        //设置头部和信息防止乱码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        String clientCheckcode = request.getParameter("checkYan").toUpperCase();//接受客户端提交上来的参数（使用toUpperCase()可以实现不区分大小写的操作）
        String serverCheckcode = (String) session.getAttribute("checkcode");//从session中提取验证码
        if (clientCheckcode.equals(serverCheckcode)) {
            account = new Account();
            service = new AccountService();
            account.setUsername(request.getParameter("username"));
            account.setPassword(request.getParameter("password"));
            account.setFirstName(request.getParameter("account.firstName"));
            account.setLastName(request.getParameter("account.lastName"));
            account.setEmail(request.getParameter("account.email"));
            account.setPhone(request.getParameter("account.phone"));
            account.setAddress1(request.getParameter("account.address1"));
            account.setAddress2(request.getParameter("account.address2"));
            account.setCity(request.getParameter("account.city"));
            account.setState(request.getParameter("account.state"));
            account.setZip(request.getParameter("account.zip"));
            account.setCountry(request.getParameter("account.country"));
            account.setLanguagePreference(request.getParameter("account.languagePreference"));
            account.setFavouriteCategoryId(request.getParameter("account.favouriteCategoryId"));
            if (request.getParameter("account.listOption") != null)
                account.setListOption(true);
            if (request.getParameter("account.bannerOption") != null)
                account.setBannerOption(true);
            service.insertAccount(account);
            request.getRequestDispatcher(Main).forward(request, response);
        }
        else {
            session.setAttribute("yanZhengMessage","Error entering captcha");
            request.getRequestDispatcher(VIEW_NEWACOUNT).forward(request,response);
        }
    }
}
