package com.F0rchy.Entropy.models;

import jakarta.persistence.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Articles {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title, anons, author, actionDate;
    private int views;

    @Lob
    @Column(length=4096)
    private String full_text;

    private Date date = new Date();

    public String dateToString(Date date) {
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        return format1.format(date);
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getActionDate() {
        return dateToString(date);
    }

    public void setActionDate(String actionDate) {
        this.actionDate = dateToString(date);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnons() {
        return anons;
    }

    public void setAnons(String anons) {
        this.anons = anons;
    }

    public String getFull_text() {
        return full_text;
    }

    public void setFull_text(String full_text) {
        this.full_text = full_text;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public Articles() {
    }

    public Articles(String title, String anons, String full_text, String author) {
        this.title = title;
        this.anons = anons;
        this.full_text = full_text;
        this.author = author;
        this.actionDate = dateToString(date);
        this.views = 0;
    }
}
