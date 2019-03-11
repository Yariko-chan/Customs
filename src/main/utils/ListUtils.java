package main.utils;

import main.controller.entities.FullTrade;

import java.util.List;

public class ListUtils {

    private ListUtils() {
    }

    public static float getSum(List<FullTrade> trades) {
        float sum = 0f;
        for (FullTrade trade: trades) {
            sum += trade.getTrade().getPrice() * trade.getTrade().getQuantity();
        }
        return sum;
    }
}
