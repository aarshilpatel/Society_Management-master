package com.example.flattemp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
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
import com.example.flattemp.Model.GalleryCatagory;
import com.example.flattemp.Model.UrlsList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{
    private TextView textView,textview2;
    CardView las,aandp,fandb,official,kys,bb,help;
    private boolean loggedIn = false;
    String semail;
    private View navHeader;
    TextView society_name,society_address;
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

        //Fetching the boolean value form sharedpreferences
        loggedIn = sharedPreferences.getBoolean(Config.LOGGEDIN_SHARED_PREF, false);

        //If we will get true
        if(!loggedIn){
            //We will start the Profile Activity
            Intent intent = new Intent(this,Login.class);
            finishAffinity();
            startActivity(intent);
        }


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       /* getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE);*/
        //Initializing textview
        android.support.v7.widget.Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        textView = (TextView) findViewById(R.id.textView);
        textview2=findViewById(R.id.textView2);

        navHeader = navigationView.getHeaderView(0);
        //txtName = (TextView) navHeader.findViewById(R.id.name);
        society_name=navHeader.findViewById(R.id.society_name);
        society_address=navHeader.findViewById(R.id.society_address);
        //Fetching email from shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
         semail = sharedPreferences.getString(Config.EMAIL_SHARED_PREF,"Not Available");
          load();
        //Showing the current logged in email to textview


        las=findViewById(R.id.las);
        aandp=findViewById(R.id.aandp);
        fandb=findViewById(R.id.fandb);
        official=findViewById(R.id.official);
        kys=findViewById(R.id.knowyoursociety);
        bb=findViewById(R.id.businessbzaer);
        help=findViewById(R.id.help);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:+911234567890"));
                startActivity(intent);
            }
        });
        clickfunction();

    }
    private void clickfunction() {
        las.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Lifeatsociety.class));
            }
        });
        aandp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AccountOptopns.class));

            }
        });
        fandb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),FacilityBooking.class));

            }
        });
        official.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),Official.class));
            }
        });
        kys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),knowyoursocietyopt.class));
            }
        });
        bb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(),"Comming soon",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),Businessbuzzar.class));

            }
        });

    }
    //Logout function
    private void logout(){
        //Creating an alert dialog to confirm logout
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to logout?");
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        //Getting out sharedpreferences
                        SharedPreferences preferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                        //Getting editor
                        SharedPreferences.Editor editor = preferences.edit();

                        //Puting the value false for loggedin
                        editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, false);

                        //Putting blank value to email
                        editor.putString(Config.EMAIL_SHARED_PREF, "");
                        editor.clear();

                        //Saving the sharedpreferences
                        editor.commit();

                        //Starting login activity
                        Intent intent = new Intent(MainActivity.this, Login.class);
                        startActivity(intent);
                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        //Showing the alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Adding our menu to toolbar
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuLogout) {
            //calling logout method when the logout button is clicked
            logout();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item){
        // Handle navigation view item clicks here.
        Intent i;
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            i=new Intent(this,MainActivity.class);startActivity(i);

        } else if (id == R.id.nav_event) {
            i=new Intent(this,UpcomingEvents.class);startActivity(i);
        }
        else if (id == R.id.nav_photo) {
            i=new Intent(this, GalleryCatagoryActivity.class);startActivity(i);
        }
        else if (id == R.id.nav_booking) {
            i=new Intent(this,BookingActivity.class);startActivity(i);
        }
        else if (id == R.id.nav_booking_history) {
            i=new Intent(this,BookingHistory.class);startActivity(i);
        }
        else if (id == R.id.nav_knowyoursociety) {
            i=new Intent(this,knowyoursocietyopt.class);startActivity(i);

        }
        else if (id == R.id.nav_notice) {
            i=new Intent(this,Notice.class);startActivity(i);

        }
        else if (id == R.id.nav_feedback) {
            Toast.makeText(getApplicationContext(),"sfhvbs",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto","abc@gmail.com", null));
            intent.putExtra(Intent.EXTRA_SUBJECT, "ABOUT Society");
            intent.putExtra(Intent.EXTRA_TEXT, "NEED SOME INFORMATION OF THE SOCIETY.");
            startActivity(Intent.createChooser(intent, "Choose an Email client :"));
        }else if (id == R.id.nav_COMPLAINT) {
            Intent intent = new Intent(MainActivity.this,Complain.class);
            startActivity(intent);

        }else if (id == R.id.nav_chairman) {
            Intent intent = new Intent(MainActivity.this,ScrollingActivityChairman.class);
            startActivity(intent);

        }else if (id == R.id.nav_profile) {
            Intent intent = new Intent(MainActivity.this,Profile.class);
            startActivity(intent);

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else  if (loggedIn){
            finish();
            System.exit(0);
        }
        else  if (!loggedIn){
            finish();
            System.exit(0);
        }else
        {
            super.onBackPressed();
        }
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
                             textview2.setText(user.getString("mem_flat_num"));

                            SharedPreferences sharedPreferences = MainActivity.this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                            //Creating editor to store values to shared preferences
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            //Adding values to editor

                            editor.putString(Config.MEMBER_ID_SHARED_PREF, user.getString("mem_id"));

                            load_society_data(user.getString("mem_id"));

                            editor.putString(Config.MEMBER_NAME_SHARED_PREF, user.getString("mem_name"));

                            textView.setText(user.getString("mem_name"));
                            editor.putString(Config.MEMBER_FLAT_SHARED_PREF, user.getString("mem_flat_num"));
                            //Saving values to editor
                            editor.commit();
                        }
                        catch (JSONException e) {
                           // Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                       // Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
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
    private void load_society_data(final String mem_id) {
        //String URL_member="http://pivotnet.co.in/SocietyManagement/Android/fetchmemberdata.php";
        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlsList.fetch_society,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            JSONObject user = array.getJSONObject(0);
                            // JSONObject user=new JSONObject(response);

                            society_name.setText(user.getString("name"));
                            society_address.setText(user.getString("address "));
                        }
                        catch (JSONException e) {
                            // Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
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
                params.put("mem_user_id",mem_id);
                //returning parameter
                return params;
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}

