package br.ufsm.csi.gpsmanager.infra.security;

import br.ufsm.csi.gpsmanager.service.AutenticacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final AutenticacaoFilter autenticacaoFilter;
    private final AutenticacaoService service;


    //TODO: Implementar autenticação
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        //.requestMatchers(HttpMethod.GET, new String[] {"/", "/login", "/usuario/cadastrar"}).permitAll()
                        //.requestMatchers(HttpMethod.POST, new String[] {"/login", "/usuario/cadastrar"}).permitAll()
                        //.anyRequest().authenticated())
                        .anyRequest().permitAll());
        //.authenticationProvider(authenticationProvider())
                        //.addFilterBefore(autenticacaoFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();

        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return login -> {
            UserDetails usuario = service.loadUserByUsername(login);

            if (usuario == null) {
                throw new UsernameNotFoundException("Usuário não encontrado");
            } else {
                return User.withUsername(usuario.getUsername())
                        .password(usuario.getPassword())
                        //TODO: Implementar a permissão do usuário (remover o hardcode)
                        //.authorities(usuario.getAuthorities())
                        .build();
            }
        };
    }

    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}