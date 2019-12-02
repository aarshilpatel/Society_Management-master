package com.example.flattemp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.flattemp.Adaptor.BookinghistoryAdaptor;
import com.example.flattemp.Adaptor.ImageAdapter;
import com.example.flattemp.Adaptor.ReciptAdapter;
import com.example.flattemp.Model.Booking;
import com.example.flattemp.Model.Config;
import com.example.flattemp.Model.Gallery;
import com.example.flattemp.Model.Recipt;
import com.example.flattemp.Model.UrlsList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingHistory extends AppCompatActivity {
    //private static final String URL_PRODUCTS = "http://pivotnet.co.in/SocietyManagement/Android/fetch_bookingdata.php";
    List<Booking> reciptlist;
    RecyclerView eventrecycler;
    BookinghistoryAdaptor reciptAdapter;
    SwipeRefreshLayout pullToRefresh;
    String id1,semail,mem_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_history);
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        mem_id = sharedPreferences.getString(Config.MEMBER_ID_SHARED_PREF,"Not Available");

        pullToRefresh = (SwipeRefreshLayout) findViewById(R.id.pullToRefresh);

        eventrecycler = findViewById(R.id.eventrecycle);
        eventrecycler.setHasFixedSize(true);
        eventrecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        reciptlist = new ArrayList<>();
      //  load();
        loadUsers();


        //this method will fetch and parse json
        //to display it in recyclerview


        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                reciptlist.clear();
                loadUsers();

            }
        });

    }

    private void loadUsers() {
        reciptlist.clear();
        //Toast.makeText(getApplicationContext(),cat,Toast.LENGTH_SHORT).show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlsList.fetch_booking_data_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("res","<<<<<<"+response);

                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);
                            // Toast.makeText(getActivity(),"entered"+array.length(),Toast.LENGTH_SHORT).show();
                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject user = array.getJSONObject(i);
// String booking_id,facility,mem_id, mem_name, mem_phone_num,booked_date, booking_reason,booked_status;
                                reciptlist.add( 0,new Booking(
                                        user.getString("facility"),
                                        user.getString("booked_date"),
                                        user.getString("date_from"),
                                        user.getString("date_to"),
                                        user.getString("booking_reason"),
                                        user.getString("booked_status"),
                                        user.getString("booking_id")




                                ));


                            }
                            if (pullToRefresh.isRefreshing()) {
                                pullToRefresh.setRefreshing(false);
                            }


                            //creating adapter object and setting it to recyclerview
                            reciptAdapter = new BookinghistoryAdaptor(getApplicationContext(), reciptlist);
                            eventrecycler.setAdapter(reciptAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                //Adding parameters to request
                params.put("mem_user_id",mem_id);
                //returning parameter
                return params;
            }
        };

        //adding our stringrequest to queue
        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);
    }


    private void load() {
        //String URL_member="http://pivotnet.co.in/SocietyManagement/Android/fetchmemberdata.php";
        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlsList.fetch_members_details_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            JSONObject user = array.getJSONObject(0);
                            // JSONObject user=new JSONObject(response);
                            id1=user.getString("mem_id");
                            Toast.makeText(getApplicationContext(), id1, Toast.LENGTH_LONG).show();
                            loadUsers();
                        }
                        catch (JSONException e) {
                            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        //You can handle error here if you want
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                //Adding parameters to request
                params.put("email", semail);
                //returning parameter
                return params;
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}