package com.elcom.service.category;

import com.elcom.model.Category;

import java.util.List;

public interface CategoryService {

    Category save(Category category);

    void remove(Category category);

    List<Category> findAll(Integer currentPage, Integer rowPerPage, String sort);

    Category findById(String categoryID);
}
