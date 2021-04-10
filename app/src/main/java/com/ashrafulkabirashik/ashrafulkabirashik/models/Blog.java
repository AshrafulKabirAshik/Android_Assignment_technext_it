package com.ashrafulkabirashik.ashrafulkabirashik.models;

public class Blog {
    int id;
    String title;
    String description;
    String cover_photo;

    private Author author;

    public Blog(int id, String title, String description, String cover_photo, Author author) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.cover_photo = cover_photo;
        this.author = author;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCover_photo() {
        return cover_photo;
    }

    public void setCover_photo(String cover_photo) {
        this.cover_photo = cover_photo;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
