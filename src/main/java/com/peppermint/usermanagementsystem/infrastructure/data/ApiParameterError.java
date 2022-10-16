package com.peppermint.usermanagementsystem.infrastructure.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class ApiParameterError {
    /**
     * A developer friendly plain English description of why the HTTP error response was returned from the API.
     */
    @JsonProperty(value = "developerMessage")
    private final String developerMessage;

    /**
     * A user friendly plain English description of why the HTTP error response was returned from the API that can be
     * presented to end users.
     */
    @JsonProperty(value = "message")
    private final String defaultUserMessage;

    /**
     * A code that can be used for globalisation support by client applications of the API.
     */
    @JsonProperty(value = "code")
    private final String userMessageGlobalisationCode;
    /**
     * The actual value of the parameter (if any) as passed to API.
     */
    private final Object value;
    /**
     * Arguments related to the user error message.
     */
    private final List<ApiErrorMessageArg> args;
    /**
     * The name of the field or parameter passed to the API that this error relates to.
     */
    private String parameterName;

    private ApiParameterError(final String globalisationMessageCode, final String defaultUserMessage, final Object[] defaultUserMessageArgs,
                              String parameterName, String value) {
        this.userMessageGlobalisationCode = globalisationMessageCode;
        this.developerMessage = defaultUserMessage;
        this.defaultUserMessage = defaultUserMessage;
        this.parameterName = parameterName;
        this.value = value;

        final List<ApiErrorMessageArg> messageArgs = new ArrayList<>();
        if (defaultUserMessageArgs != null) {
            for (final Object object : defaultUserMessageArgs) {
                if (object instanceof Date) {
                    final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
                    final String formattedDate = dateFormatter.format(object);
                    messageArgs.add(ApiErrorMessageArg.from(formattedDate));
                } else {
                    messageArgs.add(ApiErrorMessageArg.from(object));
                }
            }
        }
        this.args = messageArgs;
    }

    public static ApiParameterError generalError(final String globalisationMessageCode, final String defaultUserMessage,
                                                 final Object... defaultUserMessageArgs) {
        return new ApiParameterError(globalisationMessageCode, defaultUserMessage, defaultUserMessageArgs, "id", null);
    }

    public static ApiParameterError resourceIdentifierNotFound(final String globalisationMessageCode, final String defaultUserMessage,
                                                               final Object... defaultUserMessageArgs) {
        return new ApiParameterError(globalisationMessageCode, defaultUserMessage, defaultUserMessageArgs, "id", null);
    }

    public static ApiParameterError parameterError(final String globalisationMessageCode, final String defaultUserMessage,
                                                   final String parameterName, final Object... defaultUserMessageArgs) {
        final ApiParameterError error = new ApiParameterError(globalisationMessageCode, defaultUserMessage, defaultUserMessageArgs,
                parameterName, null);
        return error;
    }

    public static ApiParameterError parameterErrorWithValue(final String globalisationMessageCode, final String defaultUserMessage,
                                                            final String parameterName, final String value, final Object... defaultUserMessageArgs) {
        final ApiParameterError error = new ApiParameterError(globalisationMessageCode, defaultUserMessage, defaultUserMessageArgs,
                parameterName, value);
        return error;
    }

    @Override
    public String toString() {
        return "ApiParameterError{developerMessage=" + developerMessage + "; ... }";
    }
}
