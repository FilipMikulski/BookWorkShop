package pl.coderslab.model;

import pl.coderslab.service.DbService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao {

    public static void save(Author author){
        if(author.getId()==0){
            String query = "INSERT INTO Authors(firstName, lastName) VALUES (?,?)";
            List<String> params = new ArrayList<>();
            params.add(author.getFirstName());
            params.add(author.getLastName());

            try {
                Integer id = DbService.insertIntoDatabase(query,params);
                if(id!=null){
                    author.setId(id);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }else{
            String query = "UPDATE Authors SET firstName = ?, lastName = ? WHERE id = ?";
            List<String> params = new ArrayList<>();
            params.add(author.getFirstName());
            params.add(author.getLastName());
            params.add(String.valueOf(author.getId()));
            try{
                DbService.executeUpdate(query,params);

            }catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public static List<Author> loadAll(){
        String query = "SELECT * FROM Authors";
        return getAuthorsFromQuery(query,null);

    }

    public static Author loadById(Integer id){
        String query = "SELECT * FROM Authors WHERE id = ?";
        try{
            List<String> params = new ArrayList<>();
            params.add(String.valueOf(id));
            List<String[]> rows = DbService.getData(query,params);
            Author author = new Author();
            author.setId(Integer.parseInt(rows.get(0)[0]));
            author.setFirstName(rows.get(0)[1]);
            author.setLastName(rows.get(0)[2]);

            return author;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    private static List<Author> getAuthorsFromQuery(String query, List<String> params) {
        List<Author> authors = new ArrayList<>();
        try{
            List<String[]> rows = DbService.getData(query,params);
            for (String[] row: rows){
                Author author = new Author();
                author.setId(Integer.parseInt(row[0]));
                author.setFirstName(row[1]);
                author.setLastName(row[2]);
                authors.add(author);
            }
            return authors;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static void delete(Author author){
        String query = "DELETE FROM Authors WHERE id = ?";
        try{
            if(author.getId()!=0){
                List<String> params = new ArrayList<>();
                params.add(String.valueOf(author.getId()));
                DbService.executeUpdate(query,params);
                author.setId(0);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
