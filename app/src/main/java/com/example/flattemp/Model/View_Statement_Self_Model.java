package com.example.flattemp.Model;

public class View_Statement_Self_Model {
    public View_Statement_Self_Model(String pay_id, String pay_date, String mem_id, String mem_name, String mem_flat_num, String mem_flat_type, String pay_fixed, String pay_deposit, String pay_remaining, String pay_month, String pay_status) {
        this.pay_id = pay_id;
        this.pay_date = pay_date;
        this.mem_id = mem_id;
        this.mem_name = mem_name;
        this.mem_flat_num = mem_flat_num;
        this.mem_flat_type = mem_flat_type;
        this.pay_fixed = pay_fixed;
        this.pay_deposit = pay_deposit;
        this.pay_remaining = pay_remaining;
        this.pay_month = pay_month;
        this.pay_status = pay_status;
    }

    public String getPay_id() {
        return pay_id;
    }

    public void setPay_id(String pay_id) {
        this.pay_id = pay_id;
    }

    public String getPay_date() {
        return pay_date;
    }

    public void setPay_date(String pay_date) {
        this.pay_date = pay_date;
    }

    public String getMem_id() {
        return mem_id;
    }

    public void setMem_id(String mem_id) {
        this.mem_id = mem_id;
    }

    public String getMem_name() {
        return mem_name;
    }

    public void setMem_name(String mem_name) {
        this.mem_name = mem_name;
    }

    public String getMem_flat_num() {
        return mem_flat_num;
    }

    public void setMem_flat_num(String mem_flat_num) {
        this.mem_flat_num = mem_flat_num;
    }

    public String getMem_flat_type() {
        return mem_flat_type;
    }

    public void setMem_flat_type(String mem_flat_type) {
        this.mem_flat_type = mem_flat_type;
    }

    public String getPay_fixed() {
        return pay_fixed;
    }

    public void setPay_fixed(String pay_fixed) {
        this.pay_fixed = pay_fixed;
    }

    public String getPay_deposit() {
        return pay_deposit;
    }

    public void setPay_deposit(String pay_deposit) {
        this.pay_deposit = pay_deposit;
    }

    public String getPay_remaining() {
        return pay_remaining;
    }

    public void setPay_remaining(String pay_remaining) {
        this.pay_remaining = pay_remaining;
    }

    public String getPay_month() {
        return pay_month;
    }

    public void setPay_month(String pay_month) {
        this.pay_month = pay_month;
    }

    public String getPay_status() {
        return pay_status;
    }

    public void setPay_status(String pay_status) {
        this.pay_status = pay_status;
    }

    String pay_id,pay_date,mem_id,mem_name,mem_flat_num,mem_flat_type,pay_fixed,pay_deposit,pay_remaining,pay_month,pay_status;
}
