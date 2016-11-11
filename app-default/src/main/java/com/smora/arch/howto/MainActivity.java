package com.smora.arch.howto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.smora.arch.webserver.WebServer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebServer.with(getApplicationContext()).start();

        ((WebView) findViewById(R.id.webview)).loadUrl("http://localhost:8080");
    }

    @Override
    protected void onDestroy() {
        WebServer.with(getApplicationContext()).stop();
        super.onDestroy();
    }
}
