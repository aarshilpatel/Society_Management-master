package com.example.flattemp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class EditProfile extends AppCompatActivity {
    String userid="";
    String name1,flattype1,flatno1,mobno1,imgurl1;
   // String URL_member="http://pivotnet.co.in/SocietyManagement/Android/fetchmemberdata.php";
    String semail;
    String name,flatno,flattype,mobno,imgurl;
    EditText tflatno,tflattype,tmobno;
    TextView tname;
    TextView tid;
    ImageView profileimg;
    ImageButton choseimg;
    Button insert;
    private Bitmap bitmap;
    private static final int PICK_IMAGE_REQUEST=1;
    private Uri mImageUri;
    //private String UPLOAD_URL ="http://pivotnet.co.in/SocietyManagement/admin/upload_profile.php";
   // String           uploadpic="http://pivotnet.co.in/SocietyManagement/admin/uploadprofilepic.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        semail = sharedPreferences.getString(Config.EMAIL_SHARED_PREF,"Not Available");
        insert=findViewById(R.id.save);
        tid=findViewById(R.id.id);
        choseimg=findViewById(R.id.btn_select_photo);
        tname=findViewById(R.id.username);
        tflatno=findViewById(R.id.flatno);
        tflattype=findViewById(R.id.flattype);
        tmobno=findViewById(R.id.mobno);
        profileimg=findViewById(R.id.ivImage);
        load();

        choseimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name1=tname.getText().toString();
                flattype1=tflattype.getText().toString();
                flatno1=tflatno.getText().toString();
                mobno1=tmobno.getText().toString();
                if (name1.equals("") || flatno1.equals("") ||flattype1.equals("") || mobno1.equals("")){
                    Toast.makeText(getApplicationContext(),"Please Enter All feilds ",Toast.LENGTH_SHORT).show();
                }
                else{
                    uploadImage();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        load();
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
                            String id=user.getString("mem_id");
                            imgurl=user.getString("mem_img");
                            name=user.getString("mem_name");
                            flatno=user.getString("mem_flat_num");
                            flattype =user.getString("mem_flat_type");
                            mobno=user.getString("mem_phone_num");

                            tname.setText(name);
                            tflatno.setText(flatno);
                            tflattype.setText(flattype);
                            tmobno.setText(mobno);
                            tid.setText(id);

                            Picasso.with(getApplicationContext())
                                    .load("http://pivotnet.co.in/SocietyManagement/admin/"+imgurl)
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


    public void  openFileChooser()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData();
            //Picasso.with(this).load(mImageUri).into(img);
            // Toast.makeText(getApplicationContext(),"image"+mImageUri,Toast.LENGTH_SHORT).show();

            Uri filePath = data.getData();
            try {
                //Getting the Bitmap from Gallery
                profileimg.setVisibility(View.VISIBLE);
                bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), mImageUri);
                //Setting the Bitmap to ImageView
                profileimg.setImageBitmap(bitmap);
                uploadImage1(tid.getText().toString());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }
    private void uploadImage(){
        //Showing the progress dialog
        final ProgressDialog loading = ProgressDialog.show(EditProfile.this,"Uploading...","Please wait...",false,false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlsList.update_profile_data_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //Disimissing the progress dialog
                        loading.dismiss();
                        //Showing toast message of the response
                        Toast.makeText(getApplicationContext(),s , Toast.LENGTH_LONG).show();
                        /*SharedPreferences sharedPreferences = EditProfile.this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                        //Creating editor to store values to shared preferences
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        //Adding values to editor
                        editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, true);
                        editor.putString(Config.EMAIL_SHARED_PREF, name1);
                        //Showing toast
                        ;*/
                        startActivity(new Intent(EditProfile.this,Profile.class));

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Dismissing the progress dialog
                        loading.dismiss();

                        Toast.makeText(getApplicationContext(), volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Converting Bitmap to String
                // String bitmapimageurl = getStringImage(bitmap);

                //Getting Image Name
                String id=tid.getText().toString();
                name1=tname.getText().toString();
                flattype1=tflattype.getText().toString();
                flatno1=tflatno.getText().toString();
                mobno1=tmobno.getText().toString();
                //Creating parameters
                Map<String,String> params = new Hashtable<String, String>();

                //Adding parameters
                params.put("name", name1);
                params.put("flattype",flattype1);
                params.put("flatno",flatno1);
                params.put("mobno",mobno1);
                params.put("id",id);

                //returning parameters
                return params;
            }
        };

        //Creating a Request Queue
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }

    private void uploadImage1(final String id){
        //Showing the progress dialog
        final ProgressDialog loading = ProgressDialog.show(EditProfile.this,"Uploading...","Please wait...",false,false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlsList.update_profilepic_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //Disimissing the progress dialog
                        loading.dismiss();
                        load();
                        //Showing toast message of the response
                        Toast.makeText(getApplicationContext(),"Susessfully changed your profile pic", Toast.LENGTH_LONG).show();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Dismissing the progress dialog
                        loading.dismiss();

                        //Showing toast
                        Toast.makeText(getApplicationContext(), volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Converting Bitmap to String
                String bitmapimageurl = getStringImage(bitmap);
                //String name1=tname.getText().toString();
                //Creating parameters
                Map<String,String> params = new Hashtable<String, String>();

                //Adding parameters
                params.put("icon", bitmapimageurl);
                params.put("name",id);

                //returning parameters
                return params;
            }
        };

        //Creating a Request Queue
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }

}
