package com.elcom.service.book;

import com.elcom.model.Book;
import com.elcom.repository.book.BookCustomizeRepository;
import com.elcom.repository.book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookCustomizeRepository bookCustomizeRepository;

    @Override
    public List<Book> findAll(Integer currentPage, Integer rowsPerPage, String sort) {
        {
            if (currentPage > 0) currentPage--;

            Pageable paging = PageRequest.of(currentPage, rowsPerPage, Sort.by(sort).descending());

            Page<Book> pagedResult = bookRepository.findAll(paging);

            if (pagedResult.hasContent())
                return pagedResult.getContent();
            else
                return new ArrayList<>();
        }
    }
    @Override
    public Book findById(Integer bookID)
    {
        return bookCustomizeRepository.findById(bookID);
    }

    @Override
    public Book save(Book book)
    {
        bookRepository.save(book);
        return book;
    }
    @Override
    public void remove(Book book)
    {
        bookRepository.delete(book);
    }

}
