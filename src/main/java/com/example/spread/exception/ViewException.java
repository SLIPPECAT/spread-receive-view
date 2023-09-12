package com.example.spread.exception;

public class ViewException extends RuntimeException{

    public static String INVALID_TOKEN = "토큰값이 올바르지 않습니다.";
    public static String INVALID_DATE = "7일 이내 건에 대해서만 조회 가능합니다.";
    public static String NOT_ACCESSED = "뿌린 건에 대해 조회할 수 있는 권한이 없습니다.";

    public ViewException(String message){ super(message); }

}
