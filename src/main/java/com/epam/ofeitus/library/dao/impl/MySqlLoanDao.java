package com.epam.ofeitus.library.dao.impl;

import com.epam.ofeitus.library.dao.LoanDao;
import com.epam.ofeitus.library.entity.order.Loan;

import java.util.List;

public class MySqlLoanDao extends AbstractMySqlDao<Loan> implements LoanDao {
    @Override
    public int save(Loan entity) {
        // TODO
        return 0;
    }

    @Override
    public int update(Loan entity) {
        // TODO
        return 0;
    }

    @Override
    public int deleteById(int id) {
        // TODO
        return 0;
    }

    @Override
    public List<Loan> findByUserId(int userId) {
        // TODO
        return null;
    }

    @Override
    public List<Loan> findOverdueLoans() {
        // TODO
        return null;
    }
}
