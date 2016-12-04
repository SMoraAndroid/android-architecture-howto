package com.smora.arch.howto.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.smora.arch.howto.R;
import com.smora.arch.howto.data.network.DataNetworkManager;
import com.smora.arch.howto.data.network.model.Place;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.List;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.ViewHolder> {

    private final List<Place> places;
    private final PlaceAdapterCallback callback;
    private final Picasso picasso;

    public PlaceAdapter(Context context, List<Place> places, PlaceAdapterCallback callback) {
        this.places = places;
        this.callback = callback;
        this.picasso = Picasso.with(context.getApplicationContext());
    }

    @Override
    public PlaceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_place_layout, null);

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
        viewHolder.seeMoreButton.setOnClickListener(new SeeMoreOnClickListener(viewHolder.itemView, place.getId(), callback));

        picasso.load(DataNetworkManager.getImageUrl(places.get(position).getImageId())).into(viewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        if (places == null) {
            return 0;
        }
        return places.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageView;
        private final TextView titleTextView;
        private final TextView subtitleTextView;
        private final TextView supportingTextView;
        private final Button seeMoreButton;

        private ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            imageView = (ImageView) itemLayoutView.findViewById(R.id.item_place_imageview);
            titleTextView = (TextView) itemLayoutView.findViewById(R.id.item_title_textview);
            subtitleTextView = (TextView) itemLayoutView.findViewById(R.id.item_subtitle_textview);
            supportingTextView = (TextView) itemLayoutView.findViewById(R.id.item_supporting_textview);
            seeMoreButton = (Button) itemLayoutView.findViewById(R.id.item_see_more_button);
        }
    }

    public interface PlaceAdapterCallback {
        void onSeeMoreButtonClick(View transitionView, String placeId);
    }

    private static class SeeMoreOnClickListener implements View.OnClickListener {
        private final View itemView;
        private final String placeId;
        private final WeakReference<PlaceAdapterCallback> callbackRef;

        public SeeMoreOnClickListener(final View itemView, final String placeId, final PlaceAdapterCallback callback) {
            this.itemView = itemView;
            this.placeId = placeId;
            this.callbackRef = new WeakReference<>(callback);
        }

        @Override
        public void onClick(View view) {
            if (callbackRef.get() == null) {
                return;
            }
            callbackRef.get().onSeeMoreButtonClick(itemView, placeId);
        }
    }

}
