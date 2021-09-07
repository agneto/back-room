package com.digital.crud.saladereuniao.saladereuniao.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {

    private OffsetDateTime timestamp;
    private String message;
    private String details;

}
