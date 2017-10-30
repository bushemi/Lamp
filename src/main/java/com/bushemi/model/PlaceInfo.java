package com.bushemi.model;

import com.bushemi.model.entity.PlaceDto;

/**
 * Created by igor on 24.10.17.
 *
 * @Version 1.0
 * Utility class for jsp
 */
public class PlaceInfo {
    private long id;
    private String title;
    private String description;
    private double latitude;
    private double longitude;

    public PlaceInfo() {
    }
    public PlaceInfo(PlaceDto place) {
        this(place.getId(), place.getTitle(), place.getDescription(),
                place.getLatitude(), place.getLongitude());
    }

    public PlaceInfo(long id, String title, String description, double latitude, double longitude) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PlaceInfo{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", latitude=").append(latitude);
        sb.append(", longitude=").append(longitude);
        sb.append('}');
        return sb.toString();
    }
}
