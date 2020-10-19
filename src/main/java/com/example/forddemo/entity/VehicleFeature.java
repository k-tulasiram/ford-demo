package com.example.forddemo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
public class VehicleFeature implements Serializable {
    private String[] exterior;
    private String[] interior;

    public VehicleFeature() {
    }

    public VehicleFeature(String[] exterior, String[] interior) {
        this.exterior = exterior;
        this.interior = interior;
    }

    public String[]
    getExterior() {
        return exterior;
    }

    public void setExterior(String[] exterior) {
        this.exterior = exterior;
    }

    public String[] getInterior() {
        return interior;
    }

    public void setInterior(String[] interior) {
        this.interior = interior;
    }
}
