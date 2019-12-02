package com.example.flattemp.Model;

public class Event {
//$update_upcoming_events_id,$update_upcoming_events,$update_upcoming_events_date
    String  update_upcoming_events_id,update_upcoming_events,update_upcoming_events_date,update_upcoming_events_subject,update_upcoming_events_img;

    public Event() {
    }

    public Event(String update_upcoming_events_id, String update_upcoming_events, String update_upcoming_events_date, String update_upcoming_events_subject, String update_upcoming_events_img) {
        this.update_upcoming_events_id = update_upcoming_events_id;
        this.update_upcoming_events = update_upcoming_events;
        this.update_upcoming_events_date = update_upcoming_events_date;
        this.update_upcoming_events_subject = update_upcoming_events_subject;
        this.update_upcoming_events_img = update_upcoming_events_img;
    }

    public String getUpdate_upcoming_events_subject() {
        return update_upcoming_events_subject;
    }

    public void setUpdate_upcoming_events_subject(String update_upcoming_events_subject) {
        this.update_upcoming_events_subject = update_upcoming_events_subject;
    }

    public String getUpdate_upcoming_events_img() {
        return update_upcoming_events_img;
    }

    public void setUpdate_upcoming_events_img(String update_upcoming_events_img) {
        this.update_upcoming_events_img = update_upcoming_events_img;
    }

    public String getUpdate_upcoming_events_id() {
        return update_upcoming_events_id;
    }

    public void setUpdate_upcoming_events_id(String update_upcoming_events_id) {
        this.update_upcoming_events_id = update_upcoming_events_id;
    }

    public String getUpdate_upcoming_events() {
        return update_upcoming_events;
    }

    public void setUpdate_upcoming_events(String update_upcoming_events) {
        this.update_upcoming_events = update_upcoming_events;
    }

    public String getUpdate_upcoming_events_date() {
        return update_upcoming_events_date;
    }

    public void setUpdate_upcoming_events_date(String update_upcoming_events_date) {
        this.update_upcoming_events_date = update_upcoming_events_date;
    }
}
