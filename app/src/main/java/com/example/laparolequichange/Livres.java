package com.example.laparolequichange;

import org.parceler.Parcel;

@Parcel
public class Livres  {

    private String book_name;
    private int chapter_number;
    private int imageResourceId;
    private String desc_livre;
    private String available;


    public Livres(){}


    public Livres(String book_name, int chapter_number, int imageResourceId,String desc_livre, String available) {
        this.book_name = book_name;
        this.chapter_number = chapter_number;
        this.imageResourceId = imageResourceId;
        this.desc_livre = desc_livre;
        this.available = available;

    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public Livres(String book_name, int chapter_number, int imageResourceId) {
        this.book_name = book_name;
        this.chapter_number = chapter_number;
        this.imageResourceId = imageResourceId;
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

    public String getDesc_livre() {
        return desc_livre;
    }

    public void setDesc_livre(String desc_livre) {
        this.desc_livre = desc_livre;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}
