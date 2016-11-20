package com.smora.arch.howto.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smora.arch.howto.R;
import com.smora.arch.howto.data.network.model.Place;

public class FavoritesFragment extends Fragment {

    public FavoritesFragment() {
        // Required empty public constructor
    }

    public static FavoritesFragment newInstance() {
        FavoritesFragment fragment = new FavoritesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.items_recyclerview);

        Place[] places = { new Place("Help"),
                new Place("Delete"),
                new Place("Cloud"),
                new Place("Favorite"),
                new Place("Like"),
                new Place("Cloud"),
                new Place("Favorite"),
                new Place("Like"),
                new Place("Cloud"),
                new Place("Favorite"),
                new Place("Like"),
                new Place("Cloud"),
                new Place("Favorite"),
                new Place("Like"),
                new Place("Cloud"),
                new Place("Favorite"),
                new Place("Like"),
                new Place("Cloud"),
                new Place("Favorite"),
                new Place("Like"),
                new Place("Cloud"),
                new Place("Favorite"),
                new Place("Like"),
                new Place("Cloud"),
                new Place("Favorite"),
                new Place("Like"),
                new Place("Cloud"),
                new Place("Favorite"),
                new Place("Like"),
                new Place("Cloud"),
                new Place("Favorite"),
                new Place("Like"),
                new Place("Rating")};

        // 2. set layoutManger
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        // 3. create an adapter
        PlaceAdapter adapter = new PlaceAdapter(places);
        // 4. set adapter
        recyclerView.setAdapter(adapter);

        return view;
    }

    public static class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.ViewHolder> {
        private Place[] places;

        public PlaceAdapter(Place[] places) {
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

            viewHolder.labelTextView.setText(places[position].getLabel());

        }

        public static class ViewHolder extends RecyclerView.ViewHolder {

            public TextView labelTextView;

            public ViewHolder(View itemLayoutView) {
                super(itemLayoutView);
                labelTextView = (TextView) itemLayoutView.findViewById(R.id.item_label_textview);
            }
        }


        // Return the size of your itemsData (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return places.length;
        }
    }

}
