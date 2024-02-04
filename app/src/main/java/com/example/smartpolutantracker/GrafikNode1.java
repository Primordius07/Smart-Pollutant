package com.example.smartpolutantracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class GrafikNode1 extends AppCompatActivity {
    WebView grafiknode1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafik_node1);
        getSupportActionBar().setTitle("Grafik Impedansi Node1");
        grafiknode1 = (WebView) findViewById(R.id.grafiknode1);
        grafiknode1.getSettings().setJavaScriptEnabled(true);
        grafiknode1.loadUrl("https://thingspeak.com/channels/1012558/charts/1?bgcolor=%23ffffff&color=%23d62020&dynamic=true&results=60&title=Sensor+Node1&type=line");
    }
}
