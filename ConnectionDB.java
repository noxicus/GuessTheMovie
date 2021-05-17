import java.sql.*;
import java.util.ArrayList;

public class ConnectionDB {
    private static final String url = "jdbc:mysql://localhost:3306/guessthemovie";
    private static final String user = "root";
    private static final String pass = "root";

    private ConnectionDB() {
    }

    /**
     * method that connect to MaSQL database and gets list of movies
     *
     * @return ArrayList of movies from table 'movie'
     */
    public static ArrayList<String> list() {
        ArrayList<String> movieList = new ArrayList();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, pass);

            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("select * from movie");

            while (resultSet.next()) {
                movieList.add(resultSet.getString(2));
            }
            connection.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Could not find database driver " + e.getMessage());
        } catch (SQLException throwables) {
            System.out.println("Could not connect to database " + throwables.getMessage());
        }
        return movieList;
    }

    /**
     * Method that enables user to add movie
     *
     * @param movie value that user provided to add into movie list
     */
    public static void add(String movie) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, pass);

            Statement stmt = connection.createStatement();
            int insertCount = stmt.executeUpdate("INSERT INTO movie (title) VALUES ('" + movie + "')");
            System.out.println("Number of row(s) affected: " + insertCount);
            connection.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Could not find database driver " + e.getMessage());
        } catch (SQLException throwables) {
            System.out.println("Could not connect to database " + throwables.getMessage());
        }
    }

    /**
     * Method that deletes movie title
     *
     * @param movie title to be deleted
     */
    public static void delete(String movie) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, pass);

            Statement stmt = connection.createStatement();
            int insertCount = stmt.executeUpdate("DELETE FROM movie WHERE title='" + movie + "'");
            System.out.println("Number of row(s) affected: " + insertCount);
            connection.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Could not find database driver " + e.getMessage());
        } catch (SQLException throwables) {
            System.out.println("Could not connect to database " + throwables.getMessage());
        }
    }
}
