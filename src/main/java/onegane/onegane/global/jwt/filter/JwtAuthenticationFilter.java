package onegane.onegane.global.jwt.filter;


import lombok.RequiredArgsConstructor;
import onegane.onegane.global.jwt.util.JwtProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String authorizationHeader = request.getHeader("Authorization");
        String authorizationRefreshHeader = request.getHeader("Authorization-Refresh");

        String accessToken = null;
        String refreshToken = null;

        if (authorizationRefreshHeader != null && authorizationRefreshHeader.startsWith("Bearer ")) {
            refreshToken = authorizationRefreshHeader.split(" ")[1].trim();
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                accessToken = authorizationHeader.split(" ")[1].trim();
            }
        }

        if (refreshToken != null && jwtProvider.isValid(refreshToken, response)) {
            if (accessToken != null && jwtProvider.isValid(accessToken, response)) {
                Authentication authentication = jwtProvider.getAuthentication(accessToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }
}