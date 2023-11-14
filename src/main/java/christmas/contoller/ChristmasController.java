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
        DiscountCalculator discountCalculator = new DiscountCalculator();
        discountCalculator.calculateChristmasDiscount(discountType.getDay(), discountType.christmasDiscountCheck(), order.calculateTotalPrice());
        discountCalculator.calculateStarDiscount(discountType.starDiscountCheck(), order.calculateTotalPrice());
        discountCalculator.calculateWeekendDiscount(order, discountType.weekDiscountCheck(), order.calculateTotalPrice());
        discountCalculator.calculateGiftEvent(discountType.giftEventCheck(order.calculateTotalPrice()));
        printDiscountDetails(discountCalculator, discountType.weekDiscountCheck(), order.calculateTotalPrice());
    }

    private void printDiscountDetails(DiscountCalculator discountCalculator, String weekDiscountType, int totalPrice) {
        outputView.printBenefitDetailsMessage(discountCalculator, weekDiscountType);
        outputView.printTotalBenefitAmountMessage(discountCalculator);
        outputView.printExpectedPaymentAfterDiscount(totalPrice, discountCalculator.totalDiscount());
        assignBadge(discountCalculator.totalDiscount());
    }

    private void assignBadge(int totalDiscount) {
        EventBadge eventBadge = new EventBadge();
        eventBadge.eventBadge(totalDiscount);
        printBadge(eventBadge.getBadge());
    }

    private void printBadge(String badge) {
        outputView.printEventBadgeMessage(badge);
    }
}
