package com.example.forddemo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
public class VehiclePrice implements Serializable {
    private Double msrp;
    private Double savings;
    private Double finalprice;

    public VehiclePrice() {

    }
    public VehiclePrice(Double msrp, Double savings, Double finalprice) {
        this.msrp = msrp;
        this.savings = savings;
        this.finalprice = finalprice;
    }

    public Double getMsrp() {
        return msrp;
    }

    public void setMsrp(Double msrp) {
        this.msrp = msrp;
    }

    public Double getSavings() {
        return savings;
    }

    public void setSavings(Double savings) {
        this.savings = savings;
    }

    public Double getFinalprice() {
        return finalprice;
    }

    public void setFinalprice(Double finalprice) {
        this.finalprice = finalprice;
    }
}
