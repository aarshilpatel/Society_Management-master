package com.example.flattemp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class Shoesingleimage extends AppCompatActivity {
    ImageView imgview;

    private ScaleGestureDetector mScaleGestureDetector;
    private float mScaleFactor = 1.0f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoesingleimage);
        imgview=findViewById(R.id.imgview);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String img = intent.getStringExtra("img");
        //Toast.makeText(this, "Mannu" + name+img, Toast.LENGTH_SHORT).show();
        // getActionBar().setTitle("abc");
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setTitle(name);


        Picasso.with(getApplicationContext())
                .load(img)
                .placeholder(R.drawable.img_r)
                .fit()
                .into(imgview);
        mScaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        mScaleGestureDetector.onTouchEvent(motionEvent);
        return true;
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector scaleGestureDetector){
            mScaleFactor *= scaleGestureDetector.getScaleFactor();
            mScaleFactor = Math.max(0.1f,
                    Math.min(mScaleFactor, 5.0f));
            imgview.setScaleX(mScaleFactor);
            imgview.setScaleY(mScaleFactor);
            return true;
        }
    }
}

