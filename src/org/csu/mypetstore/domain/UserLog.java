package org.csu.mypetstore.domain;

public class UserLog {
    private int orderKey;
    private String userName;
    private String userBehavior;
    private String visitDate;

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    public int getOrderKey() {
        return orderKey;
    }

    public void setOrderKey(int orderKey) {
        this.orderKey = orderKey;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserBehavior() {
        return userBehavior;
    }

    public void setUserBehavior(String userBehavior) {
        this.userBehavior = userBehavior;
    }
}
