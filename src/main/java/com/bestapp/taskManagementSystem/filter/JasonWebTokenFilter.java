package com.bestapp.taskManagementSystem.filter;

import com.bestapp.taskManagementSystem.config.JwtUtils;
import com.bestapp.taskManagementSystem.dto.account.JasonWebTokenProvider;
import com.bestapp.taskManagementSystem.security.JasonWebTokenAuthentication;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JasonWebTokenFilter extends GenericFilterBean {

    private static final String AUTHORIZATION = "Authorization";

    private final JasonWebTokenProvider jasonWebTokenProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        final String token = getTokenFromRequest((HttpServletRequest) request);
        if (token != null && jasonWebTokenProvider.validateActualToken(token)) {
            final Claims claims = jasonWebTokenProvider.getActualClaims(token);
            final JasonWebTokenAuthentication jwtInfoToken = JwtUtils.calculate(claims);
            jwtInfoToken.setAuthenticated(true);
            SecurityContextHolder.getContext().setAuthentication(jwtInfoToken);
        }
        chain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        final String bearer = request.getHeader(AUTHORIZATION);
        if (StringUtils.hasText(bearer) && bearer.startsWith("Bearer ")) {
            return bearer.substring(7);
        }
        return null;
    }
}
