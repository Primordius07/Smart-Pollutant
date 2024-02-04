package com.example.smartpolutantracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PageInfo5 extends AppCompatActivity {
    DatabaseReference databaseReference;
    TextView real1,img1,mag1,fasa1,rssi1,snr1;
    String realstr,imgstr,fasastr,magstr, rssistr,snrstr;
    TextView lat,lng;
    String latstr,lngstr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_info5);
        real1 = (TextView) findViewById(R.id.real5);
        img1 = (TextView) findViewById(R.id.imaginer5);
        mag1 = (TextView) findViewById(R.id.magnitude5);
        fasa1 = (TextView) findViewById(R.id.fasa5);
        rssi1 = (TextView) findViewById(R.id.rssi5);
        snr1 = (TextView) findViewById(R.id.snr5);
        lat = (TextView) findViewById(R.id.latitude5);
        lng = (TextView) findViewById(R.id.longitude5);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                realstr = dataSnapshot.child("Real5").getValue().toString();
                real1.setText(realstr);
                imgstr = dataSnapshot.child("Imaginer5").getValue().toString();
                img1.setText(imgstr);
                magstr = dataSnapshot.child("Magnitude5").getValue().toString();
                mag1.setText(magstr);
                fasastr = dataSnapshot.child("Fasa5").getValue().toString();
                fasa1.setText(fasastr);
                rssistr = dataSnapshot.child("RSSI5").getValue().toString();
                rssi1.setText(rssistr);
                snrstr = dataSnapshot.child("SNR5").getValue().toString();
                snr1.setText(snrstr);
                latstr = dataSnapshot.child("Latitude5").getValue().toString();
                lat.setText(latstr);
                lngstr = dataSnapshot.child("Longitude5").getValue().toString();
                lng.setText(lngstr);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        getSupportActionBar().setTitle("Info Node5");
    }
}
