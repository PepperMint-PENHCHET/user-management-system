package com.peppermint.usermanagementsystem.infrastructure.exception;

public interface ErrorCode {
    String getCode();

    String getValue();

    String getDefaultMessage();
}

