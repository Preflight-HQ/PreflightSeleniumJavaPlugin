package com.preflight.seleniumPlugin;

public class PreflightTestFailException extends PreflightException {
    PreflightTestFailException(String errorMessage) {
        super(errorMessage);
    }
}
