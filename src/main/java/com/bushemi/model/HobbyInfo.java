package com.bushemi.model;

import com.bushemi.model.entity.HobbyDto;

/**
 * Created by igor on 24.10.17.
 *
 * @Version 1.0
 * Utility class for jsp
 */
public class HobbyInfo {
    private long id;
    private String title;
    private String description;

    public HobbyInfo() {
    }

    public HobbyInfo(long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public HobbyInfo(HobbyDto hobby) {
        this(hobby.getId(), hobby.getTitle(), hobby.getDescription());
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("HobbyInfo{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
