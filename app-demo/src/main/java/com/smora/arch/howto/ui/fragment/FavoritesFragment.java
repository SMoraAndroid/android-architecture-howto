package com.smora.arch.howto.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smora.arch.howto.R;
import com.smora.arch.howto.data.network.DataNetworkManager;
import com.smora.arch.howto.data.network.model.Place;
import com.smora.arch.howto.ui.PlaceActivity;
import com.smora.arch.howto.ui.adapter.PlaceAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoritesFragment extends Fragment {

    private static final String LOG_TAG = FavoritesFragment.class.getSimpleName();

    public static FavoritesFragment newInstance() {
        FavoritesFragment fragment = new FavoritesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    private RecyclerView recyclerView;

    public FavoritesFragment() {
        // Required empty public constructor
    }

    private final PlaceAdapter.PlaceAdapterCallback placeAdapterCallback = new PlaceAdapter.PlaceAdapterCallback() {

        @Override
        public void onSeeMoreButtonClick(View itemView, String placeId) {
            final Pair<View, String> transition = Pair.create(itemView.findViewById(R.id.item_place_imageview), getString(R.string.transition_name_home_placedetail_image));
            ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), transition);
            getContext().startActivity(PlaceActivity.getStartIntent(getContext(), placeId), transitionActivityOptions.toBundle());
        }

    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.items_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        callListPlaces();

        return view;
    }

    private void callListPlaces() {
        DataNetworkManager.getService().listPlaces().enqueue(dataCallback);
    }

    private void showProgress() {
        // TODO
    }

    private void showPlaces(final List<Place> places) {
        PlaceAdapter adapter = new PlaceAdapter(getContext(), places, placeAdapterCallback);
        recyclerView.setAdapter(adapter);
    }

    private void showEmpty() {
        // TODO
    }

    private void showError(final String message) {
        // TODO
    }

    private final Callback<List<Place>> dataCallback = new Callback<List<Place>>() {
        @Override
        public void onResponse(Call<List<Place>> call, Response<List<Place>> response) {
            if (!isAdded()) {
                return;
            }
            final List<Place> places = response.body();
            if (places == null || places.isEmpty()) {
                showEmpty();
            }
            showPlaces(places);
        }

        @Override
        public void onFailure(Call<List<Place>> call, Throwable t) {
            if (!isAdded()) {
                return;
            }
            Log.w(LOG_TAG, "onFailure: ", t);
            showError("Error when getting data form network.");
        }
    };

}
