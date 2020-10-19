package com.example.forddemo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehicleTable implements Serializable {
    @Id
    private long vehicleId;
    private String make;
    private String model;
    private String modelYear;
    private String bodyStyle;
    private String engine;
    private String drivetype;
    private String color;
    private String mpg;
    private String exterior;
    private String interior;
    private String msrp;
    private String savings;
    private String finalprice;
}
