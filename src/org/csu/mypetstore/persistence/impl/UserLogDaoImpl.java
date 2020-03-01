package org.csu.mypetstore.persistence.impl;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.UserLog;
import org.csu.mypetstore.persistence.DBUtil;
import org.csu.mypetstore.persistence.UserLogDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UserLogDaoImpl implements UserLogDao {

    String INSERLOG = "insert into UserLog(userName,userBehavior,visitDate) value(?,?,?)";
    String GET_ALL_USERLOG = "select visitDate,userName,userBehavior from UserLog where username=?";

    @Override
    public void insertLog(String userName, String userBehavior,String visitDate)
    {
        try {
            Connection conn= DBUtil.getconn();
            PreparedStatement ps=conn.prepareStatement(INSERLOG);
//            ps.setInt(1,1);
            ps.setString(1,userName);
            ps.setString(2,userBehavior);
            ps.setString(3,visitDate);
            ps.executeUpdate();
            ResultSet rs=null;
            DBUtil.closeAll(conn,ps,rs);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<UserLog>  getAllUserLog(Account account)
    {
        ArrayList<UserLog> userLogList = new ArrayList<UserLog>();
        try {
            Connection conn= DBUtil.getconn();
            PreparedStatement ps=conn.prepareStatement(GET_ALL_USERLOG);
            ps.setString(1,account.getUsername());
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
                UserLog userLog = new UserLog();
                userLog.setVisitDate(rs.getString(1));
                userLog.setUserName(rs.getString(2));
                userLog.setUserBehavior(rs.getString(3));
                userLogList.add(userLog);
            }

            DBUtil.closeAll(conn,ps,rs);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return userLogList;
    }

}
