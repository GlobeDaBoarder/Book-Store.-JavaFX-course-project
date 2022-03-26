package utils;



import book_store.Book;
import book_store.eBookGenre;
import book_store.eBookLang;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DBoperations {
   /* public static Connection connectToDB() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String DB_URL = "jdbc:mysql://localhost:3306/bookshop_gui_sample";
            String USER = "root";
            String PASS = "";
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void disconnectFromDB(Connection connection, Statement statement) {
        try {
            if (connection != null) connection.close();
            if (statement != null) statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Book> getAllBooksFromDb() {
        ArrayList<Book> books = new ArrayList<>();
        Connection connection = DBoperations.connectToDB();
        String selectQuery = "SELECT b.* FROM books AS b";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectQuery);
            while (resultSet.next()) {
                books.add(new Book(resultSet.getInt(1), resultSet.getDouble(2),
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getString(5), resultSet.getDate(6).toLocalDate(), resultSet.getInt(7),
                        eBookLang.valueOf(resultSet.getString(8)), resultSet.getInt(9),
                        eBookGenre.valueOf(resultSet.getString(10))));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }*/
}
