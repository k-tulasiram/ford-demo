package com.example.forddemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class VehicleCreateResponse {
    private String status;
    private int statusCode;
    private String message;

    public VehicleCreateResponse(String status, int statusCode, String message) {
        this.status = status;
        this.statusCode = statusCode;
        this.message = message;
    }
}
