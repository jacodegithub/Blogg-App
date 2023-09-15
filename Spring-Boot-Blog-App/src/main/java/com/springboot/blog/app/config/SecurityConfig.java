package com.springboot.blog.app.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth ->
                        {
                            auth.requestMatchers(HttpMethod.POST, "/blog/api/v1/**").hasAnyRole("ADMIN");
                            auth.requestMatchers(HttpMethod.GET, "/blog/api/v1/**").hasAnyRole("USER", "ADMIN");
                            auth.requestMatchers(HttpMethod.DELETE, "/blog/api/v1/**").hasAnyRole("ADMIN");
                            auth.requestMatchers(HttpMethod.PUT, "/blog/api/v1/**").hasAnyRole("ADMIN");
                            auth.requestMatchers(HttpMethod.POST, "/blog/api/v1/register").permitAll();
                            auth.anyRequest().authenticated();
                        })
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

//    @Bean
//    public UserDetailsService users() {
//
//        /*
//        * never use plain text for password, always pass it through password
//        * encoder, bcz spring doesn't take the plain text format for password
//        * always use password encoder.. Or throws BadCredentialException
//        * */
//        UserDetails user1 = User.builder()
//                .username("waqhar hussain")
//                .password(passwordEncoder().encode("waqhar"))
//                .roles("ADMIN")
//                .build();
//
//        UserDetails user2 = User.builder()
//                .username("tauqeer")
//                .password(passwordEncoder().encode("tauqeer"))
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(user1, user2);
//    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) {
        try {
            return authConfig.getAuthenticationManager();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
