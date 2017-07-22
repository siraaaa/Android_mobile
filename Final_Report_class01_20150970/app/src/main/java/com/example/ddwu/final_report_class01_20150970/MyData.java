package com.example.ddwu.final_report_class01_20150970;

/**
 * Created by sira on 2017-06-26.
 */

public class MyData {
    private int _id;
    private String title;
    private String date;
    private String star;
    private String content;
    private String review;
    //private Drawable image;

    public MyData(int _id, String title, String date, String star, String content, String review) {
        this._id = _id;
        this.title = title;
        this.date = date;
        this.star = star;
        this.content = content;
        this.review = review;
        //this.image = image;
    }


    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    @Override
    public String toString() {
        return "MyData{" +
                "_id=" + _id +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", star='" + star + '\'' +
                ", content='" + content + '\'' +
                ", review='" + review + '\'' +
                '}';
    }
}
