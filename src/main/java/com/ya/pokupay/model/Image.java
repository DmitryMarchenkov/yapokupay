package com.ya.pokupay.model;


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

    private Blob data;

    @Transient
    private String base64imageFile;

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

    public Blob getData() {
        return data;
    }

    public void setData(Blob data) {
        this.data = data;
    }

    public String getBase64imageFile() {
        return base64imageFile;
    }

    public void setBase64imageFile(String base64imageFile) {
        this.base64imageFile = base64imageFile;
    }
}
