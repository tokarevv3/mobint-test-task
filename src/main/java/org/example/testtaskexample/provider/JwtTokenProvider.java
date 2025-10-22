package org.example.testtaskexample.provider;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.example.testtaskexample.entity.CompanyAdmin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
@RequiredArgsConstructor
@PropertySource("classpath:application.properties")
public class JwtTokenProvider {


    @Value("${app.token.secret}")
    private String JWT_SECRET;  // Секретный ключ
    @Value("${app.token.expiration}")
    private long JWT_EXPIRATION_MS; // 1 час

    public String generateToken(CompanyAdmin admin) {
        String username = admin.getLogin();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION_MS);

        return Jwts.builder()
                .setSubject(username)  // Логин администратора в качестве субъекта
                .claim("company_id", admin.getCompanyId()) // Компания администратора
                .setIssuedAt(now)  // Дата выпуска токена
                .setExpiration(expiryDate)  // Дата истечения срока действия токена
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    // Метод для извлечения имени пользователя из токена
    public String getUsernameFromJWT(String token) {
        return Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Метод для валидации токена
    public boolean validateToken(String authToken) {
        Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(authToken);
        return true;  // Токен валидный
    }

    // Метод для извлечения id компании
    public Long getCompanyFromJWT(String token) {
        return ((Number) Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody()
                .get("company_id")).longValue();
    }
}