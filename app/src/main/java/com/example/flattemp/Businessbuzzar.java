package com.example.flattemp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.example.flattemp.Model.Document;

public class Businessbuzzar extends AppCompatActivity {
    CardView publicwall,offers,halloffame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_businessbuzzar);
        publicwall=findViewById(R.id.posts);
        offers=findViewById(R.id.offers);
        halloffame=findViewById(R.id.halloffame);
        publicwall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Businessbuzzar.this,Publicwall.class));
            }
        });
        offers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Businessbuzzar.this,Offers.class));
            }
        });
        halloffame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Businessbuzzar.this, Halloffame.class));
            }
        });
    }
}
