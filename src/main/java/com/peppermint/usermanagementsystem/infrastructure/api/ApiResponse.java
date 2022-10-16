package com.peppermint.usermanagementsystem.infrastructure.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@Builder
@ToString
public class ApiResponse<T> {

    private String code;

    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Pagination pagination;

    public ApiResponse() {
        this.code = "0000";
        this.message = "OK";
    }

    public ApiResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ApiResponse(T data) {
        this.code = "0000";
        this.message = "OK";
        this.data = data;
    }

    public ApiResponse(T data, Pagination pagination) {
        this.code = "0000";
        this.message = "OK";
        this.data = data;
        this.pagination = pagination;
    }

    public ApiResponse(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ApiResponse(String code, T data) {
        this.code = code;
        this.data = data;
    }
}
