package onegane.onegane.global.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    public final String[] authenticationList = {
            "/api/user/**",
            "/api/number",
            "/api/number/**"
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
                    .antMatchers("/api/user/login").permitAll()
                    .antMatchers(authenticationList).hasRole("USER")
                    .anyRequest().authenticated();

        return http.build();
    }
}
