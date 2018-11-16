package com.hrdl.partyaffairs.entity;

/**
 * Created by zhangwei on 2018/8/28.
 * 张伟所用实体类
 */
public class ZWInfo {

    //工作标题
    private String workTitle;
    //工作时间
    private String workTime;
    //工作图片
    private int workPhoto;
    //工作浏览数
    private String workNum;
    //工作内容
    private String workContent;

    public String getWorkNum() {
        return workNum;
    }

    public void setWorkNum(String workNum) {
        this.workNum = workNum;
    }

    public String getWorkContent() {
        return workContent;
    }

    public void setWorkContent(String workContent) {
        this.workContent = workContent;
    }

    public int getWorkPhoto() {
        return workPhoto;
    }

    public void setWorkPhoto(int workPhoto) {
        this.workPhoto = workPhoto;
    }

    public String getWorkTitle() {
        return workTitle;
    }

    public void setWorkTitle(String workTitle) {
        this.workTitle = workTitle;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

}
