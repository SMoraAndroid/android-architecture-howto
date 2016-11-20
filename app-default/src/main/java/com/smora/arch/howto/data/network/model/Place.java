package com.smora.arch.howto.data.network.model;

public class Place {

    private String id;
    private String label;
    private String country;
    private String image_id;
    private String image_author;
    private String image_credit;
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

    public String getImage_id() {
        return image_id;
    }

    public String getImage_author() {
        return image_author;
    }

    public String getImage_credit() {
        return image_credit;
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
