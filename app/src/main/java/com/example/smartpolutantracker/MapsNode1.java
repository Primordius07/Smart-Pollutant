package com.example.smartpolutantracker;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

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

public class MapsNode1 extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    DatabaseReference databaseReference;
    String latstr1,longstr1,latstr2,longstr2,latstr3,longstr3,latstr4,longstr4,latstr5,longstr5;
    double latitude1,longitude1,latitude2,longitude2,latitude3,longitude3,latitude4,longitude4,latitude5,longitude5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_node1);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                latstr1 = dataSnapshot.child("Latitude1").getValue().toString();
                latitude1 = Double.parseDouble(latstr1);
                longstr1 = dataSnapshot.child("Longitude1").getValue().toString();
                longitude1 = Double.parseDouble(longstr1);
                latstr2 = dataSnapshot.child("Latitude2").getValue().toString();
                latitude2 = Double.parseDouble(latstr2);
                longstr2 = dataSnapshot.child("Longitude2").getValue().toString();
                longitude2 = Double.parseDouble(longstr2);
                latstr3 = dataSnapshot.child("Latitude3").getValue().toString();
                latitude3 = Double.parseDouble(latstr3);
                longstr3 = dataSnapshot.child("Longitude3").getValue().toString();
                longitude3 = Double.parseDouble(longstr3);
                latstr4 = dataSnapshot.child("Latitude4").getValue().toString();
                latitude4 = Double.parseDouble(latstr4);
                longstr4 = dataSnapshot.child("Longitude4").getValue().toString();
                longitude4 = Double.parseDouble(longstr4);
                latstr5 = dataSnapshot.child("Latitude5").getValue().toString();
                latitude5 = Double.parseDouble(latstr5);
                longstr5 = dataSnapshot.child("Longitude5").getValue().toString();
                longitude5 = Double.parseDouble(longstr5);
                LatLng node1 = new LatLng(latitude1,longitude1);
                mMap.addMarker(new MarkerOptions().position(node1).title("Lokasi Node1"));
                LatLng node2 = new LatLng(latitude2,longitude2);
                mMap.addMarker(new MarkerOptions().position(node2).title("Lokasi Node2"));
                LatLng node3 = new LatLng(latitude3,longitude3);
                mMap.addMarker(new MarkerOptions().position(node3).title("Lokasi Node3"));
                LatLng node4 = new LatLng(latitude4,longitude4);
                mMap.addMarker(new MarkerOptions().position(node4).title("Lokasi Node4"));
                LatLng node5 = new LatLng(latitude5,longitude5);
                mMap.addMarker(new MarkerOptions().position(node5).title("Lokasi Node5"));
                float zoomLevel = 15.5f; //This goes up to 21
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(node1, zoomLevel));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(node2, zoomLevel));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(node5, zoomLevel));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(node4, zoomLevel));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(node3, zoomLevel));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        // Add a marker in Sydney and move the camera

    }
}
