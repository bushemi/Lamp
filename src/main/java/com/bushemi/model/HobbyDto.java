package com.bushemi.model;

import java.util.Objects;

/**
 * Created by igor on 15.08.17.
 */
public class HobbyDto {

    private long id;
    private String title;
    private String description;

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

    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        HobbyDto hobbyDto = (HobbyDto) o;
        return id == hobbyDto.id &&
                Objects.equals(title, hobbyDto.title) &&
                Objects.equals(description, hobbyDto.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("HobbyDto{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
