package com.bestapp.taskManagementSystem.config;

import com.bestapp.taskManagementSystem.dto.account.Role;
import com.bestapp.taskManagementSystem.security.JasonWebTokenAuthentication;
import io.jsonwebtoken.Claims;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JwtUtils {

    public static JasonWebTokenAuthentication calculate(Claims claims) {
        final JasonWebTokenAuthentication tokenInfo = new JasonWebTokenAuthentication();
        tokenInfo.setRoles(getRoles(claims));
        tokenInfo.setFirstName(claims.get("firstName", String.class));
        tokenInfo.setUsername(claims.getSubject());
        return tokenInfo;
    }

    private static Set<Role> getRoles(Claims claims) {
        final List<String> roles = claims.get("roles", List.class);
        return roles.stream()
                .map(Role::valueOf)
                .collect(Collectors.toSet());
    }
}
