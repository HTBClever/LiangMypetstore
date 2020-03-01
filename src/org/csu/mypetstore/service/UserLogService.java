package org.csu.mypetstore.service;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.UserLog;
import org.csu.mypetstore.persistence.impl.UserLogDaoImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class UserLogService {

/*
    //日志txt文件初始化时写入用户信息
    public void recordUserAccount(HttpServletRequest request, HttpServletResponse response)
    {
        try {
            response.setContentType("text/html;charset=gbk");
            String sessionId =  request.getSession().getId();
            File f =new File("log/" + sessionId + ".txt");
            BufferedWriter bw=new BufferedWriter(new FileWriter(f,true));

            Account account = (Account)request.getSession().getAttribute("account");
            String accountInto = account.getUsername();
            System.out.println(accountInto);

            //分配seesionId和Account信息
            bw.write("SessionId:     "+sessionId+"\n" +   "UserName:      " +accountInto + "\n") ;
            UserLogService userLogService = new UserLogService();
            userLogService.inserLog(accountInto,sessionId);

            bw.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }





    //记录用户行为
    public void recordUserLog2(HttpServletRequest request, HttpServletResponse response,String urlPath)
    {
        try {
            //中文
            // 乱码
            response.setContentType("text/html;charset=gbk");
            PrintWriter pw=response.getWriter();
            String sessionId =  request.getSession().getId();
            //创建一个FileWriter  看内存数据 ->  磁盘文件(写入,输出)
            //看内存数据 <-  磁盘文件(读入,输入)
            File f;
            File upload = new File("log");
            //此处使用file.exists检查文件是否存在
            if (!upload.exists()) upload.mkdirs();

            //默认路径在tomcat容器中

            f =new File("log/" + sessionId + ".txt");
            BufferedWriter bw=new BufferedWriter(new FileWriter(f,true));

            //在文件中写入数据
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

            bw.write(urlPath + "                "   + df.format(new Date())  + "\n");
            //关闭文件流
            bw.close();

            pw.println("在文件中写入了数据");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }




    //此处为插入sessionId与userName的对应关系,被recordUserAccount调用
    public void inserLog(String userName, String sessionId)
    {
        UserLogDaoImpl userLogDaoImpl = new UserLogDaoImpl();
        userLogDaoImpl.insertLog(userName,sessionId);
    }
*/

    public void recordUserLog(HttpServletRequest request, HttpServletResponse response,String urlPath)
    {
        UserLogDaoImpl userLogDaoImpl = new UserLogDaoImpl();
        //此处未登陆的话session没有account属性
        if(request.getSession().getAttribute("account") != null)
        {
            Date currentTime = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Account account = (Account)request.getSession().getAttribute("account");
            String userName = account.getUsername();
            userLogDaoImpl.insertLog(userName,urlPath,formatter.format(currentTime));
        }
    }

    public ArrayList<UserLog> getALLUserLog(Account account)
    {
        UserLogDaoImpl userLogDaoImpl = new UserLogDaoImpl();
        ArrayList<UserLog> userLogList = userLogDaoImpl.getAllUserLog(account);
        return userLogList;
    }

}
