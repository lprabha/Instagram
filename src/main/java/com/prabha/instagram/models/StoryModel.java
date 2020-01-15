package com.prabha.instagram.models;

public class StoryModel{

//    private String authorPic, postByName, postBy;
    private String dailyPhoto, postByName;

//    private int dailyPhoto, views;
//    private String sId;


    public StoryModel(String postByName, String dailyPhoto) {
        this.postByName = postByName;
        this.dailyPhoto = dailyPhoto;
    }

    public String getDailyPhoto() {
        return dailyPhoto;
    }

    public void setDailyPhoto(String dailyPhoto) {
        this.dailyPhoto = dailyPhoto;
    }

    public String getPostByName() {
        return postByName;
    }

    public void setPostByName(String postByName) {
        this.postByName = postByName;
    }
}
