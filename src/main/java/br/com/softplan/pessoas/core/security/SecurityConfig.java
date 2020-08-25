package br.com.softplan.pessoas.core.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/**").authorizeRequests()
                //.antMatchers("/**").permitAll()
                //.antMatchers("/source/**").permitAll()
                .anyRequest().permitAll()
                .and().csrf().disable()
                .oauth2Login();
    }
}