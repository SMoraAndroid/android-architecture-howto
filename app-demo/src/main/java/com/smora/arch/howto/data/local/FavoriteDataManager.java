package com.smora.arch.howto.data.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.ArraySet;

import java.util.Set;

public class FavoriteDataManager {

    private static final String PREFS_NAME = "com.smora.arch.howto.data.local.preferences";
    private static final String PREFS_KEY_PLACE_IDS = "com.smora.arch.howto.data.local.preferences.place.ids";

    private final SharedPreferences settings;

    private Set<String> placeIds;

    public FavoriteDataManager(final Context context) {
        this.settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public Set<String> getPlaceIds() {
        if (placeIds == null) {
            placeIds = settings.getStringSet(PREFS_KEY_PLACE_IDS, new ArraySet<String>());
        }
        return placeIds;
    }

    public void put(final String placeId) {
        if (TextUtils.isEmpty(placeId)) {
            return;
        }
        final Set<String> data = getPlaceIds();
        if (data.contains(placeId)) {
            return;
        }
        data.add(placeId);
        final SharedPreferences.Editor editor = settings.edit();
        editor.putStringSet(PREFS_KEY_PLACE_IDS, data);
        editor.apply();
    }

    public void remove(final String placeId) {
        if (TextUtils.isEmpty(placeId)) {
            return;
        }
        final Set<String> data = getPlaceIds();
        if (!data.contains(placeId)) {
            return;
        }
        data.remove(placeId);
        final SharedPreferences.Editor editor = settings.edit();
        editor.putStringSet(PREFS_KEY_PLACE_IDS, data);
        editor.apply();
    }

}
