package com.peppermint.usermanagementsystem.exception;

import com.peppermint.usermanagementsystem.infrastructure.data.ApiGlobalErrorResponse;
import com.peppermint.usermanagementsystem.infrastructure.data.ApiParameterError;
import com.peppermint.usermanagementsystem.infrastructure.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalRestControllerAdvice {

    @ExceptionHandler(value = {AbstractPlatformException.class})
    public ResponseEntity<Object> handleAbstractPlatformException(AbstractPlatformException exception) {
        return new ResponseEntity<>(ApiGlobalErrorResponse.serverSideError(exception.getGlobalisationMessageCode(), exception.getDefaultUserMessage(), exception.getDefaultUserMessageArgs()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {AbstractPlatformResourceNotFoundException.class})
    public ResponseEntity<Object> handleAbstractPlatformResourceNotFoundException(AbstractPlatformResourceNotFoundException exception) {
        return new ResponseEntity<>(ApiGlobalErrorResponse.notFound(exception.getGlobalisationMessageCode(), exception.getDefaultUserMessage(), exception.getDefaultUserMessageArgs()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {PlatformApiDataValidationException.class})
    public ResponseEntity<Object> handlePlatformApiDataValidationException(PlatformApiDataValidationException exception) {
        return new ResponseEntity<>(ApiGlobalErrorResponse.badClientRequest(exception.getGlobalisationMessageCode(), exception.getDefaultUserMessage(), exception.getErrors()), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    protected ResponseEntity<ApiGlobalErrorResponse> handleInvalidRequest(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        final List<ApiParameterError> dataValidationErrors = new ArrayList<>();
        for (FieldError fieldError : fieldErrors) {
            final ApiParameterError error = ApiParameterError.parameterErrorWithValue(
                    fieldError.getCode().toLowerCase() + "." + fieldError.getObjectName().toLowerCase() + "." + fieldError.getField().toLowerCase(),
                    fieldError.getDefaultMessage(),
                    fieldError.getField(),
                    fieldError.getRejectedValue() + "",
                    fieldError.getObjectName());
            dataValidationErrors.add(error);
        }
        return new ResponseEntity<>(ApiGlobalErrorResponse.badClientRequest("InvalidRequest", "Request Validation Failed", dataValidationErrors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {PlatformDataIntegrityException.class})
    public ResponseEntity<Object> handlePlatformDataIntegrityException(PlatformDataIntegrityException exception) {
        return new ResponseEntity<>(ApiGlobalErrorResponse.dataIntegrityError(exception.getGlobalisationMessageCode(), exception.getDefaultUserMessage(), exception.getParameterName()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {PlatformInternalServerException.class})
    public ResponseEntity<Object> handlePlatformDataIntegrityException(PlatformInternalServerException exception) {
        return new ResponseEntity<>(ApiGlobalErrorResponse.serverSideError(exception.getGlobalisationMessageCode(), exception.getDefaultUserMessage(), exception.getDefaultUserMessageArgs()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
