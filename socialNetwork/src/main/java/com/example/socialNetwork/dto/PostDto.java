package com.example.socialNetwork.dto;

import java.util.Date;

public class PostDto {

    public Long id;

    public String content;

    public Long likeNumber;

    public Date publicationDate;

    public Long authorId;

    public String authorUsername;

    public PostDto() {
    }

    public PostDto(Long id, String content, Long likeNumber, Date publicationDate, Long authorId, String authorUsername) {
        this.id = id;
        this.content = content;
        this.likeNumber = likeNumber;
        this.publicationDate = publicationDate;
        this.authorId = authorId;
        this.authorUsername = authorUsername;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(Long likeNumber) {
        this.likeNumber = likeNumber;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getAuthorUsername() {
        return authorUsername;
    }

    public void setAuthorUsername(String authorUsername) {
        this.authorUsername = authorUsername;
    }
}
