package com.preflight.seleniumAutohealPlugin;

public class PreflightTestFailException extends PreflightException {
    PreflightTestFailException(String errorMessage) {
        super(errorMessage);
    }
}
