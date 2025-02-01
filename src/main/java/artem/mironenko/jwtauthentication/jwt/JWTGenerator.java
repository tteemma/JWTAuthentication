package artem.mironenko.jwtauthentication.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;



import java.util.Date;

import static artem.mironenko.jwtauthentication.security.Constants.JWT_EXPIRATION;
import static artem.mironenko.jwtauthentication.security.Constants.JWT_SECRET;

@Component
public class JWTGenerator {

    public String generateJWT(Authentication authentication) {
        String username = authentication.getName();
        Date now = new Date();
        Date expiration = new Date(now.getTime() + JWT_EXPIRATION);

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expiration) // 1 день
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();

        return token;
    }
    public String getUsernameFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validateJWTToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(JWT_SECRET)
                    .parseClaimsJws(token);
            return true;
        }
        catch (Exception e) {
            throw new AuthenticationCredentialsNotFoundException("Invalid JWT token");
        }
    }
}
