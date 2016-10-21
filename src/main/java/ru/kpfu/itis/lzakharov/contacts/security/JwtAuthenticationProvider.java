package ru.kpfu.itis.lzakharov.contacts.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.lzakharov.contacts.model.Client;
import ru.kpfu.itis.lzakharov.contacts.security.exception.JwtTokenMalformedException;
import ru.kpfu.itis.lzakharov.contacts.security.model.AuthenticatedClient;
import ru.kpfu.itis.lzakharov.contacts.security.model.JwtAuthenticationToken;
import ru.kpfu.itis.lzakharov.contacts.security.transfer.JwtClientDto;
import ru.kpfu.itis.lzakharov.contacts.security.util.JwtUtil;

/**
 * Checks the token from the request and supply {@link UserDetails} if the token is valid
 */
@Component
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authentication;
        String token = jwtAuthenticationToken.getToken();

        JwtClientDto clientDto = JwtUtil.parseToken(token);

        if (clientDto == null) {
            throw new JwtTokenMalformedException("JWT token is not valid");
        }

        Client client = new Client();
        client.setId(clientDto.getId());
        client.setUsername(clientDto.getUsername());


        return new AuthenticatedClient(client);
    }
}
