package com.elcom.controller;

import com.elcom.model.Book;
import com.elcom.model.Borrow;
import com.elcom.model.User;
import com.elcom.repository.BorrowRepository;
import com.elcom.repository.UserCustomizeRepository;
import com.elcom.repository.UserRepository;
import com.elcom.repository.book.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class BorrowController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BorrowController.class);
    @Autowired
    BorrowRepository borrowRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private UserCustomizeRepository userCustomizeRepository;

    @GetMapping("/borrow")
    public List<Borrow>getAllBorrows()
    {
        return (List<Borrow>) borrowRepository.findAll();
    }
    @GetMapping("/findByUserID")
    public List<Borrow>getByUserID(@RequestParam(value = "userID") long userID)
    {
        return borrowRepository.findByUserID(userID);
    }
    @GetMapping("/findByBookID")
    public List<Borrow>getByBookID(@RequestParam(value = "bookID") Integer bookID)
    {
        return borrowRepository.findByBookID(bookID);
    }
    @PostMapping("/borrow")
    public String addBorrow(@Valid @RequestBody Borrow borrow)
    {
        User user = userRepository.findById(borrow.getUserID()).get();
        Book book = bookRepository.findById(borrow.getBoookID()).get();

    }


}
