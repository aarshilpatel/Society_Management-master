package com.example.flattemp.Model;

public class Document {

    //$update_rule_file_id,$update_rule_file,$update_rule_file_date,$update_rule_file_type,
    // $update_rule_file_for,$update_rule_file_loca

    String update_rule_file_id,update_rule_file,update_rule_file_date,update_rule_file_type,update_rule_file_for,update_rule_file_loca;

    public Document() {
    }

    public Document(String update_rule_file_id, String update_rule_file, String update_rule_file_date, String update_rule_file_type, String update_rule_file_for, String update_rule_file_loca) {
        this.update_rule_file_id = update_rule_file_id;
        this.update_rule_file = update_rule_file;
        this.update_rule_file_date = update_rule_file_date;
        this.update_rule_file_type = update_rule_file_type;
        this.update_rule_file_for = update_rule_file_for;
        this.update_rule_file_loca = update_rule_file_loca;
    }

    public String getUpdate_rule_file_id() {
        return update_rule_file_id;
    }

    public void setUpdate_rule_file_id(String update_rule_file_id) {
        this.update_rule_file_id = update_rule_file_id;
    }

    public String getUpdate_rule_file() {
        return update_rule_file;
    }

    public void setUpdate_rule_file(String update_rule_file) {
        this.update_rule_file = update_rule_file;
    }

    public String getUpdate_rule_file_date() {
        return update_rule_file_date;
    }

    public void setUpdate_rule_file_date(String update_rule_file_date) {
        this.update_rule_file_date = update_rule_file_date;
    }

    public String getUpdate_rule_file_type() {
        return update_rule_file_type;
    }

    public void setUpdate_rule_file_type(String update_rule_file_type) {
        this.update_rule_file_type = update_rule_file_type;
    }

    public String getUpdate_rule_file_for() {
        return update_rule_file_for;
    }

    public void setUpdate_rule_file_for(String update_rule_file_for) {
        this.update_rule_file_for = update_rule_file_for;
    }

    public String getUpdate_rule_file_loca() {
        return update_rule_file_loca;
    }

    public void setUpdate_rule_file_loca(String update_rule_file_loca) {
        this.update_rule_file_loca = update_rule_file_loca;
    }
}
