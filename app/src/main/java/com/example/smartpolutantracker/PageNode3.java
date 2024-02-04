package com.example.smartpolutantracker;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.os.Environment;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class PageNode3 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final int PERMISSION_STORAGE_CODE = 1000;
    Button downloaddata;
    ImageButton grafikbutton3;
    ImageButton info1;
    ImageButton lokasi;
    DatabaseReference databaseReference;
    TextView impepage3,hitungpage3;
    String impepage3str,hitungpage3str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_node3);
        Toolbar toolbar = findViewById(R.id.toolbar);
        impepage3 = (TextView) findViewById(R.id.impepage3);
        info1 = (ImageButton) findViewById(R.id.info3);
        lokasi = (ImageButton) findViewById(R.id.lokasi3);
        hitungpage3 = (TextView) findViewById(R.id.hitungpage3);
        downloaddata = (Button) findViewById(R.id.downloaddata3);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                impepage3str = dataSnapshot.child("Impedance3").getValue().toString();
                impepage3.setText(impepage3str);
                hitungpage3str = dataSnapshot.child("hitung3").getValue().toString();
                hitungpage3.setText(hitungpage3str);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        grafikbutton3 = (ImageButton) findViewById(R.id.grafik3);
        grafikbutton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(PageNode3.this,GrafikNode3.class);
                startActivity(a);
            }
        });
        info1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(PageNode3.this,PageInfo3.class);
                startActivity(a);
            }
        });
        lokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(PageNode3.this,MapsNode1.class);
                startActivity(a);
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
        getSupportActionBar().setTitle("Page Node3");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.page_node3, menu);
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
            Intent a = new Intent(PageNode3.this,MainActivity.class);
            startActivity(a);
        } else if (id == R.id.nav_pagenode1) {
            Intent a = new Intent(PageNode3.this,PageNode1.class);
            startActivity(a);
        } else if (id == R.id.nav_pagenode2) {
            Intent a = new Intent(PageNode3.this,PageNode2.class);
            startActivity(a);
        } else if (id == R.id.nav_pagenode3) {

        } else if (id == R.id.nav_pagenode4) {
            Intent a = new Intent(PageNode3.this,PageNode4.class);
            startActivity(a);
        } else if (id == R.id.nav_pagenode5) {
            Intent a = new Intent(PageNode3.this,PageNode5.class);
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
