package com.example.bookstore;

public class BookModal
{
    private String bookName ;
    private String bookPub;
    private String bookCat;
    private String bookAuth;
    private String bookQuan ;
    private String bookPrice;
    private String bookDesc;
    private int id ;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookPub() {
        return bookPub;
    }

    public void setBookPub(String bookPub) {
        this.bookPub = bookPub;
    }

    public String getBookCat() {
        return bookCat;
    }

    public void setBookCat(String bookCat) {
        this.bookCat = bookCat;
    }

    public String getBookAuth() {
        return bookAuth;
    }

    public void setBookAuth(String bookAuth) {
        this.bookAuth = bookAuth;
    }

    public String getBookQuan() {
        return bookQuan;
    }

    public void setBookQuan(String bookQuan) {
        this.bookQuan = bookQuan;
    }

    public String getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(String bookPrice) {
        this.bookPrice = bookPrice;
    }

    public String getBookDesc() {
        return bookDesc;
    }

    public void setBookDesc(String bookDesc) {
        this.bookDesc = bookDesc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public BookModal(String bookName,String bookPub, String bookCat, String bookAuth, String bookQuan, String bookPrice, String bookDesc)
    {
        this.bookName=bookName;
        this.bookPub=bookPub;
        this.bookCat=bookCat;
        this.bookAuth=bookAuth;
        this.bookQuan=bookQuan;
        this.bookPrice=bookPrice;
        this.bookDesc=bookDesc;
    }






}