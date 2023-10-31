package com.iken.Labo.security;

import com.iken.Labo.service.UserDetailServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
    private UserDetailServiceImpl userDetailServiceImpl;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.formLogin(login->login
                .loginPage("/login" )
                .successHandler((request, response, authentication) -> {response.
                        sendRedirect("/dashboard");})
                .permitAll());
        http.logout(logout -> logout
                .logoutSuccessUrl("/login") // Spécifie l'URL de redirection après la déconnexion réussie
                .permitAll()
        );


        http.authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                .requestMatchers("/dashboard/**").hasAnyRole("ADMINISTRATEUR" ,"DIRECTEUR","ENSEIGNANT","DOCTORANT")
                .requestMatchers("/members/list/**").hasRole("ADMINISTRATEUR")
                .requestMatchers("/members/add/**").hasRole("ADMINISTRATEUR")
                .requestMatchers("/members/edit/**").hasRole("ADMINISTRATEUR")
                .requestMatchers("/projects/list/**").hasAnyRole("ADMINISTRATEUR" ,"DIRECTEUR","ENSEIGNANT","DOCTORANT")
                .requestMatchers("/projects/add/**").hasRole("ADMINISTRATEUR")
                .requestMatchers("/projects/edit/**").hasRole("ADMINISTRATEUR")
                .requestMatchers("/publications/list/**").hasAnyRole("ADMINISTRATEUR" ,"DIRECTEUR","ENSEIGNANT","DOCTORANT")
                .requestMatchers("/publications/add/**").hasAnyRole("ADMINISTRATEUR","ENSEIGNANT")
                .requestMatchers("/publications/edit/**").hasRole("ADMINISTRATEUR")
                .requestMatchers("/ressources/list/**").hasAnyRole("ADMINISTRATEUR" ,"DIRECTEUR","ENSEIGNANT","DOCTORANT")
                .requestMatchers("/ressources/add/**").hasRole("ADMINISTRATEUR")
                .requestMatchers("/ressources/edit/**").hasRole("ADMINISTRATEUR")

                );

        http.exceptionHandling().accessDeniedPage("/notAuthorized");
        http.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests.anyRequest().authenticated());
        http.userDetailsService(userDetailServiceImpl);

        return http.build();
    }


}