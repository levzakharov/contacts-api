package ru.kpfu.itis.lzakharov.contacts.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import ru.kpfu.itis.lzakharov.contacts.model.Client;
import ru.kpfu.itis.lzakharov.contacts.security.transfer.JwtClientDto;

import java.util.Date;

public class JwtUtil {
    // FIXME: Generate secret key
    public static String secret = "secret";

    public static String generateToken(Client client) {
        Claims claims = Jwts.claims().setSubject(client.getUsername());
        claims.put("id", Long.toString(client.getId()));

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .setIssuedAt(new Date())
                .compact();
    }

    public static JwtClientDto parseToken(String token) {
        JwtClientDto clientDto = null;

        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            clientDto = new JwtClientDto();
            clientDto.setId(Long.parseLong((String) body.get("id")));
            clientDto.setUsername(body.getSubject());
        } catch (SignatureException e) {
            // FIXME: handle exception
            e.printStackTrace();
        }

        return clientDto;
    }
}
