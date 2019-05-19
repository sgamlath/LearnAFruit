package com.example.learnafruit;

import java.io.Serializable;

/**
 * QuestionModel
 *
 * The model class for question model implements serializable interface to make this model serializable
 */
public class FruitModel implements Serializable {
    private int id;

    private String name , img_path, description;

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

    public String getImg_path() {
        return img_path;
    }

    public void setImg_path(String img_path) {
        this.img_path = img_path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
