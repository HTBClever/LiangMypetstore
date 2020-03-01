package org.csu.mypetstore.web;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.service.AccountService;
import org.csu.mypetstore.service.UserLogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignonServlet extends HttpServlet {

    private static final String ViewSIGNON = "/WEB-INF/jsp/account/SignonForm.jsp";
    private static final String Main = "/WEB-INF/jsp/catalog/Main.jsp";
    private AccountService service;
    private Account account;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        service = new AccountService();
        account = service.getAccount(request.getParameter("username"), request.getParameter("password"));
        HttpSession session = request.getSession();
        //设置头部和信息防止乱码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        String clientCheckcode = request.getParameter("checkYan").toUpperCase();//接受客户端提交上来的参数（使用toUpperCase()可以实现不区分大小写的操作）
        String serverCheckcode = (String) session.getAttribute("checkcode");//从session中提取验证码

        //记录类
        UserLogService recordLog = new UserLogService();

        if (clientCheckcode.equals(serverCheckcode)) {
            if (account == null) {
                String yanZhengMessage=null;
                session.setAttribute("signonMessage", "Invalid username or password.  Signon failed.");
                session.setAttribute("yanZhengMessage",yanZhengMessage);
                request.getRequestDispatcher(ViewSIGNON).forward(request, response);

            } else {
                String []language={"japanese","english"};
                String []favouriteCategoryId={"BIRDS","CATS","DOGS","FISH","REPTILES"};
                String yanZhengMessage=null;
                String signonMessage=null;
                session.setAttribute("yanZhengMessage",yanZhengMessage);
                session.setAttribute("signonMessage",signonMessage);
                session.setAttribute("language",language);
                session.setAttribute("favouriteCategoryId",favouriteCategoryId);
                session.setAttribute("account", account);

                //记录日志
                recordLog.recordUserLog(request,response, "登录成功，返回主页面");
                request.getRequestDispatcher(Main).forward(request, response);
            }
        } else {
            String signonMessage=null;
            session.setAttribute("signonMessage",signonMessage);
            session.setAttribute("yanZhengMessage","Error entering captcha");
            request.getRequestDispatcher(ViewSIGNON).forward(request, response);
        }


    }
}
