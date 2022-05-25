package com.preflight.seleniumPlugin;

public class PreflightLogger {
    public void log(String s) {
        System.out.println("INFO: " + s);
    }
    public void debug(String s) {
        System.out.println("DEBUG: " + s);
    }
    public void error(String s, Exception e) {
        System.out.println("ERROR: " + s + " => " + e.toString());
    }

    public void error(String s) {
        System.out.println("ERROR: " + s);
    }

    public void warning(String s) {
        System.out.println("WARNING: " + s);
    }
}
