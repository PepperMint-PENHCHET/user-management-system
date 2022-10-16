package com.peppermint.usermanagementsystem.infrastructure.exception;

public abstract class AbstractPlatformServiceUnavailableException extends AbstractPlatformException {

    protected AbstractPlatformServiceUnavailableException(String globalisationMessageCode, String defaultUserMessage,
                                                          Object... defaultUserMessageArgs) {
        super(globalisationMessageCode, defaultUserMessage, defaultUserMessageArgs);
    }
}
