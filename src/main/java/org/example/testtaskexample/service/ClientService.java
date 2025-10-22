package org.example.testtaskexample.service;

import lombok.RequiredArgsConstructor;
import org.example.testtaskexample.entity.Client;
import org.example.testtaskexample.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public Optional<Client> findClientByNumber(String number) {
        return clientRepository.findClientByNumber(number);
    }
}
