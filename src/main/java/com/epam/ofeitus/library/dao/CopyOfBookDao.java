package com.epam.ofeitus.library.dao;

import com.epam.ofeitus.library.dao.exception.DaoException;
import com.epam.ofeitus.library.entity.book.CopyOfBook;

import java.util.List;

public interface CopyOfBookDao extends AbstractDao<CopyOfBook> {
    List<CopyOfBook> findByIsbn(String bookIsbn) throws DaoException;
}
