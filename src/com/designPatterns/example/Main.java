package com.designPatterns.example;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        CrudDAO<Book> bookDao = DaoFactory.getBookDao();
        System.out.println(bookDao.findAll());
        bookDao.update(2L, "lalalala");
        System.out.println(bookDao.findAll());

        // Closing connection
        ConnectionManager.closeConnection();
    }
}
