package com.smora.arch.howto.data.network;

import com.smora.arch.howto.data.network.model.Place;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface WebService {

    @GET("ws/places")
    Call<List<Place>> listPlaces();

    @GET("ws/places/{placeId}")
    Call<Place> getPlace(@Path("placeId") int placeId);

}
