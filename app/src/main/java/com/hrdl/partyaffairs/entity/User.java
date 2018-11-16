package com.hrdl.partyaffairs.entity;

/**
 * 用户信息
 *
 * @author dzb
 */
public class User {

    /**
     * id
     */
    private String id;
    /**
     * 登录名
     */
    private String loginName;
    /**
     * 登录密码
     */
    private String passWord;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 单位
     */
    private String company;
    /**
     * 头像地址
     */
    private String headUrl;
    /**
     * 姓名
     */
    private String name;
    /**
     * 性别 1-男，2-女
     */
    private String sex;
    /**
     * 出生日期
     */
    private long birthday;
    /**
     * 地区
     */
    private String region;
    /**
     * 是否更改过登录名
     */
    private boolean isChangedLoginName;
    /**
     * 用户类别
     */
    private String userType;
    /**
     * 用户籍贯
     */
    private String userBirthplace;
    /**
     * 民族
     */
    private String userNation;
    /**
     * 教育程度
     */
    private String userEducationLevel;
    /**
     * 职务
     */
    private String userDuty;
    /**
     * 人员简介
     */
    private String userExcerpt;

    public String getUserExcerpt() {
        return userExcerpt;
    }

    public void setUserExcerpt(String userExcerpt) {
        this.userExcerpt = userExcerpt;
    }

    public String getUserDuty() {
        return userDuty;
    }

    public void setUserDuty(String userDuty) {
        this.userDuty = userDuty;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getUserEducationLevel() {
        return userEducationLevel;
    }

    public void setUserEducationLevel(String userEducationLevel) {
        this.userEducationLevel = userEducationLevel;
    }

    public String getUserNation() {
        return userNation;
    }

    public void setUserNation(String userNation) {
        this.userNation = userNation;
    }

    public String getUserBirthplace() {
        return userBirthplace;
    }

    public void setUserBirthplace(String userBirthplace) {
        this.userBirthplace = userBirthplace;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public boolean isChangedLoginName() {
        return isChangedLoginName;
    }

    public void setChangedLoginName(boolean changedLoginName) {
        isChangedLoginName = changedLoginName;
    }
    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", loginName='" + loginName + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", headUrl='" + headUrl + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday=" + birthday +
                ", region='" + region + '\'' +
                '}';
    }
}
