package com.smora.arch.howto;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.Toast;

import com.smora.arch.webserver.WebServer;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebServer.with(this).startAsync(startServerCallback);
    }

    @Override
    protected void onDestroy() {
        WebServer.with(this).stopAsync();
        super.onDestroy();
    }

    private final WebServer.StartCallback startServerCallback = new WebServer.StartCallback() {
        public void onServerStartSucceed() {
            ((WebView) findViewById(R.id.webview)).loadUrl("http://localhost:8080/ws/places");
        }

        public void onServerStartFailed() {
            Toast.makeText(getApplicationContext(), "Error when starting local server", Toast.LENGTH_LONG).show();
        }
    };

}
