package org.csu.mypetstore.persistence.impl;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.persistence.AccountDAO;
import org.csu.mypetstore.persistence.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AccountDAOImpl implements AccountDAO {
    private static final String GET_ACCOUNT_BY_NAME="SELECT SIGNON.USERNAME, ACCOUNT.EMAIL, ACCOUNT.FIRSTNAME, ACCOUNT.LASTNAME, ACCOUNT.STATUS, ACCOUNT.ADDR1 AS address1, ACCOUNT.ADDR2 AS address2, ACCOUNT.CITY, ACCOUNT.STATE, ACCOUNT.ZIP, ACCOUNT.COUNTRY, ACCOUNT.PHONE, PROFILE.LANGPREF AS languagePreference, PROFILE.FAVCATEGORY AS favouriteCategoryId, PROFILE.MYLISTOPT AS listOption, PROFILE.BANNEROPT AS bannerOption, BANNERDATA.BANNERNAME FROM ACCOUNT, PROFILE, SIGNON, BANNERDATA WHERE ACCOUNT.USERID = ? AND SIGNON.USERNAME = ACCOUNT.USERID AND PROFILE.USERID = ACCOUNT.USERID AND PROFILE.FAVCATEGORY = BANNERDATA.FAVCATEGORY";
    private static final String GET_ACCOUNT="SELECT SIGNON.USERNAME, ACCOUNT.EMAIL, ACCOUNT.FIRSTNAME, ACCOUNT.LASTNAME, ACCOUNT.STATUS, ACCOUNT.ADDR1 AS address1, ACCOUNT.ADDR2 AS address2, ACCOUNT.CITY, ACCOUNT.STATE, ACCOUNT.ZIP, ACCOUNT.COUNTRY, ACCOUNT.PHONE, PROFILE.LANGPREF AS languagePreference, PROFILE.FAVCATEGORY AS favouriteCategoryId, PROFILE.MYLISTOPT AS listOption, PROFILE.BANNEROPT AS bannerOption, BANNERDATA.BANNERNAME FROM ACCOUNT, PROFILE, SIGNON, BANNERDATA WHERE ACCOUNT.USERID = ? AND SIGNON.PASSWORD = ? AND SIGNON.USERNAME = ACCOUNT.USERID AND PROFILE.USERID = ACCOUNT.USERID AND PROFILE.FAVCATEGORY = BANNERDATA.FAVCATEGORY";
    private static final String INSERTACCOUNT="INSERT INTO ACCOUNT (EMAIL, FIRSTNAME, LASTNAME, STATUS, ADDR1, ADDR2, CITY, STATE, ZIP, COUNTRY, PHONE, USERID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String INSERTPROFILE="INSERT INTO PROFILE (LANGPREF, FAVCATEGORY, USERID,mylistopt,banneropt) VALUES (?, ?, ?,?,?)";
    private static final String INSERTSIGNON="INSERT INTO SIGNON (PASSWORD,USERNAME) VALUES (?, ?)";
    private static final String UPDATEACCOUNT="UPDATE ACCOUNT SET EMAIL = ?, FIRSTNAME = ?, LASTNAME = ?, STATUS = ?, ADDR1 = ?, ADDR2 = ?, CITY = ?, STATE = ?, ZIP = ?, COUNTRY = ?, PHONE = ? WHERE USERID = ?";
    private static final String UPDATEPROFILE="UPDATE PROFILE SET LANGPREF = ?, FAVCATEGORY = ? WHERE USERID = ?";
    private static final String UPDATESIGNON="UPDATE SIGNON SET PASSWORD = ? WHERE USERNAME = ?";
    @Override
    public Account getAccountByUsername(String username) {
        Account account=null;
        try {
            Connection conn= DBUtil.getconn();
            PreparedStatement ps=conn.prepareStatement(GET_ACCOUNT_BY_NAME);
            ps.setString(1,username);
            ResultSet rs=ps.executeQuery();
            if (rs.next()){
                account=new Account();
                account.setUsername(rs.getString(1));
                account.setEmail(rs.getString(2));
                account.setFirstName(rs.getString(3));
                account.setLastName(rs.getString(4));
                account.setStatus(rs.getString(5));
                account.setAddress1(rs.getString(6));
                account.setAddress2(rs.getString(7));
                account.setCity(rs.getString(8));
                account.setState(rs.getString(9));
                account.setZip(rs.getString(10));
                account.setCountry(rs.getString(11));
                account.setPhone(rs.getString(12));
                account.setLanguagePreference(rs.getString(13));
                account.setFavouriteCategoryId(rs.getString(14));
                account.setListOption(rs.getBoolean(15));
                account.setBannerOption(rs.getBoolean(16));
                account.setBannerName(rs.getString(17));
            }
            DBUtil.closeAll(conn,ps,rs);
        }catch (Exception e){
            e.printStackTrace();
        }
        return account;
    }

    @Override
    public Account getAccountByUsernameAndPassword(Account account) {
        Account account1=null;
        try {
            Connection conn=DBUtil.getconn();
            PreparedStatement ps=conn.prepareStatement(GET_ACCOUNT);
            ps.setString(1,account.getUsername());
            ps.setString(2,account.getPassword());
            ResultSet rs=ps.executeQuery();
            if (rs.next()){
                account1=new Account();
                account1.setUsername(rs.getString(1));
                account1.setEmail(rs.getString(2));
                account1.setFirstName(rs.getString(3));
                account1.setLastName(rs.getString(4));
                account1.setStatus(rs.getString(5));
                account1.setAddress1(rs.getString(6));
                account1.setAddress2(rs.getString(7));
                account1.setCity(rs.getString(8));
                account1.setState(rs.getString(9));
                account1.setZip(rs.getString(10));
                account1.setCountry(rs.getString(11));
                account1.setPhone(rs.getString(12));
                account1.setLanguagePreference(rs.getString(13));
                account1.setFavouriteCategoryId(rs.getString(14));
                account1.setListOption(rs.getBoolean(15));
                account1.setBannerOption(rs.getBoolean(16));
                account1.setBannerName(rs.getString(17));
            }
            DBUtil.closeAll(conn,ps,rs);
        }catch (Exception e){
            e.printStackTrace();
        }
        return account1;
    }

    @Override
    public void insertAccount(Account account) {
        try {
            Connection conn=DBUtil.getconn();
            PreparedStatement ps=conn.prepareStatement(INSERTACCOUNT);
            ps.setString(1,account.getEmail());
            ps.setString(2,account.getFirstName());
            ps.setString(3,account.getLastName());
            ps.setString(4,account.getStatus());
            ps.setString(5,account.getAddress1());
            ps.setString(6,account.getAddress2());
            ps.setString(7,account.getCity());
            ps.setString(8,account.getState());
            ps.setString(9,account.getZip());
            ps.setString(10,account.getCountry());
            ps.setString(11,account.getPhone());
            ps.setString(12,account.getUsername());
            ps.executeUpdate();
            ResultSet rs=null;
            DBUtil.closeAll(conn,ps,rs);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void insertProfile(Account account) {
        try{
            Connection conn=DBUtil.getconn();
            PreparedStatement ps=conn.prepareStatement(INSERTPROFILE);
            ps.setString(1,account.getLanguagePreference());
            ps.setString(2,account.getFavouriteCategoryId());
            ps.setString(3,account.getUsername());
            if (account.isListOption())
                ps.setInt(4,1);
            else
                ps.setInt(4,0);
            if (account.isBannerOption())
                ps.setInt(5,1);
            else
                ps.setInt(5,0);
            ps.executeUpdate();
            ResultSet rs=null;
            DBUtil.closeAll(conn,ps,rs);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void insertSignon(Account account) {
        try {
            Connection conn=DBUtil.getconn();
            PreparedStatement ps=conn.prepareStatement(INSERTSIGNON);
            ps.setString(1,account.getPassword());
            ps.setString(2,account.getUsername());
            ps.executeUpdate();
            ResultSet rs=null;
            DBUtil.closeAll(conn,ps,rs);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateAccount(Account account) {
        try {
            Connection conn=DBUtil.getconn();
            PreparedStatement ps=conn.prepareStatement(UPDATEACCOUNT);
            ps.setString(1,account.getEmail());
            ps.setString(2,account.getFirstName());
            ps.setString(3,account.getLastName());
            ps.setString(4,account.getStatus());
            ps.setString(5,account.getAddress1());
            ps.setString(6,account.getAddress2());
            ps.setString(7,account.getCity());
            ps.setString(8,account.getState());
            ps.setString(9,account.getZip());
            ps.setString(10,account.getCountry());
            ps.setString(11,account.getPhone());
            ps.setString(12,account.getUsername());
            ps.executeUpdate();
            ResultSet rs=null;
            DBUtil.closeAll(conn,ps,rs);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateProfile(Account account) {
        try{
            Connection conn=DBUtil.getconn();
            PreparedStatement ps=conn.prepareStatement(UPDATEPROFILE);
            ps.setString(1,account.getLanguagePreference());
            ps.setString(2,account.getFavouriteCategoryId());
            ps.setString(3,account.getUsername());
            ps.executeUpdate();
            ResultSet rs=null;
            DBUtil.closeAll(conn,ps,rs);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateSignon(Account account) {
        try {
            Connection conn=DBUtil.getconn();
            PreparedStatement ps=conn.prepareStatement(UPDATESIGNON);
            ps.setString(1,account.getPassword());
            ps.setString(2,account.getUsername());
            ps.executeUpdate();
            ResultSet rs=null;
            DBUtil.closeAll(conn,ps,rs);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
