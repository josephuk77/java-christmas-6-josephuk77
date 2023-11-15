package christmas.contoller;

import christmas.domain.DiscountCalculator;
import christmas.domain.DiscountType;
import christmas.domain.EventBadge;
import christmas.domain.Order;
import christmas.view.InputView;
import christmas.view.OutputView;


public class ChristmasController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void start() {
        outputView.start();
        DiscountType discountType = inputDay();
        Order order = inputOrder();
        printDayAndOrder(discountType, order);
        printTotalPriceAndGiftMenu(discountType, order);
        calculateDiscounts(discountType, order);
    }

    private DiscountType inputDay() {
        outputView.printDayInputMessage();
        DiscountType discountType = null;
        while (discountType == null) {
            try {
                String input = inputView.inputDay();
                discountType = new DiscountType(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return discountType;
    }

    private Order inputOrder() {
        outputView.printOrderInputMessage();
        Order order = null;
        while (order == null) {
            try {
                String input = inputView.inputOrder();
                order = new Order(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return order;
    }

    private void printDayAndOrder(DiscountType discountType, Order order) {
        discountType.giftEventCheck(order.calculateTotalPrice());
        outputView.printDayMessage(discountType.getDay());
        outputView.printOrderMenuMessage(order);
    }

    private void printTotalPriceAndGiftMenu(DiscountType discountType, Order order) {
        outputView.printTotalPriceBeforeDiscountMessage(order);
        outputView.printGiftMenuMessage(discountType.giftEventCheck(order.calculateTotalPrice()));
    }

    private void calculateDiscounts(DiscountType discountType, Order order) {
        int christmasDiscount = DiscountCalculator.calculateChristmasDiscount(discountType.getDay(), discountType.christmasDiscountCheck(), order.calculateTotalPrice());
        int starDiscount = DiscountCalculator.calculateStarDiscount(discountType.starDiscountCheck(), order.calculateTotalPrice());
        int weekendDiscount = DiscountCalculator.calculateWeekendDiscount(order, discountType.weekDiscountCheck(), order.calculateTotalPrice());
        int giftEvent = DiscountCalculator.calculateGiftEvent(discountType.giftEventCheck(order.calculateTotalPrice()));

        int totalDiscount = christmasDiscount + starDiscount + weekendDiscount + giftEvent;
        printDiscountDetails(christmasDiscount, starDiscount, weekendDiscount, giftEvent, totalDiscount, discountType.weekDiscountCheck(), order.calculateTotalPrice());
    }

    private void printDiscountDetails(int christmasDiscount, int starDiscount, int weekendDiscount, int giftEvent, int totalDiscount, String weekDiscountType, int totalPrice) {
        outputView.printBenefitDetailsMessage(christmasDiscount, starDiscount, weekendDiscount, giftEvent, weekDiscountType, totalDiscount);
        outputView.printTotalBenefitAmountMessage(totalDiscount);
        outputView.printExpectedPaymentAfterDiscount(totalPrice, totalDiscount);
        assignBadge(totalDiscount);
    }

    private void assignBadge(int totalDiscount) {
        String badge = EventBadge.eventBadge(totalDiscount);
        printBadge(badge);
    }

    private void printBadge(String badge) {
        outputView.printEventBadgeMessage(badge);
    }
}
