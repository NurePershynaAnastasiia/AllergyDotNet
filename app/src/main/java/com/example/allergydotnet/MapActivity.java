package com.example.allergydotnet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.Marker;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;

import java.util.HashMap;
import java.io.IOException;
import java.util.List;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, LocationListener {

    private GoogleMap mMap;

    private Button addDraggableMarker;
    private Button setPosition;
    private Button cancel;
    private Marker draggableMarker = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        addDraggableMarker = (Button)findViewById(R.id.addbtn);
        setPosition = (Button)findViewById(R.id.choose_marker_position);
        cancel = (Button)findViewById(R.id.cancel_position);
    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {
            }

            @Override
            public void onMarkerDrag(Marker marker) {
            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                LatLng pos = marker.getPosition();
                Toast toast = Toast.makeText(getApplicationContext(),
                        pos.latitude + " " + pos.longitude, Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        LatLng pos = new LatLng(location.getLatitude(), location.getLongitude());
        mMap.addMarker(new MarkerOptions()
                .position(pos)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                .title("Ваша позиція"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(), location.getLongitude())));
        mMap.animateCamera(CameraUpdateFactory.zoomBy(12));
    }

    public void addDraggableMarker(View view) {
        addDraggableMarker.setVisibility(View.GONE);
        cancel.setVisibility(View.VISIBLE);
        setPosition.setVisibility(View.VISIBLE);

        LatLng pos = new LatLng(10, 10);
        draggableMarker = mMap.addMarker(new MarkerOptions()
                .position(pos)
                .draggable(true)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                .title("Пересуньте цей маркер на нову позицію"));
    }

    public void onChoosePosition(View view) {
        LatLng pos = draggableMarker.getPosition();
        double longtitude = pos.longitude;
        double latitude   = pos.latitude;
    }

    public void onChooseCancel(View view) {
        addDraggableMarker.setVisibility(View.VISIBLE);
        cancel.setVisibility(View.GONE);
        setPosition.setVisibility(View.GONE);
        draggableMarker.remove();
    }

}