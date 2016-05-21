package org.xdevs23.debugutils;

import android.util.Log;

public class Logging {

    public static void logt(String msg) {
        logt("Debug", msg);
    }
    
    public static void logt(String tag, String msg) {
        Log.d(tag, msg);
    }

    public static void logt(int i) {
        logt(String.valueOf(i));
    }

    public static void logt(boolean c) {
        logt(String.valueOf(c));
    }

    public static void logt(long l) {
        logt(String.valueOf(l));
    }

    public static void logt(short s) {
        logt(String.valueOf(s));
    }

    public static void logt(char[] chars) {
        logt(String.copyValueOf(chars));
    }
    
    public static void logt(String tag, int i) {
        logt(tag, String.valueOf(i));
    }

    public static void logt(String tag, boolean c) {
        logt(tag, String.valueOf(c));
    }

    public static void logt(String tag, long l) {
        logt(tag, String.valueOf(l));
    }

    public static void logt(String tag, char[] chars) {
        logt(tag, String.copyValueOf(chars));
    }

    public static void logt(Object obj) {
        logt(String.valueOf(obj));
    }
    
    public static void logt(String tag, Object obj) {
        logt(tag, String.valueOf(obj));
    }

    public static void logd(String msg) {
        logt(msg);
    }

    public static void logd(Object obj) {
        logt(obj);
    }

}
