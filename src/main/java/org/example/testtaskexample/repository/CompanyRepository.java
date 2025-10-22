package org.example.testtaskexample.repository;

import lombok.RequiredArgsConstructor;
import org.example.testtaskexample.entity.Company;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CompanyRepository {

    private final JdbcClient jdbcClient;

    public Company findCompanyById(Long id) {
        String sql = "SELECT * FROM company WHERE id = ?";
        return jdbcClient.sql(sql)
                .param(id)
                .query(Company.class)
                .single();
    }
}
