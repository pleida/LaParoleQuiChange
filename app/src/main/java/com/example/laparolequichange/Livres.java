package com.example.laparolequichange;

import android.widget.TextView;

public class Livres  {

    private String book_name;
    private String image_path;
    private int chapter_number;


    public Livres(String book_name, String image_path, int chapter_number) {
        this.book_name = book_name;
        this.image_path = image_path;
        this.chapter_number = chapter_number;

    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public int getChapter_number() {
        return chapter_number;
    }

    public void setChapter_number(int chapter_number) {
        this.chapter_number = chapter_number;
    }
}
