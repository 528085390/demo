package com.example.demo.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component // 让Spring管理这个类
public class JwtUtil {

    // 1. 定义一个密钥 (用于签名JWT，非常重要，后续可以移到配置文件)
    private SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    // 2. 定义Token的过期时间 (例如：1小时，单位是毫秒)
    private long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour

    // --- 生成Token的方法 ---
    public String generateToken(String username, String role) {
        Map<String, Object> claims = new HashMap<>();
        // 你可以在claims中放入任何你想包含在token中的非敏感信息
        claims.put("role", role); // 例如，用户的角色

        return Jwts.builder()
                .setClaims(claims) // 设置自定义声明
                .setSubject(username) // 设置主题，通常是用户名
                .setIssuedAt(new Date(System.currentTimeMillis())) // 设置签发时间
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // 设置过期时间
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY) // 使用HS512算法和密钥进行签名
                .compact(); // 生成JWT字符串
    }

    // --- 从Token中提取信息的方法 ---

    // 从Token中提取用户名
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // 从Token中提取角色 (我们之前在claims中放了role)
    public String extractRole(String token) {
        final Claims claims = extractAllClaims(token);
        return (String) claims.get("role");
    }

    // 从Token中提取过期时间
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // 提取单个声明的通用方法
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // 提取Token中的所有声明
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    // --- 验证Token的方法 ---

    // 检查Token是否过期
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // 验证Token是否有效 (用户名匹配且未过期)
    public Boolean validateToken(String token, String usernameFromUserDetails) {
        final String usernameInToken = extractUsername(token);
        return (usernameInToken.equals(usernameFromUserDetails) && !isTokenExpired(token));
    }
}