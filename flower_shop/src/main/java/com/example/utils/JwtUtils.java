package com.example.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

/**
 * @author comment
 * @date 2022/8/13 13:53
 */
public class JwtUtils {

    /**
     * 签名
     */
    private static final String SECRET = "!DAR$";

    /**
     * 获取token
     *
     * @param payload -user
     * @return token
     */
    public static String createToken(Map<String, String> payload) {
        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.SECOND, 10);

        JWTCreator.Builder builder = JWT.create();
        /*构建payload*/
        payload.forEach(builder::withClaim);
        /*指定过期时间和签名算法*/
        return builder.withExpiresAt(calendar.getTime()).sign(Algorithm.HMAC256(SECRET));
    }

    /**
     * 解析token
     *
     * @param token token字符串
     * @return 解析后的token
     */
    public static DecodedJWT decode(String token) {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        return jwtVerifier.verify(token);
    }

}