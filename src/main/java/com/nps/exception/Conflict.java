package com.nps.exception;

import lombok.Data;

@Data
public class Conflict extends RuntimeException {
    private String msg;

    public Conflict(String msg) {
        this.msg = msg;
    }
}
