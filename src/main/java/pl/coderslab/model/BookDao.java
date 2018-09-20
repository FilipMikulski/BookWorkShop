package pl.coderslab.model;

import pl.coderslab.service.DbService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
    public static void save(Book book){
        if(book.getId()==0){
            String query = "INSERT INTO Books(isbn, title, author_id, publisher, type) VALUES (?,?,?,?,?)";
            List<String> params = new ArrayList<>();
            params.add(book.getIsbn());
            params.add(book.getTitle());
            params.add(String.valueOf(book.getAuthor().getId()));
            params.add(book.getPublisher());
            params.add(book.getType());

            try {
                Long id = Long.valueOf(DbService.insertIntoDatabase(query,params));
                if(id!=null){
                    book.setId(id);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }else{
            String query = "UPDATE Books SET isbn = ?, title = ?, author_id = ?, publisher = ?, type = ? WHERE id = ?";
            List<String> params = new ArrayList<>();
            params.add(book.getIsbn());
            params.add(book.getTitle());
            params.add(String.valueOf(book.getAuthor().getId()));
            params.add(book.getPublisher());
            params.add(book.getType());
            params.add(String.valueOf(book.getId()));
            try{
                DbService.executeUpdate(query,params);

            }catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public static List<Book> loadAll(){
        String query = "SELECT * FROM Books";
        return getBooksFromQuery(query,null);

    }

    public static Book loadById(Long id){
        String query = "SELECT * FROM Books WHERE id = ?";
        try{
            List<String> params = new ArrayList<>();
            params.add(String.valueOf(id));
            List<String[]> rows = DbService.getData(query,params);
            Book book = new Book();
            book.setId(Long.parseLong(rows.get(0)[0]));
            book.setIsbn(rows.get(0)[1]);
            book.setTitle(rows.get(0)[2]);
            book.setAuthor2(AuthorDao.loadById(Integer.parseInt(rows.get(0)[3])));
            book.setPublisher(rows.get(0)[4]);
            book.setType(rows.get(0)[5]);

            return book;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    private static List<Book> getBooksFromQuery(String query, List<String> params) {
        List<Book> books = new ArrayList<>();
        try{
            List<String[]> rows = DbService.getData(query,params);
            for (String[] row: rows){
                Book book = new Book();
                book.setId(Long.parseLong(row[0]));
                book.setIsbn(row[1]);
                book.setTitle(row[2]);
                book.setAuthor2(AuthorDao.loadById(Integer.parseInt(row[3])));
                book.setPublisher(row[4]);
                book.setType(row[5]);
                books.add(book);
            }
            return books;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static void delete(Book book){
        String query = "DELETE FROM Books WHERE id = ?";
        try{
            if(book.getId()!=0){
                List<String> params = new ArrayList<>();
                params.add(String.valueOf(book.getId()));
                DbService.executeUpdate(query,params);
                book.setId(0L);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
