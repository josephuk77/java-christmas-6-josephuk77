package christmas.view;

import christmas.Message.RunMessage;
import christmas.domain.DiscountCalculator;
import christmas.domain.MenuItem;
import christmas.domain.Order;
import java.util.Map;

public class OutputView {
    public void start() {
        System.out.print(RunMessage.START.getMessage());
    }

    public void printDayInputMessage() {
        System.out.print(RunMessage.INPUT_DAY.getMessage());
    }

    public void printOrderInputMessage() {
        System.out.print(RunMessage.INPUT_ORDER.getMessage());
    }

    public void printDayMessage(int day) {
        System.out.printf(RunMessage.VISIT_DAY.getMessage(), day);
    }

    public void printOrderMenuMessage(Order order) {
        Map<MenuItem, Integer> items = order.getItems();

        System.out.print(RunMessage.ORDER.getMessage());

        for (Map.Entry<MenuItem, Integer> entry : items.entrySet()) {
            System.out.printf(RunMessage.ORDER_MENU.getMessage(), entry.getKey().getName(), entry.getValue());
        }
    }

    public void printTotalPriceBeforeDiscountMessage(Order order) {
        System.out.print(RunMessage.BEFORE_DISCOUNT.getMessage());
        System.out.printf(RunMessage.AMOUNT.getMessage(), String.format("%,d", order.getTotalPrice()));
    }

    public void printGiftMenuMessage(boolean isGiftEvent) {
        System.out.print(RunMessage.GIFT.getMessage());

        if (isGiftEvent) {
            System.out.print(RunMessage.GIFT_MENU.getMessage());
        }
        if (!isGiftEvent) {
            System.out.print(RunMessage.NONE.getMessage());
        }
    }

    public void printBenefitDetailsMessage(DiscountCalculator discountCalculator, String weekDiscountType) {
        System.out.print(RunMessage.BENEFIT_DETAILS.getMessage());

        printChristmasDiscount(discountCalculator);
        printWeekDiscount(discountCalculator, weekDiscountType);
        printStarDiscount(discountCalculator);
        printGiftEvent(discountCalculator);

        if (discountCalculator.totalDiscount() == 0) {
            System.out.print(RunMessage.NONE.getMessage());
        }
    }

    private void printChristmasDiscount(DiscountCalculator discountCalculator) {
        if (discountCalculator.getChristmasDiscount() != 0) {
            System.out.printf(RunMessage.CHRISTMAS_DISCOUNT.getMessage(),
                    String.format("%,d", discountCalculator.getChristmasDiscount()));
        }
    }

    private void printWeekDiscount(DiscountCalculator discountCalculator, String weekDiscountType) {
        if (discountCalculator.getWeekDiscount() != 0) {
            String formattedWeekDiscount = String.format("%,d", discountCalculator.getWeekDiscount());
            if (weekDiscountType.equals("메인")) {
                System.out.printf(RunMessage.WEEKDAY_DISCOUNT.getMessage(), formattedWeekDiscount);
            }
            if (weekDiscountType.equals("디저트")) {
                System.out.printf(RunMessage.WEEKEND_DISCOUNT.getMessage(), formattedWeekDiscount);
            }
        }
    }

    private void printStarDiscount(DiscountCalculator discountCalculator) {
        if (discountCalculator.getStarDiscount() != 0) {
            System.out.printf(RunMessage.SPECIAL_DISCOUNT.getMessage(),
                    String.format("%,d", discountCalculator.getStarDiscount()));
        }
    }

    private void printGiftEvent(DiscountCalculator discountCalculator) {
        if (discountCalculator.getGiftEvent() != 0) {
            System.out.print(RunMessage.GIFT_EVENT.getMessage());
        }
    }

    public void printTotalBenefitAmountMessage(DiscountCalculator discountCalculator) {
        System.out.print(RunMessage.TOTLA_BENEFIT.getMessage());
        if (discountCalculator.totalDiscount() == 0) {
            System.out.printf(RunMessage.AMOUNT.getMessage(),0);
        }
        if (discountCalculator.totalDiscount() != 0) {
            System.out.printf(RunMessage.MINUS_AMOUNT.getMessage(),
                    String.format("%,d", discountCalculator.totalDiscount()));
        }
    }

    public void printExpectedPaymentAfterDiscount(int totalPrice, int totalDiscount) {
        System.out.print(RunMessage.AFTER_DISCOUNT.getMessage());
        System.out.printf(RunMessage.AMOUNT.getMessage(), String.format("%,d", totalPrice - totalDiscount));
    }

    public void printEventBadgeMessage(String eventBadge) {
        System.out.printf(RunMessage.EVENT_BADGE.getMessage(), eventBadge);
    }
}
