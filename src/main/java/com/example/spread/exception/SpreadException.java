package com.example.spread.exception;

public class SpreadException extends RuntimeException{

    public static String INVALID_RECIPIENT = "뿌리기 대상은 1명 이상이어야 합니다.";

    public SpreadException(String message){ super(message); }
}
