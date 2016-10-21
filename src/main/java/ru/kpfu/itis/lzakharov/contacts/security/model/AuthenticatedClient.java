package ru.kpfu.itis.lzakharov.contacts.security.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.kpfu.itis.lzakharov.contacts.model.Client;

import java.util.Collection;

public class AuthenticatedClient extends Client implements UserDetails {
    public AuthenticatedClient(Client client) {
        this.setId(client.getId());
        this.setUsername(client.getUsername());
        this.setPassword(client.getPassword());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
