package com.designPatterns.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookJdbcDao implements CrudDAO<Book> {

    @Override
    public List<Book> findAll() {
        List<Book> bookList = new ArrayList<>();
        Connection connection = ConnectionManager.getConnectionInstance();
        try (Statement statement = connection.createStatement()) {
            String query = "SELECT * FROM book";
            ResultSet rs = statement.executeQuery(query);

            while (rs.next() == true) {
                Book book = new Book(
                        rs.getLong("id"),
                        rs.getString("name")
                );
                bookList.add(book);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bookList;
    }

    @Override
    public Optional<Book> findById(Long id) {
        String querySelectById = "SELECT * FROM book WHERE id=?";
        Connection connection = ConnectionManager.getConnectionInstance();

        try (PreparedStatement st = connection.prepareStatement(querySelectById)) {
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Book book = new Book(id, rs.getString("name"));
                return Optional.of(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) throws SQLException {
        String query = "DELETE FROM book WHERE id = ?";
        Connection connection = ConnectionManager.getConnectionInstance();
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            int numberRow = preparedStatement.executeUpdate();
            connection.commit();
            return numberRow > 0;
        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
            System.out.println("Something went wrong when deleting a book !");
        }
        return false;
    }

    @Override
    public Book update(Long id, String name) {
        try {
            String query = "UPDATE book SET name=? WHERE id=?";
            Connection connection = ConnectionManager.getConnectionInstance();
            PreparedStatement preparedStatement = ConnectionManager.getConnectionInstance().prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setLong(2, id);
            int rows = preparedStatement.executeUpdate();
            connection.commit();
            if(rows>0){
                Book book = new Book(id, name);
                return book;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Book create(Book element) {
        return null;
    }
}

