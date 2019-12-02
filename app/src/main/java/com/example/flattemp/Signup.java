package com.example.flattemp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.flattemp.Model.Config;

import java.util.HashMap;
import java.util.Map;

public class Signup extends AppCompatActivity {
    ProgressDialog progressDialog;
    EditText name,pass,flatno,mobno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        progressDialog=new ProgressDialog(Signup.this);
        progressDialog.setMessage("Please Wait ....");
        name=findViewById(R.id.name);
        pass=findViewById(R.id.pass);
        flatno=findViewById(R.id.flatno);
        mobno=findViewById(R.id.mobileno);
        Button signup=findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = name.getText().toString().trim();
                final String password = pass.getText().toString().trim();
                final String flat = flatno.getText().toString().trim();
                final String mob = mobno.getText().toString().trim();
                if (email.isEmpty() || password.isEmpty() || flat.isEmpty() || mob.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Enter All data",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!TextUtils.isEmpty(email) && !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    Toast.makeText(getApplicationContext(),"Email formate mismatch",Toast.LENGTH_SHORT).show();
                    return;
                }
                register(email,password,flat,mob);
            }
        });
    }

    private void register(final String email,final String password,final String flat,final String mob) {
        //Getting values from edit texts
        progressDialog.show();



        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.Signup_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        //If we are getting success from server
                        if(response.equalsIgnoreCase(Config.LOGIN_SUCCESS)){
                            Toast.makeText(getApplicationContext(),"Sucessfully registered",Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            //Starting profile activity
                            Intent intent = new Intent(Signup.this, Login.class);
                            startActivity(intent);
                        }else{
                            //If the server response is not success
                            //Displaying an error message on toast
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Fail to signup", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        //You can handle error here if you want
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                //Adding parameters to request
                params.put("email", email);
                params.put("pass", password);
                params.put("flatno",flat);
                params.put("mob", mob);
                //returning parameter
                return params;
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
