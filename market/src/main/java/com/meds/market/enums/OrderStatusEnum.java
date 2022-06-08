package com.meds.market.enums;

import java.util.stream.IntStream;

public enum OrderStatusEnum {
    
    // PENDENT("pendend"), ACCEPTED("accepted"), PICKED_UP("picked_up"), DELIVERED("delivered");
    PENDENT, ACCEPTED, PICKED_UP, DELIVERED;

    static private final OrderStatusEnum[] values = values();

    public static int getNumber(OrderStatusEnum current) {
        return IntStream.range(0, values.length).filter(i -> current == values[i]).findFirst().orElse(-1);
    }

    public static OrderStatusEnum getNext(OrderStatusEnum current) {
        return values[(getNumber(current) +1) % values.length];
    }
}
