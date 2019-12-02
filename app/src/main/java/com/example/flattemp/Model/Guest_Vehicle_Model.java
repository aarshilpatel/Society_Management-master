package com.example.flattemp.Model;

public class Guest_Vehicle_Model {
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

    public String getVehicle_type() {
        return vehicle_type;
    }

    public void setVehicle_type(String vehicle_type) {
        this.vehicle_type = vehicle_type;
    }

    public String getVehicle_num() {
        return vehicle_num;
    }

    public void setVehicle_num(String vehicle_num) {
        this.vehicle_num = vehicle_num;
    }

    public Guest_Vehicle_Model(String visitor_name, String visitor_phone_num, String mem_block_num, String mem_flat_num, String vehicle_type, String vehicle_num) {
        this.visitor_name = visitor_name;
        this.visitor_phone_num = visitor_phone_num;
        this.mem_block_num = mem_block_num;
        this.mem_flat_num = mem_flat_num;
        this.vehicle_type = vehicle_type;
        this.vehicle_num = vehicle_num;
    }

    String visitor_name,visitor_phone_num,mem_block_num,mem_flat_num,vehicle_type,vehicle_num;
}
