package pl.coderslab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.model.Author;
import pl.coderslab.model.AuthorService;
import pl.coderslab.model.Book;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @RequestMapping("/hello")
    public String hello(){
        return "{hello: Authors!}";
    }

    @GetMapping("")
    public List<Author> showAllAuthors(){
        return authorService.getList();
    }

    @RequestMapping("/{id}")
    public Author author(@PathVariable String id) {

        Integer authorId;

        try{
            authorId = Integer.parseInt(id);
        } catch (Exception e) {
            return null;
        }

        return authorService.getAuthorById(authorId);
    }

    @PostMapping("")
    public Author addAuthor(@RequestBody Author author){
        authorService.addAuthorToList(author);
        return author;
    }

    @PutMapping("/{id}")
    public String editAuthor(@PathVariable String id, @RequestBody Author author) {

        Integer authorId;

        try{
            authorId = Integer.parseInt(id);
        } catch (Exception e) {
            return null;
        }

        Author authorToEdit = authorService.getAuthorById(authorId);

        authorService.editAuthorByOtherAuthor(author, authorToEdit);

        return "Author edited";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable String id) {

        Integer authorId;;

        try{
            authorId = Integer.parseInt(id);
        } catch (Exception e) {
            return null;
        }

        authorService.deleteAuthor(authorId);

        return "Author removed";
    }
}
