package com.nps.controller;

import com.nps.dto.ErrorResponse;
import com.nps.exception.BadRequest;
import com.nps.exception.Conflict;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ExceptionHandler {
    private static  final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    @org.springframework.web.bind.annotation.ExceptionHandler(BadRequest.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBadRequest(BadRequest ex) {
        logger.error("Exception occurred while processing payment {}", ex);
        return new ErrorResponse(String.valueOf(HttpStatus.BAD_REQUEST.value()), "BadRequest", ex.getMsg());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Conflict.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleConflict(Conflict ex) {
        logger.error("Exception occurred while processing payment {}", ex);
        return new ErrorResponse(String.valueOf(HttpStatus.CONFLICT.value()), "Conflict", ex.getMsg());
    }
}
