package com.meds.market.enums;

import java.util.stream.IntStream;

public enum PayTypeEnum {
    
    CREDIT_CARD, DEBIT_CARD, PAYPAL;

    static private final PayTypeEnum[] values = values();

    public static int getNumber(PayTypeEnum current) {
        return IntStream.range(0, values.length).filter(i -> current == values[i]).findFirst().orElse(-1);
    }
}
