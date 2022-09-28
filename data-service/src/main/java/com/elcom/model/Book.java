package com.elcom.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "book")
public class Book implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "bookID")
    private Integer bookID;

    @Basic(optional = false)
    @Column(name = "bookName")
    private String bookName;

    @Basic(optional = false)
    @Column(name = "firstLetter")
    private String firstLetter;

    @ManyToOne
    @JoinColumn(name = "authorID", nullable = false)
    private Author author;

    @ManyToOne
    @JoinColumn(name = "categoryID", nullable = false)
    private Category category;

    public Book() {
    }

    public Book(Integer bookID, String bookName, String firstLetter, Author author, Category category) {
        //this.bookID = bookID;
        this.bookName = bookName;
        this.firstLetter = firstLetter;
        this.author = author;
        this.category = category;
    }

    public Integer getBookID() {
        return bookID;
    }

    public void setBookID(Integer bookID) {
        this.bookID = bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getFirstLetter() {
        return firstLetter;
    }

    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
