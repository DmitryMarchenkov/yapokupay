package com.ya.pokupay.model;

import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="advert")
@Indexed
public class Advert implements Serializable{

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    @Field
    private String title;

    private String price;

    private String description;

    private String date;

    private String authorid;

    private String authorUsername;

    private String category;

    private String state;

    private Integer viewCounter;

    private Integer imagesCount;

    @Transient
    private String base64imageFile;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthorid() {
        return authorid;
    }

    public void setAuthorid(String authorid) {
        this.authorid = authorid;
    }

    public String getAuthorUsername() {
        return authorUsername;
    }

    public void setAuthorUsername(String authorUsername) {
        this.authorUsername = authorUsername;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getViewCounter() {
        return viewCounter;
    }

    public void setViewCounter(Integer viewCounter) {
        this.viewCounter = viewCounter;
    }

    public Integer getImagesCount() {
        return imagesCount;
    }

    public void setImagesCount(Integer imagesCount) {
        this.imagesCount = imagesCount;
    }

    public String getBase64imageFile() {
        return base64imageFile;
    }

    public void setBase64imageFile(String base64imageFile) {
        this.base64imageFile = base64imageFile;
    }

    @Override
    public String toString() {
        return "Advert{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price='" + price + '\'' +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                ", authorid='" + authorid + '\'' +
                ", authorUsername='" + authorUsername + '\'' +
                ", category='" + category + '\'' +
                ", state='" + state + '\'' +
                ", imagesCount=" + imagesCount +
                '}';
    }
}
