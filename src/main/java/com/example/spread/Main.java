package com.example.spread;

import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Date date = new Date();
        DateFormat format = DateFormat.getInstance();
        String format1 = format.format(date);
        System.out.println("format1 = " + format1);

    }
}
