package com.example.flattemp.Model;

public class Notice_Model {
    String id,notice,date;

    public Notice_Model(String id, String notice, String date) {
        this.id = id;
        this.notice = notice;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
