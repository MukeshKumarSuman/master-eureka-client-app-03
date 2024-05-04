package com.nps.controller;

import com.nps.dto.PaymentRequest;
import com.nps.exception.BadRequest;
import com.nps.exception.Conflict;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/billpay")
public class BillpayController extends ExceptionHandler {

    private static  final Logger logger = LoggerFactory.getLogger(BillpayController.class);

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
}
