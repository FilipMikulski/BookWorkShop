package pl.coderslab.model;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DbAuthorService implements AuthorService {

    @Override
    public List<Author> getList() {
        return AuthorDao.loadAll();
    }

    @Override
    public Author getAuthorById(Integer id) {
        return AuthorDao.loadById(id);
    }

    @Override
    public void addAuthorToList(Author author) {
        AuthorDao.save(author);

    }

    @Override
    public void editAuthorByOtherAuthor(Author author, Author authorToEdit) {

        authorToEdit.setFirstName(author.getFirstName());
        authorToEdit.setLastName(author.getLastName());

        AuthorDao.save(authorToEdit);

    }

    @Override
    public void deleteAuthor(Integer id) {

        AuthorDao.delete(AuthorDao.loadById(id));

    }
}
