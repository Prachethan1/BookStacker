package com.librarymanagementsystem.librarymanagementsystem.service;

import com.librarymanagementsystem.librarymanagementsystem.entity.Book;
import com.librarymanagementsystem.librarymanagementsystem.entity.Cart;
import com.librarymanagementsystem.librarymanagementsystem.exception.BookException;

import java.util.List;

public interface CartService {
    public void saveMyBooks(Cart cart);
    public Cart getCart(int bookId);
    public List<Cart> getAllCartItems();
    void addBookToCart(int bookId) throws BookException;
    void deleteCartItem(int bookId) throws BookException;
    void updateCopies(Book book);
    void updateCartCopiesInViewCart(int bookId);
}
