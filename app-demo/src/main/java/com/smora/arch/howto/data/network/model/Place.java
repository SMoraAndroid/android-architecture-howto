package com.smora.arch.howto.data.network.model;

import com.google.gson.annotations.SerializedName;

public class Place {

    private String id;
    private String label;
    private String country;
    @SerializedName("image_id")
    private String imageId;
    @SerializedName("image_author")
    private String imageAuthor;
    @SerializedName("image_credit")
    private String imageCredit;
    private String description;
    private Double latitiude;
    private Double longitude;

    public String getLabel() {
        return label;
    }

    public String getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public String getImageId() {
        return imageId;
    }

    public String getImageAuthor() {
        return imageAuthor;
    }

    public String getImageCredit() {
        return imageCredit;
    }

    public String getDescription() {
        return description;
    }

    public Double getLatitiude() {
        return latitiude;
    }

    public Double getLongitude() {
        return longitude;
    }
}
