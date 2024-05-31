package com.librarymanagementsystem.librarymanagementsystem.entity;

import jakarta.persistence.*;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int cartId;

    int bookId;
    String title;
    String author;
    int price;
    int copies;

    public Cart(int bookId, String title, String author, int price, int copies) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.price = price;
        this.copies = copies;
    }

    public Cart(){
        super();
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }
}
