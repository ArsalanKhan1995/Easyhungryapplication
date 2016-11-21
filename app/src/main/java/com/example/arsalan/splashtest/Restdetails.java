package com.example.arsalan.splashtest;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.PlacePhotoMetadataBuffer;
import com.google.android.gms.location.places.PlacePhotoMetadataResult;
import com.google.android.gms.location.places.PlacePhotoResult;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;

import customfonts.MyTextView;

import static com.example.arsalan.splashtest.R.id.pricelevel;
import static com.example.arsalan.splashtest.R.id.rating;

public class Restdetails extends MainActivity {

    ImageView plus,minus;
    TextView people;
    int counter = 0;
    RatingBar ratingbar;
    MyTextView mtv1,mtv2,mtv3,mtv4,mtv5,mtv6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restdetails);
        plus = (ImageView)findViewById(R.id.plus);
        minus = (ImageView)findViewById(R.id.minus);
        people = (TextView) findViewById(R.id.sizeno);
        ratingbar = (RatingBar) findViewById(R.id.ratingbar);
        mtv1 = (MyTextView) findViewById(R.id.restname);
        mtv2 = (MyTextView) findViewById(R.id.restadd);
        mtv3 = (MyTextView) findViewById(R.id.number);
        mtv4 = (MyTextView) findViewById(R.id.web);
        mtv5 = (MyTextView) findViewById(R.id.pricelevel);
        mtv6 = (MyTextView) findViewById(R.id.locale);


        final int[] number = {0};
        people.setText(""+ number[0]);

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (number[0] == 0){
                    people.setText("" + number[0]);
                }

                if (number[0] > 0){

                    number[0] = number[0] -1;
                    people.setText(""+ number[0]);
                }
            }
        });
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (number[0] == 9) {
                    people.setText("" + number[0]);
                }

                if (number[0] < 9) {

                    number[0] = number[0] + 1;
                    people.setText("" + number[0]);

                }
            }
        });


        Intent intent = getIntent();
        String name = intent.getStringExtra("Name:");
        String add = intent.getStringExtra("Address:");
        CharSequence phn = intent.getCharSequenceExtra("Phn no");
        String web = intent.getStringExtra("Website:");
        /*Uri uri = Uri.parse(web);*/
        Float rat = intent.getFloatExtra("Rating",rating);
        Integer pr = intent.getIntExtra("Price:",2);
        String loc = intent.getStringExtra("Locale:");
        mtv1.setText(name);
        mtv2.setText(add);
        mtv5.setText(getResources().getString(R.string.pricelevel)+String.valueOf(pr));
        mtv6.setText(getResources().getString(R.string.locale)+String.valueOf(loc));
        if (rat!=null)
        {

            ratingbar.setRating(rat);
        }
        else {
            ratingbar.setRating(0);
        }
        if (phn==null)
        {
            mtv3.setText(getResources().getString(R.string.phnno));
        }
        else
        {
            mtv3.setText(phn);
        }
        if (web!=null)
        {
            mtv4.setText(web);
        }
        else
        {
            mtv4.setText(getResources().getString(R.string.web));
        }
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Restdetails.this,MainActivity.class);
                intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent2);
            }
        });


    }
}
