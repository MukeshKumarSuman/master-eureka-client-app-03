package com.nps.dto;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class StatementResponse {
    private UUID id;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate paymentDueDate;
}
