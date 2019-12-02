package com.example.flattemp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.flattemp.Model.Config;
import com.example.flattemp.Model.UrlsList;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Profile extends AppCompatActivity {
    //String URL_member="http://pivotnet.co.in/SocietyManagement/Android/fetchmemberdata.php";
    String semail;
    String name,flatno,flattype,mobno,imgurl;
    TextView tname,tflatno,tflattype,tmobno;
    ImageView profileimg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        semail = sharedPreferences.getString(Config.EMAIL_SHARED_PREF,"Not Available");

        tname=findViewById(R.id.username);
        tflatno=findViewById(R.id.flatno);
        tflattype=findViewById(R.id.flattype);
        tmobno=findViewById(R.id.mobno);
        profileimg=findViewById(R.id.profileimg);
        load();




    }

    @Override
    protected void onResume() {
        super.onResume();
        load();
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Adding our menu to toolbar
        getMenuInflater().inflate(R.menu.profile_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.edit) {
            //calling logout method when the logout button is clicked
            startActivity(new Intent(Profile.this,EditProfile.class));
        }
        return super.onOptionsItemSelected(item);
    }
*/

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
                            imgurl=user.getString("mem_img");
                            name=user.getString("mem_name");
                            flatno=user.getString("mem_flat_num");
                            flattype =user.getString("mem_flat_type");
                            mobno=user.getString("mem_phone_num");
                          //  Toast.makeText(getApplicationContext(),name+imgurl,Toast.LENGTH_SHORT).show();
                            tname.setText(name);
                            tflatno.setText(flatno);
                            tflattype.setText(flattype);
                            tmobno.setText(mobno);
                            Picasso.with(getApplicationContext())
                                    .load("http://majestic-overseas.com/society/society/admin/"+imgurl)
                                    .placeholder(R.mipmap.ic_launcher)
                                    .fit()
                                    .into(profileimg);

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
