package com.preflight.seleniumPlugin;

public class PreflightApiKeyMissingException extends PreflightException {
    PreflightApiKeyMissingException(String errorMessage) {
        super(errorMessage);
    }
}
