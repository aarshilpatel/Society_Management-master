package com.example.flattemp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.flattemp.Model.UrlsList;
import com.squareup.picasso.Picasso;

public class ShowSingleVisitor extends AppCompatActivity {
    TextView vivitorname,visitormobno,guardname,memblockno,memflatno,time,vivitngpurpose;
    ImageView viistorimg,vivitorvehicalimg;
    LinearLayout vvimg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_single_visitor);
        init();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            //String visitor_id,visitor_time,guard_name,visitor_name,visitor_phone_num,mem_block_num,
            // mem_flat_num,visitor_purpose,visitor_img,visitor_vechile_img;
            String visitor_id = extras.getString("visitor_id");
            String visitor_time = extras.getString("visitor_time");
            String guard_name = extras.getString("guard_name");
            String visitor_name = extras.getString("visitor_name");
            String visitor_phone_num = extras.getString("visitor_phone_num");
            String mem_block_num = extras.getString("mem_block_num");
            String mem_flat_num = extras.getString("mem_flat_num");
            String visitor_purpose = extras.getString("visitor_purpose");
            String visitor_img = extras.getString("visitor_img");
            String visitor_vechile_img = extras.getString("visitor_vechile_img");
            //Toast.makeText(getApplicationContext(),url1,Toast.LENGTH_SHORT).show();
            String url1= UrlsList.pdf_storage +visitor_img;
            String url2=UrlsList.pdf_storage+visitor_vechile_img;
            vivitorname.setText(visitor_name);
            visitormobno.setText(visitor_phone_num);
            guardname.setText(guard_name);
            memblockno.setText(mem_block_num);
            memflatno.setText(mem_flat_num);
            time.setText(visitor_time);
            vivitngpurpose.setText(visitor_purpose);
            Picasso.with(getApplicationContext())
                    .load(url1)
                    .placeholder(R.mipmap.ic_launcher)
                    .fit()
                    .into(viistorimg);

            if (!visitor_vechile_img.isEmpty() && !visitor_vechile_img.equals("")){
                vvimg.setVisibility(View.VISIBLE);
                Picasso.with(getApplicationContext())
                        .load(url2)
                        .placeholder(R.mipmap.ic_launcher)
                        .fit()
                        .into(vivitorvehicalimg);
            }

        }
    }

    private void init() {
        vivitorname=findViewById(R.id.visitorname);
        visitormobno=findViewById(R.id.visitorphnno);
        guardname=findViewById(R.id.guardname);
        memblockno=findViewById(R.id.memblockno);
        memflatno=findViewById(R.id.memflatno);
        time=findViewById(R.id.time);
        vivitngpurpose=findViewById(R.id.vivitingpurpose);
        viistorimg=findViewById(R.id.vivitorimg);
        vivitorvehicalimg=findViewById(R.id.visitorsvehicalimg);
        vvimg=findViewById(R.id.vvimg);

    }
}
