package com.smora.arch.howto.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.smora.arch.howto.R;
import com.smora.arch.howto.data.network.DataNetworkManager;
import com.smora.arch.howto.data.network.model.Place;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    private ImageView placeImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        placeImageView = (ImageView) findViewById(R.id.place_image_view);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        callGetPlace();
    }

    private void callGetPlace() {
        DataNetworkManager.getService().getPlace(getIntent().getStringExtra(EXTRA_NAME_PLACE_ID)).enqueue(dataCallback);
    }

    private void showPlace(@NonNull final Place place) {

        Picasso.with(getApplicationContext()).load(DataNetworkManager.getImageUrl(place.getImageId())).into(placeImageView);
    }

    private void showError(final String message) {

    }

    private final Callback<Place> dataCallback = new Callback<Place>() {
        @Override
        public void onResponse(Call<Place> call, Response<Place> response) {
            final Place place = response.body();
            if (place == null) {
                showError("Place not found.");
            }
            showPlace(place);
        }

        @Override
        public void onFailure(Call<Place> call, Throwable t) {
            Log.w(LOG_TAG, "onFailure: ", t);
            showError("Error when getting data form network.");
        }
    };
}
