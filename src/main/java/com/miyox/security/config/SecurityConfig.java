package com.miyox.security.config;

import com.miyox.security.config.handle.MiyoxAccessDeniedHandler;
import com.miyox.security.config.handle.MiyoxAuthenticationFailureHandler;
import com.miyox.security.config.handle.MiyoxAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import sun.plugin2.message.Message;

import java.util.Locale;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final MiyoxAuthenticationSuccessHandler authenticationSuccessHandler;
    private final MiyoxAuthenticationFailureHandler authenticationFailureHandler;
    private final MiyoxAccessDeniedHandler accessDeniedHandler;

    @Autowired
    public SecurityConfig(MiyoxAuthenticationSuccessHandler authenticationSuccessHandler, MiyoxAuthenticationFailureHandler authenticationFailureHandler, MiyoxAccessDeniedHandler accessDeniedHandler) {
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.authenticationFailureHandler = authenticationFailureHandler;
        this.accessDeniedHandler = accessDeniedHandler;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        UserDetails userDetails = User.withUsername("miyox")
                .password(passwordEncoder.encode("123456")).roles("USER").build();
        System.out.println(userDetails.getPassword());
        auth.inMemoryAuthentication().withUser(userDetails);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .exceptionHandling().accessDeniedHandler(accessDeniedHandler)
            .and()
            .authorizeRequests()
                .antMatchers("/css/**", "/img/**", "/js/**").permitAll()
                .antMatchers("/user/**").hasRole("USER")
                .anyRequest().authenticated()
            .and()
            .formLogin()
                .loginPage("/login")
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .permitAll();

    }

}
