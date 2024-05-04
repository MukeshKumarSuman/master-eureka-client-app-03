package com.nps.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@ResponseStatus(HttpStatus.CONFLICT)
public class Conflict extends RuntimeException {
    private String msg;

    public Conflict(String msg) {
        this.msg = msg;
    }
}
