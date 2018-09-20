package pl.coderslab.model;

import org.springframework.beans.factory.annotation.Autowired;

public class Book {

    private Long id;
    private String isbn;
    private String title;
    private Author author;
    private String publisher;
    private String type;

    private static Long idCreator = 0L;


    // Ten konstruktor używam do MemoryBookService - czyli zapisywanie do listy

//    public Book(){
//        setIdMemory();
//    }
//
//    public Book(String isbn, String title, String author, String publisher, String type) {
//        this.isbn = isbn;
//        this.title = title;
//        this.author = author;
//        this.publisher = publisher;
//        this.type = type;
//        setIdMemory();
//    }

    // Ten konstruktor używam do DbBookService, czyli zapisywanie do bazy danych.


    public Book() {
        this.id=0L;
    }

    public Book(String isbn, String title, Author author, String publisher, String type) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.type = type;
        this.id =0L;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIdMemory() {
        idCreator++;
        this.id = idCreator;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Integer id) {
        this.author = AuthorDao.loadById(id);
    }

    public void setAuthor2(Author author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
