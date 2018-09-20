package pl.coderslab.model;

import java.util.List;

public interface BookService {
    List<Book> getList();
    void setList(List<Book> list);
    Book getBookById(Long id);
    void addBookToList(Book book);
    void editBookByOtherBook(Book book, Book bookToEdit);
    void deleteBook(Long id);
}
