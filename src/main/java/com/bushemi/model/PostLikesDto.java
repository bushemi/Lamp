package com.bushemi.model;

import java.util.Objects;


public class PostLikesDto {

    private PersonDto person;
    private PostDto post;

    public PersonDto getPerson() {
        return person;
    }

    public void setPerson(PersonDto person) {
        this.person = person;
    }

    public PostDto getPost() {
        return post;
    }

    public void setPost(PostDto post) {
        this.post = post;
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        PostLikesDto that = (PostLikesDto) o;
        return Objects.equals(person, that.person) &&
                Objects.equals(post, that.post);
    }

    @Override
    public int hashCode() {
        return Objects.hash(person, post);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PostLikesDto{");
        sb.append("person=").append(person);
        sb.append(", post=").append(post);
        sb.append('}');
        return sb.toString();
    }
}
