package com.example.flattemp.Model;

public class Staff_Model {
    String staff_name,staff_img,staff_post,staff_about;

    public String getStaff_name() {
        return staff_name;
    }

    public void setStaff_name(String staff_name) {
        this.staff_name = staff_name;
    }

    public String getStaff_img() {
        return staff_img;
    }

    public void setStaff_img(String staff_img) {
        this.staff_img = staff_img;
    }

    public String getStaff_post() {
        return staff_post;
    }

    public void setStaff_post(String staff_post) {
        this.staff_post = staff_post;
    }

    public String getStaff_about() {
        return staff_about;
    }

    public void setStaff_about(String staff_about) {
        this.staff_about = staff_about;
    }

    public Staff_Model(String staff_name, String staff_img, String staff_post, String staff_about) {
        this.staff_name = staff_name;
        this.staff_img = staff_img;
        this.staff_post = staff_post;
        this.staff_about = staff_about;
    }
}
