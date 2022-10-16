package com.peppermint.usermanagementsystem.infrastructure.exception;

public class PlatformInternalServerException extends AbstractPlatformException {

    public PlatformInternalServerException(String globalisationMessageCode, String defaultUserMessage, Object... defaultUserMessageArgs) {
        super(globalisationMessageCode, defaultUserMessage, defaultUserMessageArgs);
    }
}
