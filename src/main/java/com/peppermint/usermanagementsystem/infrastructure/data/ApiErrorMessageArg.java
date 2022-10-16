package com.peppermint.usermanagementsystem.infrastructure.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ApiErrorMessageArg {
    /**
     * The actual value of the parameter (if any) as passed to API.
     */
    private Object value;

    public static ApiErrorMessageArg from(final Object object) {
        return new ApiErrorMessageArg(object);
    }

}
