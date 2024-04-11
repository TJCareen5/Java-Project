package com.example.javaproject;

import org.springframework.data.repository.CrudRepository;

/**
 * Repository for books
 */
public interface BookRepository extends CrudRepository<Book, Integer> {

    Book findBookByIsbn(String isbn);
    Book deleteBookByIsbn(String isbn);

}
