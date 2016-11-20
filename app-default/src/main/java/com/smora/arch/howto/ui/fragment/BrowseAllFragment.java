package com.smora.arch.howto.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smora.arch.howto.R;
import com.smora.arch.howto.data.network.DataNetworkManager;
import com.smora.arch.howto.data.network.model.Place;
import com.smora.arch.howto.ui.adapter.PlaceAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BrowseAllFragment extends Fragment {
    
    private static final String LOG_TAG = BrowseAllFragment.class.getSimpleName();

    public static BrowseAllFragment newInstance() {
        BrowseAllFragment fragment = new BrowseAllFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public BrowseAllFragment() {
        // Required empty public constructor
    }

    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_browse_all, container, false);

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
        PlaceAdapter adapter = new PlaceAdapter(getContext(), places);
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
