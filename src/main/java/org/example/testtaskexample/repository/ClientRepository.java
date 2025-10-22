package org.example.testtaskexample.repository;

import lombok.RequiredArgsConstructor;
import org.example.testtaskexample.entity.Client;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ClientRepository {

    private final JdbcClient jdbcClient;

    public Optional<Client> findClientByNumber(String maskedNumber) {
        String sql = "select * from client where masked_number = ?";
        return jdbcClient.sql(sql)
                .param(maskedNumber)
                .query(Client.class)
                .optional();
    }
}
