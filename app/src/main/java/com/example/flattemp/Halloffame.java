package com.example.flattemp;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.flattemp.Adaptor.EventAdapter1;
import com.example.flattemp.Adaptor.Halloffame_Adapter;
import com.example.flattemp.Model.Config;
import com.example.flattemp.Model.Event;
import com.example.flattemp.Model.Halloffame_model;
import com.example.flattemp.Model.UrlsList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Halloffame extends AppCompatActivity {
    List<Halloffame_model> eventlist;
    RecyclerView eventrecycler;
    SwipeRefreshLayout pullToRefresh;
    String  mem_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halloffame);

        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        mem_id = sharedPreferences.getString(Config.MEMBER_ID_SHARED_PREF,"Not Available");

        pullToRefresh = (SwipeRefreshLayout) findViewById(R.id.pullToRefresh);
        eventrecycler = findViewById(R.id.eventrecycle);
        eventrecycler.setHasFixedSize(true);
        eventrecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        eventlist = new ArrayList<>();


        //this method will fetch and parse json
        //to display it in recyclerview
        loadUsers();

        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                eventlist.clear();
                loadUsers();

            }
        });

    }




    private void loadUsers() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlsList.fetch_hall_of_fame,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.e("Res",">>>>>"+response);
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);
                            // Toast.makeText(getActivity(),"entered"+array.length(),Toast.LENGTH_SHORT).show();
                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject user = array.getJSONObject(i);

                                //adding the product to product list
                                //String visitor_id,visitor_name,visitor_phone_num,visitor_vechile_num,
                                // mem_name,mem_flat_num,mem_phone_num,in_time_date,visitor_img;
                                eventlist.add(0, new Halloffame_model(
                                        user.getString("fame_id"),
                                        user.getString("fame_event_name"),
                                        user.getString("fame_winner_name"),
                                        user.getString("fame_winner_img"),
                                        user.getString("fame_winner_pos"),
                                        user.getString("fame_about_event")


                                ));

                            }
                            if (pullToRefresh.isRefreshing()) {
                            }
                            pullToRefresh.setRefreshing(false);

                            //creating adapter object and setting it to recyclerview
                            Halloffame_Adapter adapter1 = new Halloffame_Adapter(getApplicationContext(), eventlist);
                            eventrecycler.setAdapter(adapter1);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Halloffame.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }
        ){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                //Adding parameters to request
                params.put("mem_user_id", mem_id);

                //returning parameter
                return params;
            }
        };

        //adding our stringrequest to queue
        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);
    }
}