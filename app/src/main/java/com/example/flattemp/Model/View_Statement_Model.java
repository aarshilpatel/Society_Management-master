package com.example.flattemp.Model;

public class View_Statement_Model {

    String mem_name,pay_date,pay_deposit,type;

    public String getMem_name() {
        return mem_name;
    }

    public void setMem_name(String mem_name) {
        this.mem_name = mem_name;
    }

    public String getPay_date() {
        return pay_date;
    }

    public void setPay_date(String pay_date) {
        this.pay_date = pay_date;
    }

    public String getPay_deposit() {
        return pay_deposit;
    }

    public void setPay_deposit(String pay_deposit) {
        this.pay_deposit = pay_deposit;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public View_Statement_Model(String mem_name, String pay_date, String pay_deposit, String type) {
        this.mem_name = mem_name;
        this.pay_date = pay_date;
        this.pay_deposit = pay_deposit;
        this.type = type;
    }
}


