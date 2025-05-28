package com.example.demo.config;

import com.example.demo.exception.InvalidInputException;
import com.example.demo.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException; // 修正: 应该是 io.jsonwebtoken.security.SignatureException 如果使用jjwt 0.12.x, 或者 io.jsonwebtoken.SignatureException for 0.11.x
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;
        String role = null;

        // 1. 检查请求头是否存在 "Authorization"
        if (authorizationHeader != null && !authorizationHeader.isEmpty()) {
            // 直接将整个 authorizationHeader 作为 JWT
            jwt = authorizationHeader;
            try {
                username = jwtUtil.extractUsername(jwt);
                role = jwtUtil.extractRole(jwt);

                if (username != null && role != null && jwtUtil.validateToken(jwt, username)) {
                    request.setAttribute("usernameFromJwt", username);
                    request.setAttribute("roleFromJwt", role);
                } else {
                    throw new InvalidInputException("JWT参数错误");
                }

            } catch (ExpiredJwtException e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Token has expired");
                return;
            } catch (SignatureException e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Invalid token signature");
                return;
            } catch (MalformedJwtException e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Malformed token");
                return;
            } catch (UnsupportedJwtException e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Unsupported token");
                return;
            } catch (IllegalArgumentException e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Invalid token");
                return;
            } catch (Exception e) { // 捕获所有其他可能的JWT处理异常
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("Error processing token");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}