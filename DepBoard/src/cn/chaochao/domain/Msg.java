package cn.tedu.domain;

import java.util.Date;

public class Msg {
    private int id;
    private String sendUser;
    private String msgs;
    private String recUser;
    private String sendTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSendUser() {
        return sendUser;
    }

    public void setSendUser(String sendUser) {
        this.sendUser = sendUser;
    }

    public String getMsgs() {
        return msgs;
    }

    public void setMsgs(String msgs) {
        this.msgs = msgs;
    }

    public String getRecUser() {
        return recUser;
    }

    public void setRecUser(String recUser) {
        this.recUser = recUser;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public Msg() {
    }

    public Msg(int id, String sendUser, String msgs, String recUser, String sendTime) {
        this.id = id;
        this.sendUser = sendUser;
        this.msgs = msgs;
        this.recUser = recUser;
        this.sendTime = sendTime;
    }
}
