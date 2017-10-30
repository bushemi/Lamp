package com.bushemi.model.entity;

import java.util.Objects;

public class PlaceDto {

    private long id;
    private String title;
    private String description;
    private double latitude;
    private double longitude;

    public PlaceDto() {
    }

    public PlaceDto(long id, String title, String description, double latitude, double longitude) {
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
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        PlaceDto placeDto = (PlaceDto) o;
        return id == placeDto.id &&
                Double.compare(placeDto.latitude, latitude) == 0 &&
                Double.compare(placeDto.longitude, longitude) == 0 &&
                Objects.equals(title, placeDto.title) &&
                Objects.equals(description, placeDto.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, latitude, longitude);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PlaceDto{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", latitude=").append(latitude);
        sb.append(", longitude=").append(longitude);
        sb.append('}');
        return sb.toString();
    }
}
