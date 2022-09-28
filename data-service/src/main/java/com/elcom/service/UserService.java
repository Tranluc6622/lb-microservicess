package com.elcom.service;


import com.elcom.model.User;
import java.util.List;

public interface UserService{
    List<User> findAll(Integer currentPage, Integer rowPerPage, String sort);
    User findById(Long userID);

    void save(User user);

    void remove(User user);




}
