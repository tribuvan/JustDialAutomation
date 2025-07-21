package com.cts.justdial.fwutils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommonUtils {
    public static void sureWait(int seconds){
        try{
            Thread.sleep(seconds*1000L);
        }catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
    public static String getCurrentDate(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-hh-mm-ss.SSS"));
    }
}
