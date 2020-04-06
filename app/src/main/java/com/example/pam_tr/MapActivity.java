package com.example.pam_tr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener {

    private GoogleMap mMap;
    private String id;
    DatabaseReference reff;
    double lat, lon;
    String name2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Intent in = getIntent();
        id = in.getStringExtra("index");
        name2 = in.getStringExtra("nama3");
        lat = (Double) Double.parseDouble(in.getStringExtra("lat"));
        lon = (Double) Double.parseDouble(in.getStringExtra("lon"));
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapl);
        mapFragment.getMapAsync(this);

        load0(id);
    }

    public void load0(String ab) {
        reff = FirebaseDatabase.getInstance().getReference().child("data").child(ab);
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                TextView a = (TextView) findViewById(R.id.title0);
                TextView b = (TextView) findViewById(R.id.ds0);
                TextView c = (TextView) findViewById(R.id.dsp0);
                ImageView im = (ImageView) findViewById(R.id.im0);
//
//
//                lat = (Double) Double.parseDouble(dataSnapshot.child("latitude").getValue().toString());
//                lon = (Double) Double.parseDouble(dataSnapshot.child("longitude").getValue().toString());

                String name = (String) dataSnapshot.child("name").getValue(String.class);
                String img = dataSnapshot.child("img").getValue().toString();
                String mini = dataSnapshot.child("desc_sm").getValue().toString();
                String pjg = dataSnapshot.child("desc_lg").getValue().toString();


                a.setText(name);
                b.setText(mini);
                c.setText(pjg);
                Picasso.get().load(img).into(im);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {


        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng lok = new LatLng(lat, lon);
        mMap.addMarker(new MarkerOptions().position(lok).title(name2));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(lok, 15.0f));

        mMap.setOnMapLongClickListener(this);
        mMap.setOnMapClickListener(this);
    }

    private String getCompleteAddressString(double LATITUDE, double LONGITUDE) {
        String strAdd = "";
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder();

                for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                }
                strAdd = strReturnedAddress.toString();
                Log.w("Current location adress", strReturnedAddress.toString());
            } else {
                Log.w("Current location adress", "No Address returned!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.w("Current loction address", "Canont get Address!");
        }
        return strAdd;
    }

    @Override
    public void onMapClick(LatLng latLng) {

        // animate camera to centre on touched position
        mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

    }

    @Override
    public void onMapLongClick(LatLng latLng) {

        //Add marker on LongClick position
        MarkerOptions markerOptions =
                new MarkerOptions().position(latLng).title(latLng.toString());
        mMap.addMarker(markerOptions);

        getCompleteAddressString(latLng.latitude, latLng.longitude);
    }
}
