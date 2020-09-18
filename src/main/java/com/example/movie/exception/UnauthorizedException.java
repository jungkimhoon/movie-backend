package com.example.movie.exception;

public class UnauthorizedException extends RuntimeException{
    private static final long seralVersionUID = -2238030302650813813L;

    public UnauthorizedException(){
        super("계정이 유효하지 않습니다. \n다시 로그인 하세요.");
    }
}
