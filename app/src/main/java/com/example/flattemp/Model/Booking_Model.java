package com.example.flattemp.Model;

public class Booking_Model {
    String name,image,booking_id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(String booking_id) {
        this.booking_id = booking_id;
    }

    public Booking_Model(String name, String image, String booking_id) {
        this.name = name;
        this.image = image;
        this.booking_id = booking_id;
    }
}
