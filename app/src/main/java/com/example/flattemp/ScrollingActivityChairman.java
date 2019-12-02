package com.example.flattemp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.flattemp.Adaptor.VisitorAdaptor;
import com.example.flattemp.Model.UrlsList;
import com.example.flattemp.Model.Visitormodel;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ScrollingActivityChairman extends AppCompatActivity {
  //  String URL_PRODUCTS="http://pivotnet.co.in/SocietyManagement/Android/fetchchairmanmsg.php";
    String msg,date;
    Toolbar toolbar;
    TextView txt;
    ImageView imgcol;
    CollapsingToolbarLayout collapsingToolbarLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling_chairman);
        loadUsers();
        imgcol=findViewById(R.id.imgviewcol);
        txt=findViewById(R.id.storytxt);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        collapsingToolbarLayout=findViewById(R.id.toolbar_layout);


        // toolbar.setTitle("djvsj");



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    private void loadUsers() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, UrlsList.fetch_chairmans_message,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {


                            JSONArray array = new JSONArray(response);
                            JSONObject user = array.getJSONObject(0);
                            msg=user.getString("update_chairman_message");
                            date=user.getString("update_chairman_message_date");
                            String name=user.getString("update_chairman_message_name");
                            String img="http://majestic-overseas.com/society/society/admin/"+user.getString("update_chairman_message_img");
                            txt.setText(msg);
                            collapsingToolbarLayout.setTitle(name);

                            Picasso.with(getApplicationContext())
                                    .load(img)
                                    .placeholder(R.drawable.ic_launcher_background)
                                    .fit()
                                    .centerCrop()
                                    .into(imgcol);



                        }
                        catch (JSONException e) {
                            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );

        //adding our stringrequest to queue
        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);
    }
}
