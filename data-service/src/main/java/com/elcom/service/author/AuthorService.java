package com.elcom.service.author;

import com.elcom.model.Author;

import java.util.List;

public interface AuthorService {
    Author save(Author author);

    void remove(Author author);

    List<Author> findAll(Integer currentPage, Integer rowPerPage, String sort);

    Author findById(String author);
}
