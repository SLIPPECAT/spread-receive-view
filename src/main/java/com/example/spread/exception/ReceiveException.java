package com.example.spread.exception;

public class ReceiveException extends RuntimeException{

    public static String INVALID_TOKEN = "유효하지 않은 토큰입니다.";
    public static String MISMATCHED_ROOM = "뿌린 대화방과 다른 대화방입니다.";
    public static String INVALID_TIME = "뿌린 건에 대한 받기는 10분간만 유효합니다.";
    public static String NOT_RECIPIENT = "자신이 뿌리기 한 건은 받을 수 없습니다.";
    public static String ALREADY_RECEIVE = "이미 뿌리기를 받았습니다.";


    public ReceiveException(String message){ super(message); }

}