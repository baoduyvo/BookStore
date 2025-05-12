package org.voduybao.bookstorebackend.tools.response.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.nimbusds.jose.shaded.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.voduybao.bookstorebackend.tools.exceptions.error.ErrorCode;
import org.voduybao.bookstorebackend.tools.exceptions.error.ResponseErrors;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
public class Result extends ResponseEntity<Result.Data> {

    @Setter
    @Getter
    @JsonSerialize
    static class Data {
        @com.nimbusds.jose.shaded.gson.annotations.SerializedName("time")
        private Date time;
        @SerializedName("code")
        private int code;
        @SerializedName("message")
        private String message;
        @SerializedName("data")
        private Object data;

        public Data(Date time, int code, String message, Object data) {
            this.time = time;
            this.code = code;
            this.message = message;
            this.data = data;
        }
    }

    private Result(Data data, int status) {
        super(data, HttpStatusCode.valueOf(status));
    }

    private static Result create(String message, int code, int status, Object data) {
        return new Result(
                new Data(
                        new Date(),
                        code,
                        message,
                        data
                ),
                status
        );
    }

    public static Result content(Object data) {
        return create("Success !!!", HttpStatus.OK.value(), HttpStatus.OK.value(), data);
    }

    public static Result success() {
        return create("Success !!!", HttpStatus.OK.value(), HttpStatus.OK.value(), null);
    }

    public static Result failure(ResponseErrors errors) {
        return create(errors.getMessage(), errors.getCode().value(), errors.getStatus().value(), null);
    }

    public static Result failure(String message, ErrorCode code, HttpStatus status) {
        return create(message, code.value(), status.value(), null);
    }

    public static Result failure(String message, int code, HttpStatus status) {
        return create(message, code, status.value(), null);
    }

    public int getStatus() {
        return getStatusCode().value();
    }

    public String toString() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(toModel());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String, Object> toModel() {
        Map<String, Object> result = new HashMap<>();
        result.put("code", getBody().code);
        result.put("message", getBody().message);
        result.put("data", getBody().data);
        result.put("status", getStatusCode().value());
        result.put("timestamp", getBody().time);
        return result;
    }
}
