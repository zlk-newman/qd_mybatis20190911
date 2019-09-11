package com.yuw.bean;

import java.util.Date;

public class UserInfoBean {
    private Integer userid;

    private String username;

    private String userpsw;

    private String userage;

    private String useraddress;

    private Date userregdate;

    private Boolean isdeleted;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getUserpsw() {
        return userpsw;
    }

    public void setUserpsw(String userpsw) {
        this.userpsw = userpsw == null ? null : userpsw.trim();
    }

    public String getUserage() {
        return userage;
    }

    public void setUserage(String userage) {
        this.userage = userage == null ? null : userage.trim();
    }

    public String getUseraddress() {
        return useraddress;
    }

    public void setUseraddress(String useraddress) {
        this.useraddress = useraddress == null ? null : useraddress.trim();
    }

    public Date getUserregdate() {
        return userregdate;
    }

    public void setUserregdate(Date userregdate) {
        this.userregdate = userregdate;
    }

    public Boolean getIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(Boolean isdeleted) {
        this.isdeleted = isdeleted;
    }

    @Override
    public String toString() {
        return "UserInfoBean{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", userpsw='" + userpsw + '\'' +
                ", userage='" + userage + '\'' +
                ", useraddress='" + useraddress + '\'' +
                ", userregdate=" + userregdate +
                ", isdeleted=" + isdeleted +
                '}';
    }
}