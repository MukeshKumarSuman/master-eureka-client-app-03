package com.nps.controller;

import com.nps.dto.ErrorResponse;
import com.nps.dto.PaymentRequest;
import com.nps.exception.BadRequest;
import com.nps.exception.Conflict;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private static  final Logger logger = LoggerFactory.getLogger(PaymentController.class);
    @PostMapping
    void initializePayment(@RequestBody PaymentRequest request) {
        logger.info("Request Received: {}", request);
        int amount = request.getAmount();
        if (amount < 10) {
            throw new BadRequest("amount must be greater than " + amount);
        }

        if ( amount > 80 && amount < 100) {
            throw new Conflict("Request all ready processed");
        }
    }

    @ExceptionHandler(BadRequest.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBadRequest(BadRequest ex) {
        logger.error("Exception occurred while processing payment {}", ex);
        return new ErrorResponse(String.valueOf(HttpStatus.BAD_REQUEST.value()), "BadRequest", ex.getMsg());
    }

    @ExceptionHandler(Conflict.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleConflict(Conflict ex) {
        logger.error("Exception occurred while processing payment {}", ex);
        return new ErrorResponse(String.valueOf(HttpStatus.CONFLICT.value()), "Conflict", ex.getMsg());
    }
}
