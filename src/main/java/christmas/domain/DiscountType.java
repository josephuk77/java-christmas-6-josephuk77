package christmas.domain;

import christmas.Message.ExceptionMessage;
import java.util.Arrays;
import java.util.List;

public class DiscountType {
    private static final List<Integer> weekend = Arrays.asList(1, 2, 8, 9, 15, 16, 22, 23, 29, 30);
    private static final List<Integer> star = Arrays.asList(3, 10, 17, 24, 25, 31);
    private static final String DAY_PATTERN = "^(3[01]|[12][0-9]|0?[1-9])$";

    private final int day;

    public DiscountType(String input) {
        validateDay(input);

        this.day = parseDay(input);
    }

    private void validateDay(String input) {
        if (!input.matches(DAY_PATTERN)) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_DAY.getMessage());
        }
    }

    private int parseDay(String input) {
        return Integer.parseInt(input);
    }

    public boolean christmasDiscountCheck() {
        return day <= 25;
    }

    public boolean starDiscountCheck() {
        return star.contains(day);
    }

    public String weekDiscountCheck() {
        if(weekend.contains(day)) {
            return "메인";
        }
        return "디저트";
    }

    public boolean giftEventCheck(int totalPrice) {
        return totalPrice >= 120000;
    }

    public int getDay() {
        return day;
    }
}
