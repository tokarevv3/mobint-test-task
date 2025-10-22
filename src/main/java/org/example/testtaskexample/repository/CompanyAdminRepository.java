package org.example.testtaskexample.repository;

import lombok.RequiredArgsConstructor;
import org.example.testtaskexample.entity.CompanyAdmin;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CompanyAdminRepository {

    private final JdbcClient jdbcClient;

    public Optional<CompanyAdmin> findByLogin(String login) {
        String sql = "select * from company_admin where login = ?";
        return jdbcClient.sql(sql)
                .param(login)
                .query(CompanyAdmin.class)
                .optional();
    }
}
