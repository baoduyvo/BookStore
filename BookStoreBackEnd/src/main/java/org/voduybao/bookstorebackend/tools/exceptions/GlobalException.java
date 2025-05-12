package org.voduybao.bookstorebackend.tools.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.voduybao.bookstorebackend.tools.exceptions.error.ResponseErrors;
import org.voduybao.bookstorebackend.tools.exceptions.error.ResponseException;
import org.voduybao.bookstorebackend.tools.response.http.Result;

@ControllerAdvice
@Slf4j
public class GlobalException {
    @ExceptionHandler(value = ResponseException.class)
    public Result handleResponseException(ResponseException ex) {
        if (ex.isCustom() == true) {
            log.warn("Custom exception: {}", ex.getMessage());
            return Result.failure(ex.getMessage(), ex.getCode(), HttpStatus.valueOf(ex.getStatus().value()));
        }

        ResponseErrors errors = ex.getErrors();
        log.warn("Predefined exception: {}", errors.getMessage());
        return Result.failure(errors);
    }

    @ExceptionHandler(value = Exception.class)
    public Result handleGenericException(Exception e) {
        log.error("Unhandled exception:", e);
        Result result = Result.failure(ResponseErrors.INTERNAL_SERVER_ERROR);
        return result;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleValidationException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();

        String mainMessage = bindingResult.hasErrors()
                ? bindingResult.getFieldError().getDefaultMessage()
                : "Validation error";

        Result response = Result.failure(
                mainMessage,
                ResponseErrors.BAD_REQUEST.getCode(),
                ResponseErrors.BAD_REQUEST.getStatus()
        );

        return response;
    }
}
