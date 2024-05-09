package com.server.InvestiMate.common.config.jwt.exception;

public class CustomJwtException extends RuntimeException {
    public CustomJwtException(String msg) {
        super(msg);
    }
}
