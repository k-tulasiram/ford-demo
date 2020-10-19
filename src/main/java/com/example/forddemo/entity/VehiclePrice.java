package com.example.forddemo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
public class VehiclePrice implements Serializable {
    private String msrp;
    private String savings;
    private String finalprice;

    public VehiclePrice() {

    }

    public VehiclePrice(String msrp, String savings, String finalprice) {
        this.msrp = msrp;
        this.savings = savings;
        this.finalprice = finalprice;
    }


    public String getMsrp() {
        return msrp;
    }

    public void setMsrp(String msrp) {
        this.msrp = msrp;
    }

    public String getSavings() {
        return savings;
    }

    public void setSavings(String savings) {
        this.savings = savings;
    }

    public String getFinalprice() {
        return finalprice;
    }

    public void setFinalprice(String finalprice) {
        this.finalprice = finalprice;
    }
}
