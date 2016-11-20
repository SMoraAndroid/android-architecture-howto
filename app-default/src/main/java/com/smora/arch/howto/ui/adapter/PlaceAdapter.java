package com.smora.arch.howto.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smora.arch.howto.R;
import com.smora.arch.howto.data.network.model.Place;

import java.util.List;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.ViewHolder> {
    private List<Place> places;

    public PlaceAdapter(List<Place> places) {
        this.places = places;
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

        viewHolder.labelTextView.setText(places.get(position).getLabel());

    }

    @Override
    public int getItemCount() {
        if (places == null) {
            return 0;
        }
        return places.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView labelTextView;

        private ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            labelTextView = (TextView) itemLayoutView.findViewById(R.id.item_label_textview);
        }
    }

}
