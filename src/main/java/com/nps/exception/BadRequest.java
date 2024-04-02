package com.nps.exception;

import lombok.Data;

@Data
public class BadRequest extends RuntimeException {
    private String msg;

    public BadRequest(String msg) {
        this.msg = msg;
    }
}
