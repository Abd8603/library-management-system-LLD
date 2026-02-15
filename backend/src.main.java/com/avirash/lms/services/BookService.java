package com.avirash.lms.services;

import com.avirash.lms.valuebeans.Book;


import java.util.HashSet;
import java.util.logging.Logger;

/**
 * Class helpful to do CRUD operation of Book Management
 */
public class BookService {
    private HashSet<Book> books = new HashSet<Book>();
    private static BookService INSTANCE;

    private static final Logger LOGGER = Logger.getLogger(BookService.class.getName());

    private BookService() {
        LOGGER.info("BookService instance created");
    }

    public static BookService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BookService();
        }
        return INSTANCE;
    }

    public void saveBook(Book book) {
        books.add(book);
        LOGGER.info("Book saved");
    }

    public HashSet<Book> getBooks() {
        if (books.isEmpty()) {
            LOGGER.warning("Inventory is empty, kindly add books");
        }
        LOGGER.info("Books found");
        return books;
    }

    public Book getBookByIsbn(String id) {
        for (Book book : books) {
            if (book.getIsbn().equals(id)) {
                LOGGER.info("Book found");
                return book;
            }
        }
        LOGGER.warning("Can not find book with id: " + id);
        return null;
    }

    public Book removeBook(String id) {
        Book book = getBookByIsbn(id);
        if (book != null) {
            books.remove(getBookByIsbn(id));
            LOGGER.info("Book removed");
        }
        return book;
    }

    public Book updateBook(Book newBook) {
        Book oldBook = getBookByIsbn(newBook.getIsbn());
        if (oldBook != null) {

            if (newBook.getTitle() != null) {
                oldBook.setTitle(newBook.getTitle());
            }

            if (newBook.getAuthor() != null) {
                oldBook.setAuthor(newBook.getAuthor());
            }

            if (newBook.getPublicationYear() <= 0){
                oldBook.setPublicationYear(newBook.getPublicationYear());
            }

            LOGGER.warning("Book updated");
        }
        return oldBook;
    }

    public Book getBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                LOGGER.info("Book found");
                return book;
            }
        }
        LOGGER.warning("Can not find book with title: " + title);
        return null;
    }

    public Book getBookByAuthor(String author) {
        for (Book book : books) {
            if (book.getAuthor().equals(author)) {
                LOGGER.info("Book found");
                return book;
            }
        }
        LOGGER.warning("Can not find book with author: " + author);
        return null;
    }
}
