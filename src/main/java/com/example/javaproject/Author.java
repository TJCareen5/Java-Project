package com.example.javaproject;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;

/**
 * Represents an author
 */
@Entity(name = "authors")
public class Author {
    /**
     * Id of the author
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * First name of the author
     */
    private String firstName;

    /**
     * Last name of the author
     */
    private String lastName;

    /**
     * List of books
     */
    @ManyToMany(mappedBy = "authorList")
    @JsonBackReference
    private List<Book> bookList;

    /**
     * Get the id
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Set the id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Get the first name
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get the last name
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get the list of books
     * @return bookList
     */
    public List<Book> getBookList() {
        return bookList;
    }

    /**
     * Set the list of books
     */
    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}
