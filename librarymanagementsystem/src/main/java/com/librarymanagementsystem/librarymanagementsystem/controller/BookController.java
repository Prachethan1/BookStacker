package com.librarymanagementsystem.librarymanagementsystem.controller;

import com.librarymanagementsystem.librarymanagementsystem.dto.BorrowRequestDto;
import com.librarymanagementsystem.librarymanagementsystem.dto.ReturnRequestDto;
import com.librarymanagementsystem.librarymanagementsystem.entity.Book;
import com.librarymanagementsystem.librarymanagementsystem.entity.Cart;
import com.librarymanagementsystem.librarymanagementsystem.entity.User;
import com.librarymanagementsystem.librarymanagementsystem.exception.BookException;
import com.librarymanagementsystem.librarymanagementsystem.repository.CartRepository;
import com.librarymanagementsystem.librarymanagementsystem.service.BookService;
import com.librarymanagementsystem.librarymanagementsystem.service.CartService;
import com.librarymanagementsystem.librarymanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@RestController
public class BookController {


    @Autowired
    private CartService cartService;

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;


    @PostMapping("/addBook")
    public Book addBook(@ModelAttribute Book book) throws BookException {
        return bookService.addBook(book);

    }

    @GetMapping("/viewBooks")
    public ModelAndView getBooks(Model model) throws BookException {
//         List<Book> books = bookService.getBooks();
//        return new ModelAndView("viewBooks","books",books);
//

         //model.addAttribute("books", books);
        // System.out.println(books);
////         return new ModelAndView("viewBooks");


        List<Book> books = bookService.getBooks();
         ModelAndView modelAndView = new ModelAndView("viewBooks");
         modelAndView.addObject("books", books);
         return modelAndView;
    }

    @PutMapping("/updateBook")
    public Book updateBook(@RequestBody Book updatedBook) throws BookException {
        return bookService.updateBook(updatedBook);
    }

    @DeleteMapping("/deleteBook/{id}")
    public void deleteBook(@PathVariable int id) throws BookException{
        bookService.deleteBook(id);
    }

//    @GetMapping("/bookId/{id}")
//    public Book getBook(@PathVariable int id) throws BookException{
//        Book book = bookService.getBook(id);
//        return book;
//    }

//    @GetMapping("/viewCart/{bookId}")
//    public ModelAndView getMyBooks(Model model, @PathVariable("bookId") int bookId) throws BookException{
//        List<Book> books = bookService.getBooksInCart();
//        model.addAttribute("books", books);
//        return "viewCart";

//        Book books = bookService.getBook(bookId);
//        Cart cart = new Cart(books.getBookId(), books.getTitle(), books.getAuthor(), books.getPrice());
//        cartService.saveMyBooks(cart);
//        ModelAndView modelAndView = new ModelAndView("viewCart");
//        modelAndView.addObject("books", books);
//        return modelAndView;
//    }

    @GetMapping("/viewCart")
    public ModelAndView getMyBooks(){
//       w ModelAndView modelAndView = new ModelAndView("viewCart");
//        //modelAndView.addObject("cart", cart);
//        return modelAndView;  w

        ModelAndView modelAndView = new ModelAndView("viewCart");
        List<Cart> cart = cartService.getAllCartItems(); // Assuming you have a method to get all cart items
        modelAndView.addObject("cart", cart);
        return modelAndView;
    }

    @RequestMapping("/viewCart/{bookId}")
    public ModelAndView getMyList(@PathVariable("bookId") int bookId, Model model) throws BookException{
//        Book books = bookService.getBook(bookId);
//        //Cart cart = new Cart(books.getBookId(), books.getTitle(), books.getAuthor(), books.getPrice());
//        Cart cart = cartService.getCart(bookId);
//        cartService.saveMyBooks(cart);
//        ModelAndView modelAndView = new ModelAndView("redirect:/viewCart");
//        modelAndView.addObject("cart", cart);
//        return modelAndView;


        Book book = bookService.getBook(bookId);
        Cart cart = cartService.getCart(bookId);
        if(book.getCopies()>0) {
            //Cart cart = new Cart(book.getBookId(), book.getTitle(), book.getAuthor(), book.getPrice(), 1);
            cartService.addBookToCart(bookId);
            bookService.decreaseBookCopies(bookId);
            //bookService.updateBookCopiesInViewBooks(bookId);
            //cartService.updateCartCopiesInViewCart(bookId);
        }
//        else if(cart1.getCopies()>0 && book.getCopies()>0 && bookId== cart1.getBookId()){
//            Cart cart = new Cart(book.getBookId(), book.getTitle(), book.getAuthor(), book.getPrice(), cart1.getCopies()+1);
//            cartService.addBookToCart(book, cart);
//            bookService.updateBookCopiesInViewBooks(bookId);
//        }
//        else if(cart.getCopies()>1){
//            cartService.updateCopies(book);
//        }
        return new ModelAndView("redirect:/viewCart");
    }



    @RequestMapping(value = "/viewCart/delete/{bookId}", method = RequestMethod.POST)
    public ModelAndView deleteCartItem(@PathVariable("bookId") int bookId) throws BookException {
            cartService.deleteCartItem(bookId);
//            bookService.updateBookCopies(bookId);
        bookService.increaseBookCopies(bookId);
        return new ModelAndView("redirect:/viewCart");
    }


    @PostMapping("/borrow/{bid}")
    public String borrowBook(@PathVariable int bid, @RequestBody BorrowRequestDto borrowRequestDto) throws Exception{
        bookService.borrowBook(bid, borrowRequestDto/*borrowRequestDto.getBorrower(), borrowRequestDto.getBorrowDate(), borrowRequestDto.getReturnDate()*/);
        return "Book borrowed successfully";
    }

    @PostMapping("/return/{bid}")
    public String returnBook(@PathVariable int bid, @RequestBody ReturnRequestDto returnRequestDto){
        bookService.returnBook(bid, returnRequestDto);
        return "Book returned successfully";
    }

//    @PostMapping("/return/{bid}")
//    public String returnBook(@PathVariable int bid, @RequestParam int uid, @RequestBody ReturnRequestDto returnRequestDto, @RequestBody BorrowRequestDto borrowRequestDto) throws Exception {
//        Book book = bookService.getBook(bid);
//        User user = userService.getUser(uid);
//
////        LocalDate currentDate = new LocalDate();
////        book.setSubmitDate(currentDate);
//
//        if(returnRequestDto.getSubmitDate().isBefore(borrowRequestDto.getReturnDate())) {
//            return "Book returned successful and no fine applied";
//        }
//        else{
//            double fine = bookService.calculateFine(book);
//            userService.updateTotalFine(user, fine);
//            return String.format("Book returned successfully and total fine is %.2f", fine);
//        }
//    }

    @PostMapping("/payFine/{uid}")
    public String payFine(@PathVariable int uid, @RequestParam double amount) throws Exception {
        User user = userService.getUser(uid);
        userService.payFine(user, amount);
        if(user.getTotalFine()==0)
        return "fine collection successful";
        else
            return String.format("Remaining fine amount %.2f", user.getTotalFine());
    }


}
