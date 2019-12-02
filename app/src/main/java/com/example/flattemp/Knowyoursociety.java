package com.example.flattemp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.example.flattemp.Model.UrlsList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Knowyoursociety extends AppCompatActivity {
    WebView webView;
    String url="";
    String pdfurl="";
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knowyoursociety);


        Intent intent = getIntent();
        String cat = intent.getStringExtra("cat");


        webView = (WebView) findViewById(R.id.activity_main_webview);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);




        if (cat.equals("a")){
            //url="http://pivotnet.co.in/SocietyManagement/rules_regulations_android.php";
            load();

        }else
        if (cat.equals("b")){
            url= UrlsList.documents_web_url;
            webcall();
        }else
        if (cat.equals("c")){
            url=UrlsList.vendors_web_url;
            webcall();
        }else
        if (cat.equals("d")){
            url=UrlsList.staffs_web_url;
            webcall();
        }





    }

    private void webcall() {
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
//hide loading image
                findViewById(R.id.progressBar1).setVisibility(View.GONE);
//show webview
                findViewById(R.id.activity_main_webview).setVisibility(View.VISIBLE);
            }});
        webView.loadUrl(url);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (webView.canGoBack()) {
                        webView.goBack();
                    } else {
                        finish();
                    }
                    return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private void load() {

        String URL_member=UrlsList.rules_web_url;
        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_member,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            JSONObject user = array.getJSONObject(0);
                            // JSONObject user=new JSONObject(response);
                            pdfurl="http://majestic-overseas.com/society/society/"+user.getString("rules_file");
                          //  Toast.makeText(Knowyoursociety.this,user.getString("rules_file") , Toast.LENGTH_SHORT).show();

                            webView.setWebViewClient(new WebViewClient(){
                                @Override
                                public void onPageFinished(WebView view, String url) {

//hide loading image
                                    findViewById(R.id.progressBar1).setVisibility(View.GONE);
//show webview
                                    findViewById(R.id.activity_main_webview).setVisibility(View.VISIBLE);
                                }});
                            Intent i=new Intent();
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            i.setType(Intent.ACTION_VIEW);
                            i.setData(Uri.parse(pdfurl));
                            startActivity(i);

                            webView.loadUrl(pdfurl);

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
                });

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
