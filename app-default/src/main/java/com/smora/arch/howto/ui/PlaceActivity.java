package com.smora.arch.howto.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;

import com.smora.arch.howto.R;

public class PlaceActivity extends AppCompatActivity {

    private static final String LOG_TAG = PlaceActivity.class.getSimpleName();

    private static final String EXTRA_NAME_PLACE_ID = "com.smora.arch.howto.ui.PlaceActivity.place.id";

    public static Intent getStartIntent(final Context context, final String placeId) {
        if (TextUtils.isEmpty(placeId)) {
            throw new IllegalArgumentException("Argument placeId cannot be null or empty.");
        }
        final Intent intent = new Intent(context, PlaceActivity.class);
        intent.putExtra(EXTRA_NAME_PLACE_ID, placeId);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
