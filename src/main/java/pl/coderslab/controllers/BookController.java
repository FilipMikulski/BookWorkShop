package pl.coderslab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.model.Author;
import pl.coderslab.model.Book;
import pl.coderslab.model.BookService;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookService bookService;

    @RequestMapping("/hello")
    public String hello(){
        return "{hello: World}";
    }

    @RequestMapping("/helloBook")
    public Book helloBook(){
        return new Book("9788324631766","Thinking in Java",
                new Author("Bruce", "Eckel"),"Helion","programming");
    }

    @GetMapping("")
    public List<Book> showAllBooks(){
        return bookService.getList();
    }

    @RequestMapping("/{id}")
    public Book book(@PathVariable String id) {

        Long bookId;

        try{
            bookId = Long.parseLong(id);
        } catch (Exception e) {
            return null;
        }

        return bookService.getBookById(bookId);
    }

    @PostMapping("")
    public Book addBook(@RequestBody Book book){
        bookService.addBookToList(book);
        return book;
    }

    @PutMapping("/{id}")
    public String editBook(@PathVariable String id, @RequestBody Book book) {
        Long bookId;

        try{
            bookId = Long.parseLong(id);
        } catch (Exception e) {
            return null;
        }

        Book bookToEdit = bookService.getBookById(bookId);

        bookService.editBookByOtherBook(book, bookToEdit);

        return "Book edited";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable String id) {

        Long bookId;

        try{
            bookId = Long.parseLong(id);
        } catch (Exception e) {
            return null;
        }

        bookService.deleteBook(bookId);

        return "Book removed";
    }


}