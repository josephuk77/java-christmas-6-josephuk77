package christmas.domain;

import christmas.Message.ExceptionMessage;
import java.util.Arrays;
import java.util.List;

public class DiscountType {
    private static final List<Integer> weekend = Arrays.asList(1, 2, 8, 9, 15, 16, 22, 23, 29, 30);
    private static final List<Integer> star = Arrays.asList(3, 10, 17, 24, 25, 31);
    private static final String DAY_PATTERN = "^(3[01]|[12][0-9]|0?[1-9])$";

    private final int day;
    private final boolean isChristmasDiscount;
    private final boolean isStarDiscount;
    private final String weekDiscountType;
    private boolean isGiftEvent;

    public DiscountType(String input) {
        validateDay(input);
        int day = parseDay(input);

        this.day = day;
        this.isChristmasDiscount = christmasDiscountCheck(day);
        this.isStarDiscount = starDiscountCheck(day);
        this.weekDiscountType = weekDiscount(day);
    }

    private void validateDay(String input) {
        if (!input.matches(DAY_PATTERN)) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_DAY.getMessage());
        }
    }

    private int parseDay(String input) {
        return Integer.parseInt(input);
    }

    private boolean christmasDiscountCheck(int day) {
        return day <= 25;
    }

    private boolean starDiscountCheck(int day) {
        return star.contains(day);
    }

    private String weekDiscount(int day) {
        if(weekend.contains(day)) {
            return "메인";
        }

        return "디저트";
    }

    public void updateGiftEventStatus(int totalPrice) {
        this.isGiftEvent = totalPrice >= 120000;
    }

    public int getDay() {
        return day;
    }

    public boolean getChristmasDiscountStatus() {
        return isChristmasDiscount;
    }

    public boolean getStarDiscountStatus() {
        return isStarDiscount;
    }

    public String getWeekDiscount() {
        return weekDiscountType;
    }

    public boolean getGiftEventStatus() {
        return isGiftEvent;
    }
}
