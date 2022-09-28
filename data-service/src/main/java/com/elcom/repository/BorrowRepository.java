package com.elcom.repository;

import com.elcom.model.Borrow;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowRepository extends PagingAndSortingRepository<Borrow,Long> {
    List<Borrow> findByUserID(long userID);
    List<Borrow> findByBookID(Integer bookID);
}
