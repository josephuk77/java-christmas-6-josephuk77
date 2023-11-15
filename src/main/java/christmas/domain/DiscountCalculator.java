package christmas.domain;

import java.util.Map;

public class DiscountCalculator {
    public static int calculateChristmasDiscount(int day, boolean isChristmasDiscount, int totalPrice) {
        if (isChristmasDiscount && totalPrice >= 10000) {
            return 1000 + (day - 1) * 100;
        }
        return 0;
    }

    public static int calculateStarDiscount(boolean isStarDiscount, int totalPrice) {
        if (isStarDiscount && totalPrice >= 10000) {
            return 1000;
        }
        return 0;
    }

    public static int calculateWeekendDiscount(Order order, String weekDiscountType, int totalPrice) {
        int count = 0;
        for (Map.Entry<MenuItem, Integer> entry : order.getItems().entrySet()) {
            if (weekDiscountType.equals(entry.getKey().getCategory()) && totalPrice >= 10000) {
                count += entry.getValue();
            }
        }
        return count * 2023;
    }

    public static int calculateGiftEvent(boolean isGiftEvent) {
        if (isGiftEvent) {
            return 25000;
        }
        return 0;
    }
}
