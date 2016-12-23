package com.ya.pokupay.model;


import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Table(name="images")
public class Image {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    private String name;

    private int advertid;

    @Transient
    private String base64imageFile;

    @Transient
    private String user;

    @Transient
    private String title;

    @Transient
    private MultipartFile file;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAdvertid() {
        return advertid;
    }

    public void setAdvertid(int advertid) {
        this.advertid = advertid;
    }

    public String getBase64imageFile() {
        return base64imageFile;
    }

    public void setBase64imageFile(String base64imageFile) {
        this.base64imageFile = base64imageFile;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
