package com.example.flattemp.Model;

public class Project_Expences_Model {
    String exp_name,exp_date,exp_amount;

    public String getExp_name() {
        return exp_name;
    }

    public void setExp_name(String exp_name) {
        this.exp_name = exp_name;
    }

    public String getExp_date() {
        return exp_date;
    }

    public void setExp_date(String exp_date) {
        this.exp_date = exp_date;
    }

    public String getExp_amount() {
        return exp_amount;
    }

    public void setExp_amount(String exp_amount) {
        this.exp_amount = exp_amount;
    }

    public Project_Expences_Model(String exp_name, String exp_date, String exp_amount) {
        this.exp_name = exp_name;
        this.exp_date = exp_date;
        this.exp_amount = exp_amount;
    }
}
