package com.elcom.controller;


import com.elcom.model.Author;
import com.elcom.repository.author.AuthorCustomizeRepository;
import com.elcom.service.author.AuthorService;
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
public class AuthorController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorController.class);

    @Autowired
    private AuthorService authorService;

    @Autowired
    private AuthorCustomizeRepository authorCustomizeRepository;

    @RequestMapping(value = "/author", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Lay danh sach Author",response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Thành công, có dữ liệu"),
            @ApiResponse(code = 204, message = "Thành công, không có dữ liệu"),
            @ApiResponse(code = 401, message = "Chưa xác thực"),
            @ApiResponse(code = 403, message = "Truy cập bị cấm"),
            @ApiResponse (code = 404, message = "Không tìm thấy"),
            @ApiResponse(code = 500, message = "Lỗi BackEnd")
    })
    public ResponseEntity<List<Author>>findAll(@RequestParam(defaultValue = "1") Integer currentPage,
                                               @RequestParam(defaultValue = "10") Integer rowsPerPage,
                                               @RequestParam(defaultValue = "authorid") String sort){
        List<Author> authorList = authorCustomizeRepository.findAll();
        if(authorList.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(authorList,HttpStatus.OK);
    }
    @PostMapping("/author")
    public ResponseEntity<Author>createAuthor(@RequestBody Author author)
    {
        Author _author = authorService.save(new Author(author.getAuthorID(), author.getAuthorName()));
        return new ResponseEntity<>(_author,HttpStatus.OK);
    }
}
