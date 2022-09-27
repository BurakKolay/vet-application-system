package burakkolay.vetproject.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(AuthenticationManagerBuilder auth)throws Exception{
        auth.inMemoryAuthentication()
                .withUser("user")
                .password("ege")
                .roles("USER")

        ;
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {

        http.headers().frameOptions().disable();

        http.csrf().disable()
                .formLogin()
                .loginPage("/login.html")
                .failureUrl("/login-error.html")
                .and()

        ;


    }


}
