package com.example.javaproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Main controller for the Java project.
 */
@RestController
@RequestMapping("/api/v1")
public class MainController {

    /**
     * Path for books
     */
    public static final String BOOK = "/books";

    /**
     * Path for authors
     */
    public static final String AUTHOR = "/authors";

    /**
     * Book repository
     */
    @Autowired
    private BookRepository bookRepository;

    /**
     * Author repository
     */
    @Autowired
    private AuthorRepository authorRepository;

    /**
     * Get all books
     * @return all books
     */
    @GetMapping(BOOK)
    @CrossOrigin(origins = "*")
    public @ResponseBody
    Iterable<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    /**
     * Get a book with a specific ISBN
     * @param isbn ISBN of the book
     * @return book with the specified ISBN
     */
    @GetMapping(path = BOOK + "/{isbn}")
    @CrossOrigin(origins = "*")
    public @ResponseBody
    Book getBookWithId(@PathVariable String isbn) {
        return bookRepository.findBookByIsbn(isbn);
    }

    /**
     * Add a new book
     * @param isbn ISBN of the book
     * @param title title of the book
     * @param editionNumber edition number of the book
     * @param copyright copyright
     * @param author_id author ID
     * @return message
     */
    @PostMapping(BOOK)
    @CrossOrigin(origins = "*")
    public @ResponseBody
    String addNewBook(@RequestParam String isbn, @RequestParam String title, @RequestParam int editionNumber, @RequestParam String copyright, @RequestParam Integer author_id) {
        Book book = new Book();
        book.setIsbn(isbn);
        book.setTitle(title);
        book.setEditionNumber(editionNumber);
        book.setCopyright(copyright);
        Optional<Author> author = authorRepository.findById(author_id);
        if(author.isPresent()){
            book.getAuthorList().add(author.get());
            bookRepository.save(book);
            return "Book added";
        }
        return "Author not found";
    }

    /**
     * Update a book
     * @param isbn ISBN of the book
     * @param title title of the book
     * @param editionNumber edition number of the book
     * @param copyright copyright
     * @param author_id author ID
     * @return message
     */
    @GetMapping(AUTHOR)
    @CrossOrigin(origins = "*")
    public @ResponseBody
    Iterable<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    /**
     * Get an author with a specific ID
     * @param author_id author ID
     * @return author with the specified ID
     */
    @GetMapping(path = AUTHOR + "/{author_id}")
    @CrossOrigin(origins = "*")
    public @ResponseBody
    Optional<Author> getAuthorWithId(@PathVariable Integer author_id) {
        return authorRepository.findById(author_id);
    }

    /**
     * Add a new author
     * @param firstName first name of the author
     * @param lastName last name of the author
     * @return message
     */
    @PostMapping(AUTHOR)
    @CrossOrigin(origins = "*")
    public @ResponseBody
    String addNewAuthor(@RequestParam String firstName, @RequestParam String lastName) {
        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);
        authorRepository.save(author);
        return "Author added";
    }

    /**
     * Update an author
     * @param author_id author ID
     * @param firstName first name of the author
     * @param lastName last name of the author
     * @return message
     */
    @PutMapping(AUTHOR + "/{author_id}")
    @CrossOrigin(origins = "*")
    public @ResponseBody
    String updateAuthor(@PathVariable Integer author_id, @RequestParam String firstName, @RequestParam String lastName) {
        Optional<Author> author = authorRepository.findById(author_id);
        if (author.isPresent()) {
            Author author1 = author.get();
            author1.setFirstName(firstName);
            author1.setLastName(lastName);
            authorRepository.save(author1);
            return "Author updated";
        } else {
            return "Author not found";
        }
    }

    /**
     * Delete an author
     * @param author_id author ID
     * @return message
     */
    @DeleteMapping(AUTHOR + "/{author_id}")
    @CrossOrigin(origins = "*")
    public @ResponseBody
    String deleteAuthor(@PathVariable Integer author_id) {
        Optional<Author> author = authorRepository.findById(author_id);
        if (author.isPresent()) {
            authorRepository.deleteById(author_id);
            return "Deleted";
        } else {
            return "Author not found";
        }
    }

    /**
     * Update a book
     * @param isbn ISBN of the book
     * @param title title of the book
     * @param editionNumber edition number of the book
     * @param copyright copyright
     * @param author_id author ID
     * @return message
     */
    @PutMapping(BOOK + "/{isbn}")
    @CrossOrigin(origins = "*")
    public @ResponseBody
    String updateBook(@PathVariable String isbn, @RequestParam String title, @RequestParam int editionNumber, @RequestParam String copyright, @RequestParam Integer author_id) {
        Book book = bookRepository.findBookByIsbn(isbn);
        if (book != null) {
            book.setTitle(title);
            book.setEditionNumber(editionNumber);
            book.setCopyright(copyright);
            Optional<Author> author = authorRepository.findById(author_id);
            if (author.isPresent()) {
                book.getAuthorList().clear();
                book.getAuthorList().add(author.get());
                bookRepository.save(book);
                return "Book updated";
            } else {
                return "Author not found";
            }
        } else {
            return "Book not found";
        }
    }

    /**
     * Delete a book
     * @param isbn ISBN of the book
     * @return message
     */
    @DeleteMapping(BOOK + "/{isbn}")
    @CrossOrigin(origins = "*")
    public @ResponseBody
    String deleteBook(@PathVariable String isbn) {
        Book book = bookRepository.findBookByIsbn(isbn);
        if (book != null) {
            bookRepository.delete(book);
            return "Book deleted";
        } else {
            return "Book not found";
        }
    }
}