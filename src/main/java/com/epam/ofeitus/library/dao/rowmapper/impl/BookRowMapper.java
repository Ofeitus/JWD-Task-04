package com.epam.ofeitus.library.dao.rowmapper.impl;

import com.epam.ofeitus.library.dao.rowmapper.RowMapper;
import com.epam.ofeitus.library.entity.book.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRowMapper implements RowMapper<Book> {
    @Override
    public Book map(ResultSet resultSet) throws SQLException {
        return null;
    }
}
