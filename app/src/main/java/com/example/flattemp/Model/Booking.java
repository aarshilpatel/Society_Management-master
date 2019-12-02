package com.example.flattemp.Model;

public class Booking {
    String facility,booked_date,date_from,date_to,booking_reason,booked_status,booking_id;

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public String getBooked_date() {
        return booked_date;
    }

    public void setBooked_date(String booked_date) {
        this.booked_date = booked_date;
    }

    public String getDate_from() {
        return date_from;
    }

    public void setDate_from(String date_from) {
        this.date_from = date_from;
    }

    public String getDate_to() {
        return date_to;
    }

    public void setDate_to(String date_to) {
        this.date_to = date_to;
    }

    public String getBooking_reason() {
        return booking_reason;
    }

    public void setBooking_reason(String booking_reason) {
        this.booking_reason = booking_reason;
    }

    public String getBooked_status() {
        return booked_status;
    }

    public void setBooked_status(String booked_status) {
        this.booked_status = booked_status;
    }

    public String getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(String booking_id) {
        this.booking_id = booking_id;
    }

    public Booking(String facility, String booked_date, String date_from, String date_to, String booking_reason, String booked_status, String booking_id) {
        this.facility = facility;
        this.booked_date = booked_date;
        this.date_from = date_from;
        this.date_to = date_to;
        this.booking_reason = booking_reason;
        this.booked_status = booked_status;
        this.booking_id = booking_id;
    }
}
