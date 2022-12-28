package com.kartikworld.bangali;
public class MovieModel {
    private String newsTitle, newsDes, newsCat;
    public MovieModel() {
    }
    public MovieModel(String newsTitle, String newsDes, String newsCat) {
        this.newsTitle = newsTitle;
        this.newsDes = newsDes;
        this.newsCat = newsCat;
    }

    public String getTitle() {
        return newsTitle;
    }
    public void setTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getDes() {
        return newsDes;
    }
    public void setGenre(String newsDes) {
        this.newsDes = newsDes;
    }

    public String getCat() {
        return newsCat;
    }
    public void setCat(String newsCat) {
        this.newsCat = newsCat;
    }



}