package com.example.toptativa2.Models;

public class Option {
    private String Title;
    private int Image;

    public Option(String _pTitle, int _pImage){
        this.Title = _pTitle;
        this.Image = _pImage;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }
}
