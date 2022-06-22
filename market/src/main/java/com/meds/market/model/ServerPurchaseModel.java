package com.meds.market.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ServerPurchaseModel {
    @JsonProperty("orderId")
    private Long orderId;

    public ServerPurchaseModel(long orderId){
        this.orderId = orderId;

    }
    public ServerPurchaseModel(){ }
}
