package com.example.smartpolutantracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class GrafikNode5 extends AppCompatActivity {
    WebView grafiknode5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafik_node5);
        getSupportActionBar().setTitle("Grafik Impedansi Node5");
        grafiknode5 = (WebView) findViewById(R.id.grafiknode5);
        grafiknode5.getSettings().setJavaScriptEnabled(true);
        grafiknode5.loadUrl("https://thingspeak.com/channels/1012558/charts/5?bgcolor=%23ffffff&color=%23d62020&dynamic=true&results=60&title=Sensor+Node5&type=line");
    }
}
