package com.example.flattemp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.flattemp.Adaptor.Staff_Adapter;
import com.example.flattemp.Model.Config;
import com.example.flattemp.Model.Staff_Model;
import com.example.flattemp.Model.UrlsList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Complain extends AppCompatActivity  implements
        AdapterView.OnItemSelectedListener {
    RelativeLayout layout;
    String[] country = { "Select Issue",  "Lift Issue", "Plumbing", "Water", "Other"};
    Button submit;
    EditText details;
    String mem_id,issue,desc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complain);
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        mem_id = sharedPreferences.getString(Config.MEMBER_ID_SHARED_PREF,"Not Available");


        details=findViewById(R.id.complain_details);
        submit=findViewById(R.id.submit_complain);



        Spinner spin = (Spinner) findViewById(R.id.spinner_complain);

        spin.setOnItemSelectedListener(this);

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,country);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                desc=details.getText().toString();
                if (issue.equals("Select Issue")){
                    Toast.makeText(Complain.this, "Please Select Issue", Toast.LENGTH_SHORT).show();
                }else if(desc.isEmpty()) {
                    Toast.makeText(Complain.this, "Please Enter Details", Toast.LENGTH_SHORT).show();
                }
                else {
                        submit_Data();
                    }
                }

        });



    }

    private void submit_Data() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlsList.insert_complain,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                     if (response.trim().equals("success")){
                         Toast.makeText(Complain.this, "Complain Submited Successfull", Toast.LENGTH_SHORT).show();
                         Intent intent=new Intent(Complain.this,Viewcomplain.class);
                         startActivity(intent);
                         finish();
                     }else {
                         Toast.makeText(Complain.this, "Some Problem Try again", Toast.LENGTH_SHORT).show();
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
                params.put("mem_complaint",issue);
                params.put("mem_complaint_desc",desc);
                //returning parameter
                return params;
            }
        };

        //adding our stringrequest to queue
        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        issue=country[position];
        //Toast.makeText(getApplicationContext(),country[position] , Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
