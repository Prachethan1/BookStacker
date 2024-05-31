package com.librarymanagementsystem.librarymanagementsystem.serviceImp;

import com.librarymanagementsystem.librarymanagementsystem.dto.BorrowRequestDto;
import com.librarymanagementsystem.librarymanagementsystem.dto.ReturnRequestDto;
import com.librarymanagementsystem.librarymanagementsystem.entity.Book;
import com.librarymanagementsystem.librarymanagementsystem.entity.Cart;
import com.librarymanagementsystem.librarymanagementsystem.entity.User;
import com.librarymanagementsystem.librarymanagementsystem.exception.BookException;
import com.librarymanagementsystem.librarymanagementsystem.exception.CustomNotFoundException;
import com.librarymanagementsystem.librarymanagementsystem.repository.BookRepository;
import com.librarymanagementsystem.librarymanagementsystem.repository.CartRepository;
import com.librarymanagementsystem.librarymanagementsystem.repository.UserRepository;
import com.librarymanagementsystem.librarymanagementsystem.service.BookService;
import com.librarymanagementsystem.librarymanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BookServiceImp implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;


    @Override
    public Book addBook(Book book) {
//        Book newBook = new Book();
//        newBook.setTitle(Book.getTitle());
//        newBook.setAuthor(Book.getAuthor());
//        newBook.setTitle(String.valueOf(Book.getCopies()));
//        newBook.setAuthor(Book.getDescription());
        return bookRepository.save(book);

    }

    @Override
    public List<Book> getBooks() {
        return bookRepository.findAll();

    }


    @Override
    public void updateBookCopies(int bookId) throws BookException {
//        Book book = getBook(bookId);
//        if (newCopies < 0) {
//            throw new BookException("Copies cannot be negative");
//        }
//        book.setCopies(newCopies);
//        bookRepository.save(book);

        Book book = getBook(bookId);
        book.setCopies(book.getCopies()+1);
        bookRepository.save(book);
    }

    @Override
    public void updateBookCopiesInViewBooks(int bookId) throws BookException {
        Book book = getBook(bookId);
        if (book.getCopies() <= 0) {
            throw new BookException("Copies cannot be negative");
        }
        book.setCopies(book.getCopies()-1);
        bookRepository.save(book);
    }



    @Override
    public Book updateBook(Book book) {
        Book existingBook = bookRepository.findById(book.getBookId()).orElse(null);
        existingBook.setTitle(book.getTitle());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setCopies(book.getCopies());
        existingBook.setDescription(book.getDescription());
        existingBook.setPrice(book.getPrice());
        existingBook.setStatus(book.getStatus());

        return bookRepository.save(existingBook);
    }

    @Override
    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Book getBook(int id) {
        return bookRepository.findById(id).orElse(null);
    }

//    @Override
//    public double calculateFine(Book book) {
//        if (!book.getSubmitDate().isBefore(book.getReturnDate())) {
//            long daysOverDue = ChronoUnit.DAYS.between(book.getReturnDate(), book.getSubmitDate());
//            double finePerDay = 1.0;
//            return finePerDay * daysOverDue;
//        } else
//            return 0.0;
//    }

   @Override
   public void borrowBook(int bid,BorrowRequestDto borrowRequestDto/* String borrower, LocalDate borrowDate, LocalDate returnDate*/) throws CustomNotFoundException {
       // Book book = bookRepository.findById(bid).orElseThrow(() -> new NoSuchElementException("Book not found with this id" + bid));
//       Optional<Book> bookOptional = bookRepository.findById(bid);
//       Book book = bookOptional.orElseThrow(() -> new CustomNotFoundException("Book not found with this id" + bid));
//       if ("Available".equals(book.getStatus())) {
//           book.borrowBook(borrower, borrowDate, returnDate);
//           book.setBorrowDate(LocalDate.now());
//           book.setReturnDate(LocalDate.now().plusDays(7));
//           bookRepository.save(book);
//       }
//        else {
//           throw new IllegalStateException("Book is not available for borrowing");
//       }
       Book book = bookRepository.findById(bid)
               .orElseThrow(() -> new RuntimeException("Book not found with ID: " + bid));
       if ("Available".equals(book.getStatus()) && book.getCopies() > 0) {
           book.setStatus("Borrowed");
           book.setBorrower(borrowRequestDto.getBorrower());
           borrowRequestDto.setBorrowDate(borrowRequestDto.getBorrowDate());
           borrowRequestDto.setReturnDate(borrowRequestDto.getReturnDate());
           book.setCopies(book.getCopies() - 1);

           bookRepository.save(book);
       } else {
           throw new IllegalStateException("Book is not available for borrowing");
       }
   }

   @Override
    public void returnBook(int bid, ReturnRequestDto returnRequestDto){
//       User authenticatedUser = userService.getAuthenticatedUser();
//       BorrowRequestDto borrowRequestDto = new BorrowRequestDto();
//       borrowRequestDto.setBorrower(authenticatedUser.getUsername());
//       borrowRequestDto.setBorrowDate(LocalDate.now());
//       borrowRequestDto.setReturnDate(LocalDate.now().plusDays(14));
//       Book book = bookRepository.findById(bid)
//               .orElseThrow(() -> new NoSuchElementException("Book not found with id: " + bid));
//
//       if ("Borrowed".equals(book.getStatus())) {
//           book.returnBook(submitDate);
//           bookRepository.save(book);
//       } else {
//           throw new IllegalStateException("Book is not currently borrowed");
//       }
       BorrowRequestDto borrowRequestDto = new BorrowRequestDto();
       Book book = bookRepository.findById(bid)
               .orElseThrow(() -> new RuntimeException("Book not found with ID: " + bid));
       if ("Borrowed".equals(book.getStatus())) {
           book.setStatus("Available");
//           book.setBorrower(null);
//           book.setBorrowDate(null);
//           book.setReturnDate(null);
           returnRequestDto.setSubmitDate(returnRequestDto.getSubmitDate());
           if (returnRequestDto.getSubmitDate().isAfter(borrowRequestDto.getReturnDate())) {
               double fine = calculateFine(borrowRequestDto.getReturnDate(), returnRequestDto.getSubmitDate());
               User borrower = userRepository.findByUsername(book.getBorrower());
               collectFine(borrower, fine);
           }

           book.setCopies(book.getCopies() + 1);
           bookRepository.save(book);
       } else {
           throw new IllegalStateException("Book is not borrowed and cannot be returned");
       }
  }
  @Override
    public double calculateFine(LocalDate returnDate, LocalDate submitDate) {
        long daysLate = ChronoUnit.DAYS.between(returnDate, submitDate);
        double FINE_PER_DAY = 1.0;
        if (daysLate > 0) {
            return daysLate * FINE_PER_DAY;
        }
        return 0;
    }
    @Override
    public void collectFine(User borrower, double fine) {
        borrower.setTotalFine(borrower.getTotalFine() + fine);
    }
}
