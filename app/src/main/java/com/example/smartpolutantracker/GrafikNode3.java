package com.example.smartpolutantracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class GrafikNode3 extends AppCompatActivity {
    WebView grafiknode3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafik_node3);
        getSupportActionBar().setTitle("Grafik Impedansi Node 3");
        grafiknode3 = (WebView) findViewById(R.id.grafiknode3);
        grafiknode3.getSettings().setJavaScriptEnabled(true);
        grafiknode3.loadUrl("https://thingspeak.com/channels/1012558/charts/3?bgcolor=%23ffffff&color=%23d62020&dynamic=true&results=60&title=Sensor+Node3&type=line");
    }
}
