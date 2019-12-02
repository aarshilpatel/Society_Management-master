package com.example.flattemp.Model;

public class Vendor_Model {
    String shop_name,owner_name,about_vendor,vendor_img;

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public String getAbout_vendor() {
        return about_vendor;
    }

    public void setAbout_vendor(String about_vendor) {
        this.about_vendor = about_vendor;
    }

    public String getVendor_img() {
        return vendor_img;
    }

    public void setVendor_img(String vendor_img) {
        this.vendor_img = vendor_img;
    }

    public Vendor_Model(String shop_name, String owner_name, String about_vendor, String vendor_img) {
        this.shop_name = shop_name;
        this.owner_name = owner_name;
        this.about_vendor = about_vendor;
        this.vendor_img = vendor_img;
    }
}
