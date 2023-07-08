package onegane.onegane.global.config.security;

import lombok.RequiredArgsConstructor;
import onegane.onegane.global.jwt.filter.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private final String[] authenticationList = {
            "/api/user/**",
            "/api/number",
            "/api/number/**",
            "/api/auth/logout"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().cors();

        http
                .authorizeHttpRequests()
                    .antMatchers("/api/auth/bsm").permitAll()
                    .antMatchers(authenticationList).hasRole("USER")
                    .anyRequest().authenticated();

        http
                .addFilterBefore(jwtAuthenticationFilter, OncePerRequestFilter.class);

        return http.build();
    }
}
