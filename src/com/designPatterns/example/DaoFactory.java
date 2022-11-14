package com.designPatterns.example;

public class DaoFactory {

    private DaoFactory() {
    }

    public static CrudDAO<Book> getBookDao() {
//        return new BookMemoryDao();
        return new BookJdbcDao();
    }
}
