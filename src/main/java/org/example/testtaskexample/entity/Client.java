package org.example.testtaskexample.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    Long Id;

    String fullName;

    String maskedNumber;
}
