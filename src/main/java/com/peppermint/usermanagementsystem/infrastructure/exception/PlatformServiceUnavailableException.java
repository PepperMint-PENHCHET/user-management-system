package com.peppermint.usermanagementsystem.infrastructure.exception;


public class PlatformServiceUnavailableException extends AbstractPlatformServiceUnavailableException {

    public PlatformServiceUnavailableException(String globalisationMessageCode, String defaultUserMessage,
                                               Object... defaultUserMessageArgs) {
        super(globalisationMessageCode, defaultUserMessage, defaultUserMessageArgs);
    }
}
