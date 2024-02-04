package com.example.smartpolutantracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class GrafikNode4 extends AppCompatActivity {
    WebView grafiknode4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafik_node4);
        getSupportActionBar().setTitle("Grafik Impedansi Node 4");
        grafiknode4 = (WebView) findViewById(R.id.grafiknode4);
        grafiknode4.getSettings().setJavaScriptEnabled(true);
        grafiknode4.loadUrl("https://thingspeak.com/channels/1012558/charts/4?bgcolor=%23ffffff&color=%23d62020&dynamic=true&results=60&title=Sensor+Node4&type=line");
    }
}
