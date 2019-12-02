package com.example.flattemp.Model;

public class Gallery {

    String gallery_img_id,gallery_img,gallery_img_name;

    public Gallery() {
    }
    public Gallery(String gallery_img_id, String gallery_img, String gallery_img_name) {
        this.gallery_img_id = gallery_img_id;
        this.gallery_img = gallery_img;
        this.gallery_img_name = gallery_img_name;
    }

    public String getGallery_img_id() {
        return gallery_img_id;
    }

    public void setGallery_img_id(String gallery_img_id) {
        this.gallery_img_id = gallery_img_id;
    }

    public String getGallery_img() {
        return gallery_img;
    }

    public void setGallery_img(String gallery_img) {
        this.gallery_img = gallery_img;
    }

    public String getGallery_img_name() {
        return gallery_img_name;
    }

    public void setGallery_img_name(String gallery_img_name) {
        this.gallery_img_name = gallery_img_name;
    }
}
