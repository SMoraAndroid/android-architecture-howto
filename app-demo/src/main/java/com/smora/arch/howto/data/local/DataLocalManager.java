package com.smora.arch.howto.data.local;

import android.content.Context;

import java.util.Set;

public class DataLocalManager {

    private static volatile DataLocalManager INSTANCE;

    public static DataLocalManager with(final Context context) {
        synchronized (DataLocalManager.class) {
            if (INSTANCE == null) {
                INSTANCE = new DataLocalManager(context);
            }
            return INSTANCE;
        }
    }

    private final FavoriteDataManager favoriteDataManager;

    private DataLocalManager(final Context context) {
        this.favoriteDataManager = new FavoriteDataManager(context);
    }

    public void getFavoritePlaceIds(final DataLocalCallback<Set<String>> callback) {
        if (callback == null) {
            return;
        }
        callback.onResponse(favoriteDataManager.getPlaceIds());
    }

    public void markPlaceAsFavorite(final String placeId) {
        favoriteDataManager.put(placeId);
    }

    public void unmarkPlaceAsFavorite(final String placeId) {
        favoriteDataManager.remove(placeId);
    }

    public interface DataLocalCallback<T> {

        void onResponse(T data);
        void onFailure(Throwable throwable);

    }


}
