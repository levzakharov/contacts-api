package ru.kpfu.itis.lzakharov.contacts.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.kpfu.itis.lzakharov.contacts.security.JwtAuthenticationEntryPoint;
import ru.kpfu.itis.lzakharov.contacts.security.JwtAuthenticationProvider;
import ru.kpfu.itis.lzakharov.contacts.security.JwtAuthenticationSuccessHandler;
import ru.kpfu.itis.lzakharov.contacts.security.JwtAuthenticationTokenFilter;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Autowired
    private JwtAuthenticationProvider authenticationProvider;

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return new ProviderManager(Arrays.asList(authenticationProvider));
    }

    public JwtAuthenticationTokenFilter authenticationTokenFilter() throws Exception {
        JwtAuthenticationTokenFilter authenticationTokenFilter = new JwtAuthenticationTokenFilter();

        authenticationTokenFilter.setAuthenticationManager(authenticationManagerBean());
        authenticationTokenFilter.setAuthenticationSuccessHandler(new JwtAuthenticationSuccessHandler());

        return authenticationTokenFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            // we don't need CSRF because our token is invulnerable
            .csrf().disable()
            .authorizeRequests()
                .anyRequest().permitAll()
//                .antMatchers("/api/**").authenticated()
            .and()
            // Call our errorHandler if authentication/authorisation fails
            .exceptionHandling()
                .authenticationEntryPoint(unauthorizedHandler)
            .and()
            // don't create session
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //.and()

        // add JWT based security filter
        http
            .addFilterBefore(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        // disable page caching
        http.headers().cacheControl();
    }
}
