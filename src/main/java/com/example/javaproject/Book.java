package com.example.javaproject;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a book
 */

@Entity(name = "titles")
public class Book {

    /**
     * ISBN of the book
     */
    @Id
    private String isbn;

    /**
     * Title of the book
     */
    private String title;

    /**
     * Edition number of the book
     */
    private int editionNumber;

    /**
     * Year of copyright
     */
    private String copyright;

    /**
     * List of authors
     */
    @ManyToMany
    @JoinTable(
            name = "author_isbn",
            joinColumns = @JoinColumn(name = "isbn"),
            inverseJoinColumns = @JoinColumn(name = "id"))
    private List<Author> authorList = new ArrayList<>();


    /**
     * Get the ISBN
     * @return isbn
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Set the ISBN
     */

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Get the title
     * @return title
     */

    public String getTitle() {
        return title;
    }

    /**
     * Set the title
     */

    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * Get the edition number
     * @return editionNumber
     */

    public int getEditionNumber() {
        return editionNumber;
    }

    /**
     * Set the edition number
     */

    public void setEditionNumber(int editionNumber) {
        this.editionNumber = editionNumber;
    }

    /**
     * Get the year of copyright
     * @return copyright
     */

    public String getCopyright() {
        return copyright;
    }

    /**
     * Set the year of copyright
     */

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    /**
     * Get the list of authors
     * @return authorList
     */
    public List<Author> getAuthorList() {
        return authorList;
    }

    /**
     * Set the list of authors
     */
    public void setAuthorList(List<Author> authorList) {
        this.authorList = authorList;
    }
}
