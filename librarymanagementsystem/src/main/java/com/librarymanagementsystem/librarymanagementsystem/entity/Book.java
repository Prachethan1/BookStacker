package com.librarymanagementsystem.librarymanagementsystem.entity;

import com.librarymanagementsystem.librarymanagementsystem.dto.BorrowRequestDto;
import com.librarymanagementsystem.librarymanagementsystem.dto.ReturnRequestDto;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Book {

    @Id
    @GeneratedValue
    private int bookId;
    private String author;
    private String title;
    private String status;
    private int copies;
    private int price;
    private String description;
    private String borrower;


    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cart> carts;

    public Book(int bookId,String title, String author, int price) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.price = price;
    }


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getBorrower() {
        return borrower;
    }

    public void setBorrower(String borrower) {
        this.borrower = borrower;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Book(){
        this.status = "Available";
       // this.copies = 1;
    }

//    public void borrowBook(String borrower, LocalDate borrowDate, LocalDate returnDate) {
//            this.status = "Borrowed";
//            this.borrower = borrowRequestDto.getBorrower();
//            this.borrowDate = borrowRequestDto.getBorrowDate();
//            this.returnDate = borrowRequestDto.getReturnDate();
//            this.copies--;
////            else{
////            System.out.println("Book not available for borrowing");
////            System.out.println("Book Status: " + this.status);
////            System.out.println("Available Copies: " + this.copies);
////        }
//    }

//    public void returnBook(LocalDate submitDate) {
//        this.status = "Available";
//        this.borrower = null;
//        this.borrowDate = null;
//        this.returnDate = null;
//        this.submitDate = returnRequestDto.getSubmitDate();
//        this.copies++;
//    }

}
