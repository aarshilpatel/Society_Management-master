package com.example.flattemp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.flattemp.Adaptor.NoticeAdapter;
import com.example.flattemp.Adaptor.Polls_Adapter;
import com.example.flattemp.Adaptor.ReciptAdapter;
import com.example.flattemp.Model.Config;
import com.example.flattemp.Model.Notice_Model;
import com.example.flattemp.Model.Polls_Model;
import com.example.flattemp.Model.Recipt;
import com.example.flattemp.Model.UrlsList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class  Polls extends AppCompatActivity {


Button pre,next;
    ViewPager mViewPager;
    private int currentPage = 0;
    String question,ans1,ans2,ans3,ans4;
    List<Polls_Model> eventlist;
    String mem_id;
    Polls_Adapter adapter;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_polls);

        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        mem_id = sharedPreferences.getString(Config.MEMBER_ID_SHARED_PREF,"Not Available");

        eventlist=new ArrayList<>();

        pre=findViewById(R.id.pre);
        next=findViewById(R.id.next);

        getdata();

        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mViewPager.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                return true;
            }
        });


        next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                pre.setVisibility(View.VISIBLE);
                if(currentPage==mViewPager.getAdapter().getCount()-1){

                    next.setVisibility(View.GONE);
                }
                currentPage++;
              //  que++;
              //  available_q.setText(String.valueOf(que));

                mViewPager.setCurrentItem(getItem(+1));

              /*  currentPage++;
                que++;
                available_q.setText(String.valueOf(que));
                mViewPager.setCurrentItem(getItem(+1)); //getItem(-1) for previous*/
            }
        });

        pre.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                next.setVisibility(View.VISIBLE);
                if(currentPage==1){

                    pre.setVisibility(View.GONE);
                }
                currentPage--;
           //     que--;
             //   available_q.setText(String.valueOf(que));

                mViewPager.setCurrentItem(getItem(-1));

                /*currentPage--;
                que--;
                available_q.setText(String.valueOf(que));
                mViewPager.setCurrentItem(getItem(-1)); //getItem(-1) for previous*/
            }
        });

    }

    private int getItem(int i) {



        return mViewPager.getCurrentItem() + i;
    }

    private void getdata() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlsList.fetch_poll_question,
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


                                eventlist.add(0, new Polls_Model(
                                        user.getString("id"),
                                        user.getString("subject"),
                                        user.getString("created"),
                                        user.getString("modified"),
                                        user.getString("status"),
                                        user.getString("ans1"),
                                        user.getString("ans2"),
                                        user.getString("ans3"),
                                        user.getString("ans4")


                                ));


                            }

                            //creating adapter object and setting it to recyclerview
                            adapter = new Polls_Adapter(getApplicationContext(), eventlist);
                            mViewPager.setAdapter(adapter);
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
}
