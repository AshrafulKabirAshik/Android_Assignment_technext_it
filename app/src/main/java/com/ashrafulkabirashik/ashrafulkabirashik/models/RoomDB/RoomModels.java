package com.ashrafulkabirashik.ashrafulkabirashik.models.RoomDB;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "ashik_database_table")
public class RoomModels implements Serializable {

    @PrimaryKey(autoGenerate = false)
    private int ID;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "author_name")
    private String author_name;
    @ColumnInfo(name = "author_profession")
    private String author_profession;
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] cover_photo;
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] author_avatar;

    public RoomModels() {
    }

    public RoomModels(int ID, String title, String description, String author_name, String author_profession, byte[] cover_photo, byte[] author_avatar) {
        this.ID = ID;
        this.title = title;
        this.description = description;
        this.author_name = author_name;
        this.author_profession = author_profession;
        this.cover_photo = cover_photo;
        this.author_avatar = author_avatar;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getAuthor_profession() {
        return author_profession;
    }

    public void setAuthor_profession(String author_profession) {
        this.author_profession = author_profession;
    }

    public byte[] getCover_photo() {
        return cover_photo;
    }

    public void setCover_photo(byte[] cover_photo) {
        this.cover_photo = cover_photo;
    }

    public byte[] getAuthor_avatar() {
        return author_avatar;
    }

    public void setAuthor_avatar(byte[] author_avatar) {
        this.author_avatar = author_avatar;
    }
}
