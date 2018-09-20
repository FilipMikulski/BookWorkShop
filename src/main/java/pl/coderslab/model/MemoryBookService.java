package pl.coderslab.model;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class MemoryBookService implements BookService {
    private List<Book> list;
    public MemoryBookService() {
        list = new ArrayList<>();
        list.add(new Book("9788324631766", "Thinking in Java", new Author("Bruce", "Eckel"),
                "Helion", "programming"));
        list.add(new Book( "9788324627738", "Rusz glowa, Java.",
                new Author("Sierra", "Kathy"), "Helion", "programming"));
        list.add(new Book("9780130819338", "Java 2. Podstawy",
                new Author("Cay","Horstmann"), "Helion", "programming"));
    }
    public List<Book> getList() {return list;}

    public void setList(List<Book> list) {this.list = list;}

    public Book getBookById(Long id) {

        List<Book> book = list.stream().filter(e -> e.getId() == id).collect(Collectors.toList());

        if(book.size()>0){
            return book.get(0);
        } else {
            return null;
        }
    }

    public void addBookToList(Book book) {
        list.add(book);
    }

    public void editBookByOtherBook(Book book, Book bookToEdit) {
        bookToEdit.setTitle(book.getTitle());
        bookToEdit.setAuthor2(book.getAuthor());
        bookToEdit.setPublisher(book.getPublisher());
        bookToEdit.setType(book.getType());
        bookToEdit.setIsbn(book.getIsbn());
    }

    public void deleteBook(Long id){
        Book bookToDelete = getBookById(id);
        list.remove(bookToDelete);

    }
}