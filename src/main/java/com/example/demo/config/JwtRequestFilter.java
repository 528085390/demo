package com.example.demo.config;

import com.example.demo.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter; // Spring提供的基础过滤器类

import java.io.IOException;

@Component // 将这个过滤器声明为一个Spring Bean，让Spring管理它
public class JwtRequestFilter extends OncePerRequestFilter { // 继承OncePerRequestFilter确保每个请求只执行一次此过滤器

    @Autowired
    private JwtUtil jwtUtil; // 注入我们之前创建的JwtUtil


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;
        String role = null; // 我们也从token中提取角色

        // 1. 检查请求头是否存在 "Authorization" 并且以 "Bearer " 开头
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7); // 提取 "Bearer " 后面的JWT字符串
            try {
                username = jwtUtil.extractUsername(jwt); // 从JWT中提取用户名
                role = jwtUtil.extractRole(jwt);     // 从JWT中提取角色

                // 简单的做法是，如果能成功提取用户名和角色，我们就认为token基本有效（签名和未过期）
                // 我们可以将解析出的用户信息存入request的attribute中，供后续Controller使用（如果需要）
                if (username != null && role != null && !jwtUtil.validateToken(jwt, username)) {
                    // 如果validateToken返回false (例如token过期，或者用户名不匹配--虽然我们这里只用token里的username)
                    System.out.println("JWT Token is invalid or expired");
                    // 可以选择直接返回401或403，或者什么都不做让请求继续（取决于后续是否有其他安全层）
                    // 为了简单起见，我们先不在这里直接拦截，除非是明显的解析错误
                    // response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    // return;
                    username = null; // 标记为无效
                    role = null;
                } else if (username != null && role != null) {
                    System.out.println("JWT Token validated for user: " + username + " with role: " + role);
                    // 将用户信息放入请求属性中，以便后续Controller可以访问（如果需要）
                    request.setAttribute("usernameFromJwt", username);
                    request.setAttribute("roleFromJwt", role);
                }

            } catch (ExpiredJwtException e) {
                System.out.println("JWT Token has expired: " + e.getMessage());
                // 对于过期的token，我们应该阻止请求
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Token has expired");
                return; // 不再继续处理请求
            } catch (SignatureException e) {
                System.out.println("JWT Signature validation failed: " + e.getMessage());
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Invalid token signature");
                return;
            } catch (MalformedJwtException e) {
                System.out.println("JWT Token is malformed: " + e.getMessage());
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Malformed token");
                return;
            } catch (UnsupportedJwtException e) {
                System.out.println("JWT Token is unsupported: " + e.getMessage());
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Unsupported token");
                return;
            } catch (IllegalArgumentException e) {
                System.out.println("JWT claims string is empty: " + e.getMessage());
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Invalid token");
                return;
            } catch (Exception e) {
                System.out.println("Error processing JWT Token: " + e.getMessage());
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("Error processing token");
                return;
            }
        } else {
            System.out.println("Authorization header does not exist or does not start with Bearer String");
        }

        // 2. 让请求继续沿着过滤器链向下传递
        // 无论token是否存在或是否有效（除非我们上面已经return了），我们都让请求继续
        // 后续Controller或其他过滤器可以根据request中设置的attribute来做进一步判断
        filterChain.doFilter(request, response);
    }
}