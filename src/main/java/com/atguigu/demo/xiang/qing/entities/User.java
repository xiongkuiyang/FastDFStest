/******************************************************************
 *
 *    Java Lib For Android, Powered By Shenzhen Jiuzhou.
 *
 *    Copyright (c) 2001-2014 Digital Telemedia Co.,Ltd
 *    http://www.d-telemedia.com/
 *
 *    Package:     com.atguigu.demo.xiang.qing.entities
 *
 *    Filename:    User.java
 *
 *    Description: TODO(用一句话描述该文件做什么)
 *
 *    Copyright:   Copyright (c) 2001-2014
 *
 *    Company:     Digital Telemedia Co.,Ltd
 *
 *    @author:     Administrator
 *
 *    @version:    1.0.0
 *
 *    Create at:   2017年10月9日 下午6:35:37
 *
 *    Revision:
 *
 *    2017年10月9日 下午6:35:37
 *        - first revision
 *
 *****************************************************************/
package com.atguigu.demo.xiang.qing.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="demo_xiang_qin")
public class User {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer userId;
    private String userName;
    private String userPwd;
    private String userNick;
    private int userGender;
    private String userJob;
    private String userHometown;
    private String userDesc;
    private String userPicGroup;
    private String userPicFilename;
    private String tempOne;
    private String tempTwo;
    
    public User() {
        super();
    }

    public User(Integer userId, String userName, String userPwd, String userNick, int userGender, String userJob,
            String userHometown, String userDesc, String userPicGroup, String userPicFilename, String tempOne,
            String tempTwo) {
        super();
        this.userId = userId;
        this.userName = userName;
        this.userPwd = userPwd;
        this.userNick = userNick;
        this.userGender = userGender;
        this.userJob = userJob;
        this.userHometown = userHometown;
        this.userDesc = userDesc;
        this.userPicGroup = userPicGroup;
        this.userPicFilename = userPicFilename;
        this.tempOne = tempOne;
        this.tempTwo = tempTwo;
    }

    
    public Integer getUserId() {
        return userId;
    }

    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    
    public String getUserName() {
        return userName;
    }

    
    public void setUserName(String userName) {
        this.userName = userName;
    }

    
    public String getUserPwd() {
        return userPwd;
    }

    
    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    
    public String getUserNick() {
        return userNick;
    }

    
    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    
    public int getUserGender() {
        return userGender;
    }

    
    public void setUserGender(int userGender) {
        this.userGender = userGender;
    }

    
    public String getUserJob() {
        return userJob;
    }

    
    public void setUserJob(String userJob) {
        this.userJob = userJob;
    }

    
    public String getUserHometown() {
        return userHometown;
    }

    
    public void setUserHometown(String userHometown) {
        this.userHometown = userHometown;
    }

    
    public String getUserDesc() {
        return userDesc;
    }

    
    public void setUserDesc(String userDesc) {
        this.userDesc = userDesc;
    }

    
    public String getUserPicGroup() {
        return userPicGroup;
    }

    
    public void setUserPicGroup(String userPicGroup) {
        this.userPicGroup = userPicGroup;
    }

    
    public String getUserPicFilename() {
        return userPicFilename;
    }

    
    public void setUserPicFilename(String userPicFilename) {
        this.userPicFilename = userPicFilename;
    }

    
    public String getTempOne() {
        return tempOne;
    }

    
    public void setTempOne(String tempOne) {
        this.tempOne = tempOne;
    }

    
    public String getTempTwo() {
        return tempTwo;
    }

    
    public void setTempTwo(String tempTwo) {
        this.tempTwo = tempTwo;
    }

    @Override
    public String toString() {
        return "User [userId=" + userId + ", userName=" + userName + ", userPwd=" + userPwd + ", userNick=" + userNick
                + ", userGender=" + userGender + ", userJob=" + userJob + ", userHometown=" + userHometown
                + ", userDesc=" + userDesc + ", userPicGroup=" + userPicGroup + ", userPicFilename=" + userPicFilename
                + ", tempOne=" + tempOne + ", tempTwo=" + tempTwo + "]";
    }
    
}