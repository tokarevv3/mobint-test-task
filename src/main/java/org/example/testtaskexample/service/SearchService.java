package org.example.testtaskexample.service;

import lombok.RequiredArgsConstructor;
import org.example.testtaskexample.dto.ClientDto;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final ClientService clientService;

    public ClientDto findClientByNumber(String number, String numberMask) {
        String maskedNumber = maskNumber(number, numberMask);

        return clientService.findClientByNumber(maskedNumber)
                .map(client -> new ClientDto(client.getFullName(), client.getMaskedNumber()))
                .orElseThrow(() -> new IllegalArgumentException("Клиент с данным номером не найден"));
    }


    private String maskNumber(String number, String mask) {
        if (!isNumberValid(number, mask)) {
            throw new IllegalArgumentException("Неверный формат номера: " + number);
        }

        StringBuilder result = new StringBuilder();
        int index = 0;

        for (char c : mask.toCharArray()) {
            if (c == '*') {
                result.append(number.charAt(index++));
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }


    private boolean isNumberValid(String number, String mask) {
        int maskCount = mask.length() - mask.replace("*", "").length();
        return number.length() == maskCount;
    }
}
