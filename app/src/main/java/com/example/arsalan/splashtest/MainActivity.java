package com.example.arsalan.splashtest;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.server.converter.StringToIntConverter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import android.support.v4.app.FragmentActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements OnConnectionFailedListener{
    private GoogleApiClient mGoogleApiClient;
    private Button get_place;
    int PLACE_PICKER_REQUEST=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();
        get_place = (Button) findViewById(R.id.butt);
        get_place.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

                Intent intent;
                try {
                    try {
                        intent = builder.build(MainActivity.this);
                        startActivityForResult(intent, PLACE_PICKER_REQUEST);
                    } catch (GooglePlayServicesRepairableException e) {
                        e.printStackTrace();
                    }
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }


            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode==PLACE_PICKER_REQUEST)
        {
            if (resultCode==RESULT_OK){
                Place place = PlacePicker.getPlace(this, data);
                CharSequence address = place.getAddress();
                CharSequence name = place.getName();
                Float rating = place.getRating();
                CharSequence phone = place.getPhoneNumber();
                Uri website = place.getWebsiteUri();
                int price = place.getPriceLevel();
                Locale locale = place.getLocale();
                String id = place.getId();
                Intent intent = new Intent(MainActivity.this,Restdetails.class);
                intent.putExtra("Name:",name);
                intent.putExtra("Address:",address);
                intent.putExtra("Phn no",phone);
                intent.putExtra("Rating",rating);
                if(website!=null)
                {
                    intent.putExtra("Website:",website.toString());
                }
                intent.putExtra("Price:",price);
                intent.putExtra("Locale:",locale);
                intent.putExtra("Id:",id);

                startActivity(intent);
            }
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}

