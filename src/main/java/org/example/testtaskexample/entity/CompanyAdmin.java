package org.example.testtaskexample.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyAdmin {

    Long id;

    String login;

    String password;

    Long companyId;
}
