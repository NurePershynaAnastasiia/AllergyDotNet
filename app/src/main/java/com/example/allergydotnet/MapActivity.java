package com.example.allergydotnet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.annotation.SuppressLint;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.allergydotnet.util.MarkerInfo;
import com.example.allergydotnet.util.PointInfo;
import com.example.allergydotnet.util.RetrofitInterface;
import com.example.allergydotnet.util.UserNameSubInfo;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.Marker;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
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
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, LocationListener {

    Intent intent;
    int user_id;

    private GoogleMap mMap;

    private HashMap<Marker, MarkerInfo> MarkersInfo = new HashMap<>();

    private Button addDraggableMarker;
    private Button setPosition;
    private Button cancel;
    private Marker draggableMarker = null;

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://192.168.1.105:3000";
    private RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        intent = getIntent();
        user_id = intent.getIntExtra("user_id", 0);

        BottomNavigationView bottomNavMenu = findViewById(R.id.bottom_navigation);
        bottomNavMenu.setSelectedItemId(R.id.map);

        bottomNavMenu.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent myint;
                if (item.getItemId() == R.id.consultations) {
                    myint = new Intent(getApplicationContext(), ConsultationsActivity.class);
                    myint.putExtra("user_id", user_id);
                    startActivity(myint);
                } else if (item.getItemId() == R.id.map) {
                    myint = new Intent(getApplicationContext(), MapActivity.class);
                    myint.putExtra("user_id", user_id);
                    startActivity(myint);
                } else if (item.getItemId() == R.id.doctors) {
                    myint = new Intent(getApplicationContext(), DoctorsActivity.class);
                    myint.putExtra("user_id", user_id);
                    startActivity(myint);
                }
                return false;
            }
        });

        RelativeLayout top_menu = findViewById(R.id.top_menu);
        ImageButton toProfile = findViewById(R.id.profilebtn);
        toProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myint = new Intent(getApplicationContext(), ProfileActivity.class);
                myint.putExtra("user_id", user_id);
                startActivity(myint);
            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        addDraggableMarker = (Button) findViewById(R.id.addbtn);
        setPosition = (Button) findViewById(R.id.choose_marker_position);
        cancel = (Button) findViewById(R.id.cancel_position);
        loadMarkersFromServer();

//        if (markerInfo.get) {
//            CreateAllergenPopup();
//        }
    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        mMap.moveCamera( CameraUpdateFactory.newLatLngZoom(new LatLng(50.015942834701185,36.226407489713274) , 14.0f) );


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

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                MarkerInfo markerInfo = MarkersInfo.get(marker);
                if(markerInfo != null) {
//                    Intent intent = new Intent(MapActivity.this, MapActivity.class);
//                    intent.putExtra("markerInfo",markerInfo);
//                    startActivity(intent);
                    CreateAllergenPopup(markerInfo.getTitle());
                }
                return true;
            }
        });

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_COARSE);
        criteria.setSpeedRequired(true);
        criteria.setBearingAccuracy(Criteria.ACCURACY_HIGH);

        if (locationManager != null && locationManager.getBestProvider(criteria, false) != null) {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            locationManager.requestSingleUpdate(locationManager.getBestProvider(criteria, false), this, null);
        } else {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            //setMarkersFromDb();
            locationManager.requestSingleUpdate("passive", this, null);
        }
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

        LatLng pos = new LatLng(50.015942834701185, 36.226407489713274);
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

    public void loadMarkersFromServer() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);
        HashMap<String, String> map = new HashMap<>();

        Call<ArrayList<PointInfo>> call = retrofitInterface.executeCheckedPoints(map);
        call.enqueue(new Callback<ArrayList<PointInfo>>() {
            @Override
            public void onResponse(Call<ArrayList<PointInfo>> call, Response<ArrayList<PointInfo>> response) {

                if (response.code() == 200) {

                    ArrayList<PointInfo> result = response.body();
                    int point_id;
                    String allergen_name;
                    String point_coordinates_latitude;
                    String point_coordinates_longitude;
                    for (int i = 0; i < result.size(); i++) {
                        point_id = result.get(i).getPoint_id();
                        allergen_name = result.get(i).getAllergen_name();
                        point_coordinates_latitude = result.get(i).getPoint_coordinates_latitude();
                        point_coordinates_longitude = result.get(i).getPoint_coordinates_longitude();
                        MarkerInfo markerInfo = new MarkerInfo(point_id,Double.parseDouble(point_coordinates_longitude), Double.parseDouble(point_coordinates_latitude), allergen_name);
                        LatLng pos = new LatLng(markerInfo.getLatitude(), markerInfo.getLongtitude());
                        Marker marker = mMap.addMarker(new MarkerOptions()
                                        .position(pos)
                                        .title(markerInfo.getTitle()));
                        MarkersInfo.put(marker, markerInfo);
                    }
                } else if (response.code() == 404) {
                    Toast.makeText(MapActivity.this, "Something went wrong",
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<PointInfo>> call, Throwable t) {
                Toast.makeText(MapActivity.this, "Name" + t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    private void CreateAllergenPopup(String allergen_name) {
        layout = findViewById(R.id.map_relative);
        LayoutInflater inflater= (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popUpView=inflater.inflate(R.layout.popup_allergen_info,null);
        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = ViewGroup.LayoutParams.MATCH_PARENT;
        boolean focusable = true;
        TextView allergenName = popUpView.findViewById(R.id.allergenName);
        allergenName.setText(allergen_name);
        PopupWindow popupWindow = new PopupWindow(popUpView,width,height,focusable);
        layout.post(new Runnable() {
            @Override
            public void run() {
                popupWindow.showAtLocation(layout, Gravity.BOTTOM,0,0);
            }
        });
        Button back = popUpView.findViewById(R.id.backbtn);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
//                Toast.makeText(SettingsActivity.this, "Листа успішно надіслано",
//                        Toast.LENGTH_LONG).show();
            }
        });
        popUpView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }

}