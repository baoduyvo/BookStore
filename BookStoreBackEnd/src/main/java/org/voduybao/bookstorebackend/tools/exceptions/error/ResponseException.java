package org.voduybao.bookstorebackend.tools.exceptions.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ResponseException  extends RuntimeException{
    private ResponseErrors errors;

    private boolean isCustom = false;
    private ErrorCode code;
    private HttpStatus status;
    private String message;

    public ResponseException(ErrorCode code,  String message, HttpStatus status) {
        super(message);
        this.code = code;
        this.status = status;
        this.message = message;
        this.isCustom = true;
    }

    public ResponseException(ResponseErrors errors) {
        super(errors.getMessage());
        this.errors = errors;
    }

    public ResponseException(ResponseErrors errors, String msg) {
        super(msg);
        this.errors = errors;
    }

    public ResponseException(ResponseErrors errors, String msg, Throwable cause) {
        super(errors.getMessage(), cause);
        this.errors = errors;
    }


}