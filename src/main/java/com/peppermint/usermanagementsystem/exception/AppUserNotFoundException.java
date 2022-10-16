package com.peppermint.usermanagementsystem.exception;

import com.peppermint.usermanagementsystem.infrastructure.exception.AbstractPlatformResourceNotFoundException;

public class AppUserNotFoundException extends AbstractPlatformResourceNotFoundException {

    public AppUserNotFoundException(String globalisationMessageCode, String defaultUserMessage, Object... defaultUserMessageArgs) {
        super(globalisationMessageCode, defaultUserMessage, defaultUserMessageArgs);
    }

    public AppUserNotFoundException(String message) {
        super("AppUserNotFoundException", message);
    }


}
