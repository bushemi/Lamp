package com.bushemi.model.entity;

import java.util.Objects;


public class PostLikesDto {

    private PersonDto liker;
    private PostDto post;

    public PersonDto getLiker() {
        return liker;
    }

    public void setLiker(PersonDto liker) {
        this.liker = liker;
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
        return Objects.equals(liker, that.liker) &&
                Objects.equals(post, that.post);
    }

    @Override
    public int hashCode() {
        return Objects.hash(liker, post);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PostLikesDto{");
        sb.append("liker=").append(liker);
        sb.append(", post=").append(post);
        sb.append('}');
        return sb.toString();
    }
}
