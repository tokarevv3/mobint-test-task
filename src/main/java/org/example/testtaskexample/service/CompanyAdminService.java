package org.example.testtaskexample.service;

import lombok.RequiredArgsConstructor;
import org.example.testtaskexample.entity.CompanyAdmin;
import org.example.testtaskexample.repository.CompanyAdminRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyAdminService implements UserDetailsService {

    private final CompanyAdminRepository companyAdminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return findByLogin(username)
                .map(user -> new org.springframework.security.core.userdetails.User(
                        user.getLogin(),
                        user.getPassword(),
                        Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"))
                )).orElseThrow(() -> new UsernameNotFoundException("Неправильный логин: " + username));
    }

    public Optional<CompanyAdmin> findByLogin(String login) {
        return companyAdminRepository.findByLogin(login);
    }
}
