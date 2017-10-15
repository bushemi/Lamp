package com.bushemi.dao.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * Created by igor on 15.08.17.
 */
@Entity
@Table(name = "HOBBY")
public class Hobby {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "hobbies")
    private Collection<Person> personsWithHobby =  new ArrayList<>();

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

    public Collection<Person> getPersonsWithHobby() {
        return personsWithHobby;
    }

    public void setPersonsWithHobby(Collection<Person> personsWithHobby) {
        this.personsWithHobby = personsWithHobby;
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        Hobby hobby = (Hobby) o;
        return id == hobby.id &&
                Objects.equals(title, hobby.title) &&
                Objects.equals(description, hobby.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Hobby{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", personsWithHobby=").append(personsWithHobby);
        sb.append('}');
        return sb.toString();
    }
}
