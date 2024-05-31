package com.librarymanagementsystem.librarymanagementsystem.service;

import com.librarymanagementsystem.librarymanagementsystem.dto.BookDto;
import com.librarymanagementsystem.librarymanagementsystem.dto.BorrowRequestDto;
import com.librarymanagementsystem.librarymanagementsystem.dto.ReturnRequestDto;
import com.librarymanagementsystem.librarymanagementsystem.entity.Book;
import com.librarymanagementsystem.librarymanagementsystem.entity.User;
import com.librarymanagementsystem.librarymanagementsystem.exception.BookException;
import com.librarymanagementsystem.librarymanagementsystem.exception.UserException;

import java.time.LocalDate;
import java.util.List;

public interface BookService {
    Book addBook(Book book) throws BookException;
    List<Book> getBooks() throws BookException;
    Book updateBook(Book book) throws BookException;
    void deleteBook(int id) throws BookException;
    Book getBook(int id) throws BookException;
    //double calculateFine(Book book) throws Exception;
    void borrowBook(int bid, BorrowRequestDto borrowRequestDto) throws Exception;
    void returnBook(int bid, ReturnRequestDto returnRequestDto);
    double calculateFine(LocalDate returnDate, LocalDate submitDate);
    void collectFine(User borrower, double fine);
    void updateBookCopies(int bookId) throws BookException;
    void updateBookCopiesInViewBooks(int bookId) throws BookException;
}
