package com.designPatterns.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookMemoryDao implements CrudDAO<Book> {

    private static List<Book> bookList = new ArrayList(){{
        add(new Book(1L, "Exemple Livre 1"));
        add(new Book(2L, "Exemple Livre 2"));
    }};

    @Override
    public List<Book> findAll() {
        return bookList;
    }

    @Override
    public Optional<Book> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public Book update(Long id, String name) {
        return null;
    }

    @Override
    public Book create(Book element) {
        return null;
    }
}
