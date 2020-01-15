package com.prabha.instagram.models;

public class PostModel {

    private String postBy, subHead, caption, post, authorPic, postByName;
//    int loveCount, loveReacts, comments;


    public PostModel(String postBy, String postByName, String subHead, String caption, String post, String authorPic) {
        this.postBy = postBy;
        this.postByName = postByName;
        this.subHead = subHead;
        this.caption = caption;
        this.post = post;
        this.authorPic = authorPic;
    }

    public String getSubHead() {
        return subHead;
    }

    public void setSubHead(String subHead) {
        this.subHead = subHead;
    }

    public String getPostBy() {
        return postBy;
    }

    public void setPostBy(String postBy) {
        this.postBy = postBy;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getAuthorPic() {
        return authorPic;
    }

    public void setAuthorPic(String authorPic) {
        this.authorPic = authorPic;
    }

    public String getPostByName() {
        return postByName;
    }

    public void setPostByName(String postByName) {
        this.postByName = postByName;
    }
}
