package com.github.face.login;

import com.alibaba.fastjson2.JSON;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wo
 */
@Component
public class JwtUtil {
    private static final long TOKEN_EXPIRATION = 1000 * 60 * 15;
    private static final String SIGN_KEY = "123qwe!@#QWE";

    /**
     * 生成token,根据userId生成token
     */
    public static String createToken(Long userId) {
        Map<String, Object> claim = new HashMap<>();
        claim.put("userId", userId);
        // 创建一个JWT生成器，并配置相关信息
        return Jwts.builder()
                .setIssuer("")
                .setSubject("FACE-USER")// 设置JWT主题（通常是令牌的用途描述）
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION))//设置JWT的过期时间
                .addClaims(claim)//
                .signWith(SignatureAlgorithm.HS256, encodeSecret())// 使用HS512算法进行签名，并提供签名密钥
                .compressWith(CompressionCodecs.GZIP) // 使用GZIP进行压缩（可选）
                .compact();
    }

    /**
     * 加密算法
     */
    private static SecretKey encodeSecret() {
        byte[] encode = Base64.getEncoder().encode(SIGN_KEY.getBytes());
        return new SecretKeySpec(encode, 0, encode.length, "AES");
    }

    /**
     * token 解密
     */
    public Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(encodeSecret())
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * token 解密，并返回一个对象，可是User对象
     */
    public <T> T parseToken(String token, Class<T> clazz) {
        Claims body = Jwts.parser()
                .setSigningKey(encodeSecret())
                .parseClaimsJws(token)
                .getBody();
        return JSON.parseObject(body.getSubject(), clazz);
    }

}
