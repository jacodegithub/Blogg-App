package com.springboot.blog.app.security;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private UserDetailsService userDetailsService;

    private JWTAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth ->
                        {
                            auth.requestMatchers(HttpMethod.POST, "/blog/api/v1/register").permitAll();
                            auth.requestMatchers(HttpMethod.POST, "/blog/api/v1/login").permitAll();
//                            auth.requestMatchers(HttpMethod.GET, "/blog/api/v1/**").hasAnyRole("ADMIN");
                            auth.requestMatchers(HttpMethod.GET, "/blog/api/v1/**").hasAnyRole("USER", "ADMIN");
                            auth.requestMatchers(HttpMethod.POST, "/blog/api/v1/**").hasAnyRole("ADMIN");
                            auth.requestMatchers(HttpMethod.DELETE, "/blog/api/v1/**").hasAnyRole("ADMIN");
                            auth.requestMatchers(HttpMethod.PUT, "/blog/api/v1/**").hasAnyRole("ADMIN");
                            auth.anyRequest().authenticated();
                        })
                .exceptionHandling(e -> e.authenticationEntryPoint(((request, response, authException) -> {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
                })))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

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

    @Bean
    public FilterRegistrationBean<CorsFilter> coresFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedOriginPattern("*");
        corsConfiguration.addAllowedHeader("Authorization");
        corsConfiguration.addAllowedHeader("Content-Type");
        corsConfiguration.addAllowedHeader("Accept");
        corsConfiguration.setAllowedMethods(List.of("POST", "GET", "DELETE", "PUT", "OPTIONS"));
        corsConfiguration.setMaxAge(3600L);

        source.registerCorsConfiguration("/**", corsConfiguration);

        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));

        bean.setOrder(-110);

        return bean;
    }

}
