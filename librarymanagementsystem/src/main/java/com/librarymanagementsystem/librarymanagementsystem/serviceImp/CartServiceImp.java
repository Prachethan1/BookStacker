package com.librarymanagementsystem.librarymanagementsystem.serviceImp;

import com.librarymanagementsystem.librarymanagementsystem.entity.Book;
import com.librarymanagementsystem.librarymanagementsystem.entity.Cart;
import com.librarymanagementsystem.librarymanagementsystem.exception.BookException;
import com.librarymanagementsystem.librarymanagementsystem.repository.BookRepository;
import com.librarymanagementsystem.librarymanagementsystem.repository.CartRepository;
import com.librarymanagementsystem.librarymanagementsystem.service.BookService;
import com.librarymanagementsystem.librarymanagementsystem.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImp implements CartService {

    @Autowired
    private BookService bookService;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void saveMyBooks(Cart cart){
        cartRepository.save(cart);
    }

    @Override
    public Cart getCart(int bookId){
        return cartRepository.findById(bookId).orElse(null);
    }

    @Override
    public List<Cart> getAllCartItems(){
        return cartRepository.findAll();
    }

    @Override
    public void addBookToCart(Book book, Cart cart) {
            //if (cart.getCopies() == 0) {
              //  cartItem = new Cart(book.getBookId(), book.getTitle(), book.getAuthor(), book.getPrice(), 1);
            //} else {
        //Cart cartItem = new Cart(book.getBookId(), book.getTitle(), book.getAuthor(), book.getPrice(), book.getCopies() + 1);

        cartRepository.save(cart);

//        Cart cart = getCart(cartId);
//        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
//        cart.addBook(book);
//        cartRepository.save(cart);
    }

    @Override
    public void updateCartCopiesInViewCart(int bookId){
        Cart cart = getCart(bookId);
        if(cart.getCopies()==0) {
            cart.setCopies(1);
        }
        else{
            cart.setCopies(cart.getCopies()+1);
        }
        cartRepository.save(cart);
    }


    @Override
    public void updateCopies(Book book) {
        int bookId = book.getBookId();
        Cart cart = getCart(bookId);
        cart.setCopies(cart.getCopies()+1);
        cartRepository.save(cart);
    }


    @Override
    public void deleteCartItem(int cartId)throws BookException {
        cartRepository.deleteById(cartId);
    }
}
