package com.example.smartpolutantracker;

import android.Manifest;
import android.app.DownloadManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.NotificationCompat;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.os.Environment;
import android.os.Handler;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final int PERMISSION_STORAGE_CODE = 1000;
    private NotificationManager mManager;
    String ANDROID_CHANNEL_ID = "com.example.smartpolutantracker";
    public static final String ANDROID_CHANNEL_NAME = "ANDROID CHANNEL";
    Button downloaddata;
    DatabaseReference databaseReference;
    String impestr1,impestr2,impestr3,impestr4,impestr5;
    TextView impe1,impe2,impe3,impe4,impe5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        impe1 = (TextView) findViewById(R.id.impedansi1);
        impe2 = (TextView) findViewById(R.id.impedansi2);
        impe3 = (TextView) findViewById(R.id.impedansi3);
        impe4 = (TextView) findViewById(R.id.impedansi4);
        impe5 = (TextView) findViewById(R.id.impedansi5);
        downloaddata = (Button) findViewById(R.id.download);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                impestr1 = dataSnapshot.child("Impedance1").getValue().toString();
                impe1.setText(impestr1);
                impestr2 = dataSnapshot.child("Impedance2").getValue().toString();
                impe2.setText(impestr2);
                impestr3 = dataSnapshot.child("Impedance3").getValue().toString();
                impe3.setText(impestr3);
                impestr4 = dataSnapshot.child("Impedance4").getValue().toString();
                impe4.setText(impestr4);
                impestr5 = dataSnapshot.child("Impedance5").getValue().toString();
                impe5.setText(impestr5);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        downloaddata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED) {
                        String[] permissions =  {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(permissions, PERMISSION_STORAGE_CODE);
                    }
                    else {
                        startDownloading();
                    }
                }
                else {
                    startDownloading();
                }
            }
        });

        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        getSupportActionBar().setTitle("Smart Polutan Tracker");
    }

    public boolean doubleTapParam = false;

    @Override
    public void onBackPressed() {
        if (doubleTapParam) {
            super.onBackPressed();
            return;
        }

        this.doubleTapParam = true;
        Toast.makeText(this, "Tap sekali lagi untuk keluar", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleTapParam = false;
            }
        }, 2000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {

        } else if (id == R.id.nav_pagenode1) {
            Intent a = new Intent(MainActivity.this,PageNode1.class);
            startActivity(a);
        } else if (id == R.id.nav_pagenode2) {
            Intent a = new Intent(MainActivity.this,PageNode2.class);
            startActivity(a);
        } else if (id == R.id.nav_pagenode3) {
            Intent a = new Intent(MainActivity.this,PageNode3.class);
            startActivity(a);
        } else if (id == R.id.nav_pagenode4) {
            Intent a = new Intent(MainActivity.this,PageNode4.class);
            startActivity(a);
        } else if (id == R.id.nav_pagenode5) {
            Intent a = new Intent(MainActivity.this,PageNode5.class);
            startActivity(a);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void startDownloading(){
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse("https://thingspeak.com/channels/1012558/feed.csv"));
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE |
                DownloadManager.Request.NETWORK_WIFI);
        request.setTitle("Data_Impedansi");
        request.setDescription("Downloading data...");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, ""+ System.currentTimeMillis());
        DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_STORAGE_CODE: {
                if (grantResults.length>0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED){
                    startDownloading();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Permission Denied...!",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

}
