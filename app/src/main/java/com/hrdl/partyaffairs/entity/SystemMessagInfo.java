package com.hrdl.partyaffairs.entity;

/**
 * 作者：王健 on 2018/8/31
 * 邮箱：845040970@qq.com
 */
public class SystemMessagInfo {
    private String id;
    private String title;
    private String content;
    private String sendTime;
    private String sendTimeLongNum;//时间戳（毫秒）
    private boolean unRead;//已读未读状态区分
    private boolean bo;

    public boolean isUnRead() {
        return unRead;
    }

    public void setUnRead(boolean unRead) {
        this.unRead = unRead;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getSendTimeLongNum() {
        return sendTimeLongNum;
    }

    public void setSendTimeLongNum(String sendTimeLongNum) {
        this.sendTimeLongNum = sendTimeLongNum;
    }

    public boolean isBo() {
        return bo;
    }

    public void setBo(boolean bo) {
        this.bo = bo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
