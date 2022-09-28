package com.elcom.model;

import com.elcom.JsonDateSerializer.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "borrow")
public class Borrow implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long borrowID;

    @Column(name = "userID")
    @ManyToOne
    @JoinColumn(name = "userID")
    private Long userID;

    @Column(name = "boookID")
    @ManyToOne
    @JoinColumn(name = "boookID")
    private Integer boookID;

    @Column(name = "borrowDate",nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = JsonDateSerializer.class)
    @CreatedDate
    private Date borrowDate;

    @Column(name = "returnDate",nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = JsonDateSerializer.class)
    @CreatedDate
    private Date returnDate;

    public Borrow() {
    }

    public Borrow(Long borrowID, Long userID, Integer boookID, Date borrowDate, Date returnDate) {
        this.borrowID = borrowID;
        this.userID = userID;
        this.boookID = boookID;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public Long getBorrowID() {
        return borrowID;
    }

    public void setBorrowID(Long borrowID) {
        this.borrowID = borrowID;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Integer getBoookID() {
        return boookID;
    }

    public void setBoookID(Integer boookID) {
        this.boookID = boookID;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
