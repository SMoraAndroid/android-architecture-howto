package com.smora.arch.howto.data.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataNetworkManager {

    private static final String END_POINT = "http://localhost:8090";

    private static final Retrofit RETROFIT = new Retrofit.Builder()
            .baseUrl(END_POINT)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static final WebService WEB_SERVICE = RETROFIT.create(WebService.class);

    public static WebService getService() {
        return WEB_SERVICE;
    }

    public static String getImageUrl(final String imageId) {
        return END_POINT + "/images/" + imageId;
    }
}
