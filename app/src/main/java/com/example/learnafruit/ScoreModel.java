package com.example.learnafruit;

/**
 * ScoreModel
 *
 * The model class for score record model
 */
class ScoreModel {
    private int id;
    private String name;
    private String score;
    private String timestamp;

    public ScoreModel(int id, String name, String score, String timestamp) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.timestamp = timestamp;
    }

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

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
