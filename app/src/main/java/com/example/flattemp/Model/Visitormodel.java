package com.example.flattemp.Model;

public class Visitormodel {

    String visitor_id,visitor_time,guard_name,visitor_name,visitor_phone_num,mem_block_num,mem_flat_num,visitor_purpose,visitor_img,visitor_vechile_img;

    public Visitormodel() {
    }

    public Visitormodel(String visitor_id, String visitor_time, String guard_name, String visitor_name,
                        String visitor_phone_num, String mem_block_num, String mem_flat_num, String visitor_purpose, String visitor_img, String visitor_vechile_img) {
        this.visitor_id = visitor_id;
        this.visitor_time = visitor_time;
        this.guard_name = guard_name;
        this.visitor_name = visitor_name;
        this.visitor_phone_num = visitor_phone_num;
        this.mem_block_num = mem_block_num;
        this.mem_flat_num = mem_flat_num;
        this.visitor_purpose = visitor_purpose;
        this.visitor_img = visitor_img;
        this.visitor_vechile_img = visitor_vechile_img;
    }

    public String getVisitor_id() {
        return visitor_id;
    }

    public void setVisitor_id(String visitor_id) {
        this.visitor_id = visitor_id;
    }

    public String getVisitor_time() {
        return visitor_time;
    }

    public void setVisitor_time(String visitor_time) {
        this.visitor_time = visitor_time;
    }

    public String getGuard_name() {
        return guard_name;
    }

    public void setGuard_name(String guard_name) {
        this.guard_name = guard_name;
    }

    public String getVisitor_name() {
        return visitor_name;
    }

    public void setVisitor_name(String visitor_name) {
        this.visitor_name = visitor_name;
    }

    public String getVisitor_phone_num() {
        return visitor_phone_num;
    }

    public void setVisitor_phone_num(String visitor_phone_num) {
        this.visitor_phone_num = visitor_phone_num;
    }

    public String getMem_block_num() {
        return mem_block_num;
    }

    public void setMem_block_num(String mem_block_num) {
        this.mem_block_num = mem_block_num;
    }

    public String getMem_flat_num() {
        return mem_flat_num;
    }

    public void setMem_flat_num(String mem_flat_num) {
        this.mem_flat_num = mem_flat_num;
    }

    public String getVisitor_purpose() {
        return visitor_purpose;
    }

    public void setVisitor_purpose(String visitor_purpose) {
        this.visitor_purpose = visitor_purpose;
    }

    public String getVisitor_img() {
        return visitor_img;
    }

    public void setVisitor_img(String visitor_img) {
        this.visitor_img = visitor_img;
    }

    public String getVisitor_vechile_img() {
        return visitor_vechile_img;
    }

    public void setVisitor_vechile_img(String visitor_vechile_img) {
        this.visitor_vechile_img = visitor_vechile_img;
    }
}
