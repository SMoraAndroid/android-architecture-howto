package com.smora.arch.howto.data.network;

import com.smora.arch.howto.data.network.model.Place;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface WebService {

    @GET("ws/places")
    Call<List<Place>> listPlaces();

}
