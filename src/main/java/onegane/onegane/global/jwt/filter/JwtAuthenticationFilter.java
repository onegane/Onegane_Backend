package onegane.onegane.global.jwt.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import onegane.onegane.global.exception.domain.ApiErrorResult;
import onegane.onegane.global.jwt.dto.TokenFilterResponse;
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

        String accessToken = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            accessToken = authorizationHeader.split(" ")[1].trim();
        }

        if (accessToken != null) {
            TokenFilterResponse accessTokenResult = jwtProvider.isValid(accessToken);

            if (accessTokenResult != null && !accessTokenResult.getIsValid()) {
                response.getWriter().write(getErrorResponse(response, accessTokenResult.getMessage()));
                return;
            }

            Authentication authentication = jwtProvider.getAuthentication(accessToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String getErrorResponse(HttpServletResponse response, String message) throws JsonProcessingException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        return new ObjectMapper().writeValueAsString(
                ApiErrorResult.builder()
                        .status("JWT ERROR")
                        .message(message)
                        .build()
        );
    }
}