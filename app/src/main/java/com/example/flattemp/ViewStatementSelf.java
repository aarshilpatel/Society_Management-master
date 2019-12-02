package com.example.flattemp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.flattemp.Adaptor.ReciptAdapter;
import com.example.flattemp.Adaptor.SelfstatementAdapter;
import com.example.flattemp.Model.Config;
import com.example.flattemp.Model.Recipt;
import com.example.flattemp.Model.UrlsList;
import com.example.flattemp.Model.View_Statement_Self_Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewStatementSelf extends AppCompatActivity {
    //String URL_member="http://pivotnet.co.in/SocietyManagement/Android/fetchmemberdata.php";

  // private static final String URL_PRODUCTS = "http://pivotnet.co.in/SocietyManagement/Android/fetch_viewstatement_self.php";
    List<View_Statement_Self_Model> reciptlist;
    RecyclerView eventrecycler;
    ReciptAdapter reciptAdapter;
    SwipeRefreshLayout pullToRefresh;
    String semail,mem_id;
    String id1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_statement_self);
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        semail = sharedPreferences.getString(Config.EMAIL_SHARED_PREF,"Not Available");
       // load();
       // SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        mem_id = sharedPreferences.getString(Config.MEMBER_ID_SHARED_PREF,"Not Available");
        loadUsers();
        pullToRefresh = (SwipeRefreshLayout) findViewById(R.id.pullToRefresh);

        eventrecycler = findViewById(R.id.eventrecycle);
        eventrecycler.setHasFixedSize(true);
        eventrecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        reciptlist = new ArrayList<>();


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

        StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlsList.fetch_view_statement_self,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("response",">>>>>"+response);
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);
                            // Toast.makeText(getActivity(),"entered"+array.length(),Toast.LENGTH_SHORT).show();
                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject user = array.getJSONObject(i);

                                //adding the product to product list
                                // String pay_id,pay_date,mem_id,mem_name, mem_flat_num, mem_flat_type, pay_fixed,
                                // pay_deposit,pay_remaining, pay_month,pay_status;
                                reciptlist.add( 0,new View_Statement_Self_Model(
                                        user.getString("pay_id"),
                                        user.getString("pay_date"),
                                        user.getString("mem_id"),
                                        user.getString("mem_name"),
                                        user.getString("mem_flat_num"),
                                        user.getString("mem_flat_type"),
                                        user.getString("pay_fixed"),
                                        user.getString("pay_deposit"),
                                        user.getString("pay_remaining"),
                                        user.getString("pay_month"),
                                        user.getString("pay_status")
                              ));
                            }
                            if (pullToRefresh.isRefreshing()) {
                            }
                            pullToRefresh.setRefreshing(false);

                            //creating adapter object and setting it to recyclerview
                            SelfstatementAdapter adapter1 = new SelfstatementAdapter(getApplicationContext(), reciptlist);
                            eventrecycler.setAdapter(adapter1);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();
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
                params.put("cat",mem_id);
                //returning parameter
                return params;
            }
        };

        //adding our stringrequest to queue
        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);
    }

    private void load() {

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