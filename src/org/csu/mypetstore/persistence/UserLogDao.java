package org.csu.mypetstore.persistence;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.UserLog;

import java.util.ArrayList;

public interface UserLogDao {

    void insertLog(String userName, String userBehavior,String visitDate);

    ArrayList<UserLog> getAllUserLog(Account account);
}
