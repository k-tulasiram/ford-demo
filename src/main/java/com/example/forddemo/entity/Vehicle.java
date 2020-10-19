package com.example.forddemo.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Vehicle implements Serializable {
    private long vehicleId;
    private VehicleDetails vehicleDetails;
    private VehiclePrice vehiclePrice;

    public Vehicle(){
    }

    public Vehicle(long vehicleId, VehicleDetails vehicleDetails, VehiclePrice vehiclePrice) {
        this.vehicleId = vehicleId;
        this.vehicleDetails = vehicleDetails;
        this.vehiclePrice = vehiclePrice;
    }
}
