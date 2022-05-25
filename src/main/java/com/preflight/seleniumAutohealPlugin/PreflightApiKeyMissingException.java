package com.preflight.seleniumAutohealPlugin;

public class PreflightApiKeyMissingException extends PreflightException {
    PreflightApiKeyMissingException(String errorMessage) {
        super(errorMessage);
    }
}
