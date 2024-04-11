package com.example.javaproject;

import org.springframework.data.repository.CrudRepository;

/**
 * Repository for authors
 */
public interface AuthorRepository extends CrudRepository<Author, Integer> {

    Author findAuthorByFirstName(String firstName);
    Author findAuthorByLastName(String lastName);
    Author findAuthorByFirstNameAndLastName(String firstName, String lastName);
    Author findAuthorById(Integer id);
}
