package com.example.socialNetwork.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Post")
public class Post {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "content", nullable = true)
    public String content;

    @Column(name = "like_number")
    public Long likeNumber;

    @Column(name = "publication_date", nullable = false)
    public Date publicationDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Useer user;

    public Post() {
    }

    public Post(Long id, String content, Long likeNumber, Date publicationDate, Useer user) {
        this.id = id;
        this.content = content;
        this.likeNumber = likeNumber;
        this.publicationDate = publicationDate;
        this.user = user;
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

    public Useer getUser() {
        return user;
    }

    public void setUser(Useer user) {
        this.user = user;
    }
}
