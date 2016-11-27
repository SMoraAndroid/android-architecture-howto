package com.smora.arch.howto.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.smora.arch.howto.R;
import com.smora.arch.howto.data.network.DataNetworkManager;
import com.smora.arch.howto.data.network.model.Place;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.ViewHolder> {

    private final Picasso picasso;
    private List<Place> places;

    public PlaceAdapter(Context context, List<Place> places) {
        this.places = places;
        this.picasso = Picasso.with(context.getApplicationContext());
    }

    @Override
    public PlaceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_place_layout, null);

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        final Place place = places.get(position);

        if (place == null) {
            return;
        }

        viewHolder.titleTextView.setText(place.getLabel());
        viewHolder.subtitleTextView.setText(place.getCountry());
        viewHolder.supportingTextView.setText(place.getDescription());

        picasso.load(DataNetworkManager.getImageUrl(places.get(position).getImageId())).into(viewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        if (places == null) {
            return 0;
        }
        return places.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView titleTextView;
        private TextView subtitleTextView;
        private TextView supportingTextView;
        private ImageView imageView;

        private ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            titleTextView = (TextView) itemLayoutView.findViewById(R.id.item_title_textview);
            subtitleTextView = (TextView) itemLayoutView.findViewById(R.id.item_subtitle_textview);
            supportingTextView = (TextView) itemLayoutView.findViewById(R.id.item_supporting_textview);
            imageView = (ImageView) itemLayoutView.findViewById(R.id.item_place_imageview);
        }
    }

}
