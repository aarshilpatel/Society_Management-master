package com.example.flattemp.Model;

public class GalleryCatagory {
    String id,catagory,icon,date;

    public GalleryCatagory() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public GalleryCatagory(String id, String catagory, String icon, String date) {
        this.id = id;
        this.catagory = catagory;
        this.icon = icon;
        this.date=date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCatagory() {
        return catagory;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
