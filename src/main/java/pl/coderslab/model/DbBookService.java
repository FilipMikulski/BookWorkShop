package pl.coderslab.model;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class DbBookService implements BookService {

    @Override
    public List<Book> getList() {
        return BookDao.loadAll();
    }

    @Override
    public void setList(List<Book> list) {

    }

    @Override
    public Book getBookById(Long id) {
        return BookDao.loadById(id);
    }

    @Override
    public void addBookToList(Book book) {

        BookDao.save(book);

    }

    @Override
    public void editBookByOtherBook(Book book, Book bookToEdit) {

        bookToEdit.setTitle(book.getTitle());
        bookToEdit.setAuthor2(book.getAuthor());
        bookToEdit.setPublisher(book.getPublisher());
        bookToEdit.setType(book.getType());
        bookToEdit.setIsbn(book.getIsbn());

        BookDao.save(bookToEdit);

    }

    @Override
    public void deleteBook(Long id) {

        BookDao.delete(BookDao.loadById(id));

    }
}
