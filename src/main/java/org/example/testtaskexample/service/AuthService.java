package org.example.testtaskexample.service;

import lombok.RequiredArgsConstructor;
import org.example.testtaskexample.dto.JwtResponse;
import org.example.testtaskexample.dto.UserAuthDto;
import org.example.testtaskexample.provider.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtTokenProvider tokenProvider;
    private final CompanyAdminService companyAdminService;
    private final AuthenticationManager authenticationManager;

    public JwtResponse authUser(UserAuthDto userAuthDto) throws BadCredentialsException {
        if (userAuthDto.getLogin() == null || userAuthDto.getPassword() == null) {
            throw new IllegalArgumentException("Логин и пароль обязательны.");
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userAuthDto.getLogin(),
                        userAuthDto.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        var admin = companyAdminService.findByLogin(userAuthDto.getLogin());

        String jwt = tokenProvider.generateToken(admin.get());
        return new JwtResponse(jwt);
    }
}
