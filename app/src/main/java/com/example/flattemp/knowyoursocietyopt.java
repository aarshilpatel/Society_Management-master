package com.example.flattemp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.example.flattemp.Model.Document;

public class knowyoursocietyopt extends AppCompatActivity {
    CardView rules,documents,vendors,staffs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knowyoursocietyopt);
        rules=findViewById(R.id.rules);
        documents=findViewById(R.id.documents);
        vendors=findViewById(R.id.venders);
        staffs=findViewById(R.id.staffs);
        rules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(knowyoursocietyopt.this,RulesAndRegulation.class);

                startActivity(i);
            }
        });
        documents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(knowyoursocietyopt.this, DocumentsActivity.class);
                i.putExtra("cat","b");
                startActivity(i);
            }
        });
        vendors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(knowyoursocietyopt.this,Vendor_Details.class);
                i.putExtra("cat","c");
                startActivity(i);
            }
        });
        staffs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(knowyoursocietyopt.this,Staff_Info.class);

                startActivity(i);
            }
        });
    }
}
