package com.elcom.service.book;

import com.elcom.model.Book;

import java.util.List;
import java.util.UUID;

public interface BookService {
    Book save(Book book);


    void remove(Book book);

    List<Book> findAll(Integer currentPage, Integer rowPerPage, String sort);

    Book findById(Integer book);
}
