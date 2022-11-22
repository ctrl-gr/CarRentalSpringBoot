package com.carrentalspringboot.security;


import lombok.Builder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Builder

public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final AuthEntryPoint jwtAuthenticationEntryPoint;
    private final UserDetailsService jwtUserDetailsService;
    private final RequestFilter jwtRequestFilter;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    private static final String[] ADMIN_MATCHER =
            {
                    "/users/all/**",
                    "/users/edit/**",
                    "/users/delete/**",
                    "/cars/all/**",
                    "/cars/edit/**",
                    "/cars/delete/**",
                    "/bookings/approve/**",
                    "/bookings/all/**",
                    "/bookings/save/**"
            };

    private static final String[] USER_MATCHER =
            {
                    "/cars/get-available-cars/**",
                    "/bookings/my-bookings/{userId}/**",
                    "/bookings/save/**"
            };

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // We don't need CSRF for this example. But it's dangerous, remove it
        httpSecurity.csrf().disable()
                .authorizeRequests()
                .antMatchers("/authenticate", "/register").permitAll() //TODO
                .antMatchers(ADMIN_MATCHER).access("hasRole('ADMIN')")
                .antMatchers(USER_MATCHER).access("hasRole('USER')")
                .anyRequest().authenticated().and().
                        exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }


    /*public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**").allowedMethods("POST");

    }

     */



}