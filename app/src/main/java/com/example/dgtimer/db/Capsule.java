package com.example.dgtimer.db;

import android.graphics.Color;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.List;

@Entity(tableName = "capsules")
public class Capsule {
    @PrimaryKey
    private int id;
    private String name;
    private String type;
    @TypeConverters(Converter.class)
    private List<Integer> stage;
    private String color;
    private String image;

    private boolean major = false;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Integer> getStage() {
        return stage;
    }

    public void setStage(List<Integer> stage) {
        this.stage = stage;
    }

    public int getColorAsInt(){
        return Color.parseColor(color);
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isMajor() {
        return major;
    }

    public void setMajor(boolean major) {
        this.major = major;
    }
}
