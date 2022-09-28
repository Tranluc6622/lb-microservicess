package com.elcom.controller;

import com.elcom.model.Book;
import com.elcom.repository.book.BookCustomizeRepository;
import com.elcom.service.book.BookService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;
    @Autowired
    private BookCustomizeRepository bookCustomizeRepository;

    @RequestMapping(value = "/book", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Lay danh sach Author",response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Thành công, có dữ liệu"),
            @ApiResponse(code = 204, message = "Thành công, không có dữ liệu"),
            @ApiResponse(code = 401, message = "Chưa xác thực"),
            @ApiResponse(code = 403, message = "Truy cập bị cấm"),
            @ApiResponse (code = 404, message = "Không tìm thấy"),
            @ApiResponse(code = 500, message = "Lỗi BackEnd")
    })
    public ResponseEntity<List<Book>> findAll(@RequestParam(defaultValue = "1") Integer currentPage,
                                              @RequestParam(defaultValue = "10") Integer rowsPerPage,
                                              @RequestParam(defaultValue = "authorid") String sort){
        List<Book> bookList = bookCustomizeRepository.findAll();
        if(bookList.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(bookList,HttpStatus.OK);
    }
    @PostMapping("/book")
    public Book createBook(@RequestBody Book book)
    {
//        Book _book = bookService.save(new Book(book.getBookName(),book.getFirstLetter(),book.getAuthor(),book.getCategory()));
//        return new ResponseEntity<>(_book,HttpStatus.OK);
        return bookService.save(book);
    }
}
