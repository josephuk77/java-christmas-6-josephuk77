package christmas.domain;

import java.util.Map;

public class DiscountCalculator {
    private int christmasDiscount;
    private int starDiscount;
    private int weekDiscount;
    private int giftEvent;

    public DiscountCalculator() {
        this.christmasDiscount = 0;
        this.starDiscount = 0;
        this.weekDiscount = 0;
        this.giftEvent = 0;
    }

    public void calculateChristmasDiscount(int day, boolean isChristmasDiscount, int totalPrice) {
        if (isChristmasDiscount && totalPrice >= 10000) {
            this.christmasDiscount = 1000 + (day - 1) * 100;
        }
    }

    public void calculateStarDiscount(boolean isStarDiscount, int totalPrice) {
        if (isStarDiscount && totalPrice >= 10000) {
            this.starDiscount = 1000;
        }
    }

    public void calculateWeekendDiscount(Order order, String weekDiscountType, int totalPrice) {
        int count = 0;
        for (Map.Entry<MenuItem, Integer> entry : order.getItems().entrySet()) {
            if (weekDiscountType.equals(entry.getKey().getCategory()) && totalPrice >= 10000) {
                count += entry.getValue();
            }
        }
        this.weekDiscount = count * 2023;
    }

    public void calculateGiftEvent(boolean isGiftEvent) {
        if (isGiftEvent) {
            this.giftEvent = 25000;
        }
    }

    public int totalDiscount() {
        return christmasDiscount + starDiscount + weekDiscount + giftEvent;
    }

    public int getChristmasDiscount() {
        return christmasDiscount;
    }

    public int getStarDiscount() {
        return starDiscount;
    }

    public int getWeekDiscount() {
        return weekDiscount;
    }

    public int getGiftEvent() {
        return giftEvent;
    }
}
