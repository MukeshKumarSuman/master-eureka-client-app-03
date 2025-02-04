package com.nps.controller;

import com.nps.dto.PaymentRequest;
import com.nps.dto.StatementResponse;
import com.nps.exception.BadRequest;
import com.nps.exception.Conflict;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@RestController
//@RequestMapping("/v1")
public class PaymentController extends ExceptionHandler {

    private static  final Logger logger = LoggerFactory.getLogger(PaymentController.class);
    @PostMapping("/payment")
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

    @GetMapping("/statement")
    public ResponseEntity<StatementResponse> getStatement() {
        StatementResponse response = new StatementResponse();
        LocalDateTime localDateTime = LocalDateTime.now().minusHours(18);
        OffsetDateTime offsetDateTime = OffsetDateTime.of(localDateTime, ZoneOffset.UTC);
        String isoDateTime = offsetDateTime.format(DateTimeFormatter.ISO_DATE_TIME);
        response.setId(UUID.randomUUID());
        response.setPaymentDueDate(offsetDateTime);
        logger.info("UTC  offsetDateTime: {}", offsetDateTime);
        logger.info("ISO_DATE_TIME localDateTime: {}", isoDateTime);
        logger.info("Statement response: {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/demo1")
    public String demo() {
        return "Demo";
    }

}
