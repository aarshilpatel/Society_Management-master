package com.example.flattemp;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.example.flattemp.Adaptor.ImageAdapter;
import com.example.flattemp.Adaptor.NoticeAdapter;
import com.example.flattemp.Model.Config;
import com.example.flattemp.Model.Gallery;
import com.example.flattemp.Model.Notice_Model;
import com.example.flattemp.Model.UrlsList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Notice extends AppCompatActivity implements  NoticeAdapter.OnItemClickListener{
    RecyclerView eventrecycler;
    List<Notice_Model> eventlist;
    SwipeRefreshLayout pullToRefresh;
    NoticeAdapter adapter;
    String mem_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        mem_id = sharedPreferences.getString(Config.MEMBER_ID_SHARED_PREF,"Not Available");


        pullToRefresh = (SwipeRefreshLayout) findViewById(R.id.pullToRefresh);
        eventrecycler = findViewById(R.id.eventrecycle);
        eventrecycler.setHasFixedSize(true);
        eventrecycler.setLayoutManager(new LinearLayoutManager(Notice.this));

        eventlist = new ArrayList<>();
        adapter=new NoticeAdapter(getApplicationContext(),eventlist);
        adapter.setOnItemClickListener(Notice.this);


        loadData();

        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                eventlist.clear();
                loadData();

            }
        });
    }


    private void loadData() {

            StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlsList.fetch_notice,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                //converting the string to json array object
                                JSONArray array = new JSONArray(response);
                                // Toast.makeText(getActivity(),"entered"+array.length(),Toast.LENGTH_SHORT).show();
                                //traversing through all the object
                                for (int i = 0; i < array.length(); i++) {

                                    //getting product object from json array
                                    JSONObject user = array.getJSONObject(i);


                                    eventlist.add(0, new Notice_Model(
                                            user.getString("notice_id"),
                                            user.getString("update_notice"),
                                            user.getString("update_date")
                                    ));


                                }
                                if (pullToRefresh.isRefreshing()) {
                                }
                                pullToRefresh.setRefreshing(false);

                                //creating adapter object and setting it to recyclerview
                                adapter = new NoticeAdapter(getApplicationContext(), eventlist);
                                eventrecycler.setAdapter(adapter);
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


    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onWhatEverClick(int position) {

    }

    @Override
    public void onDeleteClick(int position) {

    }
}
