package com.example.flattemp.Model;

public class Rules_Model {
    String rules_file,rules_name;

    public String getRules_file() {
        return rules_file;
    }

    public void setRules_file(String rules_file) {
        this.rules_file = rules_file;
    }

    public String getRules_name() {
        return rules_name;
    }

    public void setRules_name(String rules_name) {
        this.rules_name = rules_name;
    }

    public Rules_Model(String rules_file, String rules_name) {
        this.rules_file = rules_file;
        this.rules_name = rules_name;
    }
}
