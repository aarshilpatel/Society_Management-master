package com.example.flattemp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.example.flattemp.Model.GalleryCatagory;

public class Lifeatsociety extends AppCompatActivity {
    CardView gallery,upcomingevent,chairmanmessage,payment,visitor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifeatsociety);
        gallery=findViewById(R.id.gallery);
        upcomingevent=findViewById(R.id.upcomingevents);
        chairmanmessage=findViewById(R.id.chairman);

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Lifeatsociety.this, GalleryCatagoryActivity.class
                ));
            }
        });
        upcomingevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Lifeatsociety.this, UpcomingEvents.class));
            }
        });
        chairmanmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Lifeatsociety.this, ScrollingActivityChairman.class));
            }
        });
      /*  payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PayActivity.class));
            }
        });
        visitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Addvisitor.class));
            }
        });*/

    }
}
