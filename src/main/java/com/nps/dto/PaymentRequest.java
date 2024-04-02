package com.nps.dto;

import lombok.Data;

@Data
public class PaymentRequest {
    private String txId;
    private int amount;
    private int exponent;
}
