package org.example.testtaskexample.http.controller;

import lombok.RequiredArgsConstructor;
import org.example.testtaskexample.dto.ClientDto;
import org.example.testtaskexample.dto.NumberSearchDto;
import org.example.testtaskexample.service.CompanyService;
import org.example.testtaskexample.service.SearchService;
import org.example.testtaskexample.utils.SecurityUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;
    private final CompanyService companyService;
    private final SecurityUtils securityUtils;

    @PostMapping
    public ResponseEntity<ClientDto> searchClient(@RequestBody NumberSearchDto numberSearchDto) {
        Long companyId = securityUtils.getAuthenticatedAdminCompanyId();
        var company = companyService.findById(companyId);

        ClientDto result = searchService.findClientByNumber(numberSearchDto.getSearch(), company.getNumberMask());
        return ResponseEntity.ok(result);
    }

}
