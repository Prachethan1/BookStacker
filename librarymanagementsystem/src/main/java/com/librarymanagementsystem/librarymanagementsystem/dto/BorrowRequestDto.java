package com.librarymanagementsystem.librarymanagementsystem.dto;

import java.time.LocalDate;

public class BorrowRequestDto {
    private String borrower;
    private LocalDate borrowDate;
    private LocalDate returnDate;

    public String getBorrower() {
        return borrower;
    }

    public void setBorrower(String borrower) {
        this.borrower = borrower;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
