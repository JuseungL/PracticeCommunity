package com.server.InvestiMate.common.config.jwt;

public class JwtConstants {
    public static final String secret = "${spring.jwt.secret}";
    public static final int ACCESS_EXP_TIME = 60;
    public static final int REFRESH_EXP_TIME = 60*24;
    public static final String JWT_HEADER = "Authorization";
    public static final String JWT_TYPE = "Bearer ";
}
