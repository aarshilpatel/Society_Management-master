package com.example.flattemp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class FacilityBooking extends AppCompatActivity {
    CardView visitor,booking,bookinghistory,guest_vehicle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facility_booking);
        visitor=findViewById(R.id.visitor);
        booking=findViewById(R.id.booking);
        guest_vehicle=findViewById(R.id.guest_vehicle);
        bookinghistory=findViewById(R.id.bookinghistory);
        visitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FacilityBooking.this,Visitor.class));
            }
        });
        booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FacilityBooking.this,BookingActivity.class));
            }
        });
        bookinghistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FacilityBooking.this,BookingHistory.class));
            }
        });

        guest_vehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FacilityBooking.this,Guest_Vehicle.class));
            }
        });

    }
}
