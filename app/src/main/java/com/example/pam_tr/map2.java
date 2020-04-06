package com.example.pam_tr;

import androidx.fragment.app.FragmentActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class map2 extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map2);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapz);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapClick(LatLng latLng) {
        mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        MarkerOptions markerOptions = new MarkerOptions().position(latLng).title(latLng.toString());
        mMap.addMarker(markerOptions);

        getCompleteAddressString(latLng.latitude, latLng.longitude);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng salatiga = new LatLng(-7.3305, 110.5084);
        mMap.addMarker(new MarkerOptions().position(salatiga).title("Marker in Salatiga"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(salatiga, 15.0f));

        mMap.setOnMapLongClickListener(this);
        mMap.setOnMapClickListener(this);
    }

    private String getCompleteAddressString(double LAT, double LONG){
        String strAdd = "";
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        try {
            List<Address> addresses = geocoder.getFromLocation(LAT, LONG, 1);

            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder();

                for (int i = 0; i < returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                }

                strAdd = strReturnedAddress.toString();
                Log.w("CLA", strReturnedAddress.toString());
            } else {
                Log.w("CLA", "No address returned!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.w("CLA", "Cannot get the address!");
        }

        return strAdd;
    }
}
