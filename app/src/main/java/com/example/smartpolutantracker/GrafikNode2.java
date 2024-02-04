package com.example.smartpolutantracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class GrafikNode2 extends AppCompatActivity {
    WebView grafiknode2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafik_node2);
        getSupportActionBar().setTitle("Grafik Impedansi Node2");
        grafiknode2 = (WebView) findViewById(R.id.grafiknode2);
        grafiknode2.getSettings().setJavaScriptEnabled(true);
        grafiknode2.loadUrl("https://thingspeak.com/channels/1012558/charts/2?bgcolor=%23ffffff&color=%23d62020&dynamic=true&results=60&title=Sensor+Node2&type=line");
    }
}
