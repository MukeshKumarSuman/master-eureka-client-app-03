package com.nps.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequest extends RuntimeException {
    private String msg;

    public BadRequest(String msg) {
        this.msg = msg;
    }
}
