package pl.coderslab.model;

import java.util.List;

public interface AuthorService {
    List<Author> getList();
    Author getAuthorById(Integer id);
    void addAuthorToList(Author author);
    void editAuthorByOtherAuthor(Author author, Author authorToEdit);
    void deleteAuthor(Integer id);
}
