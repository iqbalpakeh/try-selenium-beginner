package com.herokuapp.theinternet.util;

public class Util {

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void log(String msg) {
        System.out.println("[LOG] ------------------------------------------------------------------------");
        System.out.println("[LOG] " + msg);
        System.out.println("[LOG] ------------------------------------------------------------------------");
    }

}
