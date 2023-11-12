package christmas.contoller;

import christmas.domain.DiscountCalculator;
import christmas.domain.DiscountType;
import christmas.domain.EventBadge;
import christmas.domain.Order;
import christmas.view.InputView;
import christmas.view.OutputView;


public class ChristmasController {
    InputView inputView = new InputView();
    OutputView outputView = new OutputView();
    Order order;
    DiscountType discountType;
    DiscountCalculator discountCalculator;
    EventBadge eventBadge;

    public void start() {
        outputView.start();
        inputDay();
        inputOrder();
        printDayAndOrder();
        printTotalPriceAndGiftMenu();
        calculateDiscounts();
        printDiscountDetails();
        assignBadge();
        printBadge();
    }

    private void inputDay() {
        outputView.printDayInputMessage();
        discountType = inputView.inputDay();
    }

    private void inputOrder() {
        outputView.printOrderInputMessage();
        order = inputView.inputOrder();
    }

    private void printDayAndOrder() {
        discountType.giftEventCheck(order.calculateTotalPrice());
        outputView.printDayMessage(discountType.getDay());
        outputView.printOrderMenuMessage(order);
    }

    private void printTotalPriceAndGiftMenu() {
        outputView.printTotalPriceBeforeDiscountMessage(order);
        outputView.printGiftMenuMessage(discountType.giftEventCheck(order.calculateTotalPrice()));
    }

    private void calculateDiscounts() {
        discountCalculator = new DiscountCalculator();
        discountCalculator.calculateChristmasDiscount(discountType.getDay(), discountType.christmasDiscountCheck(), order.calculateTotalPrice());
        discountCalculator.calculateStarDiscount(discountType.starDiscountCheck(), order.calculateTotalPrice());
        discountCalculator.calculateWeekendDiscount(order, discountType.weekDiscountCheck(), order.calculateTotalPrice());
        discountCalculator.calculateGiftEvent(discountType.giftEventCheck(order.calculateTotalPrice()));
    }

    private void printDiscountDetails() {
        outputView.printBenefitDetailsMessage(discountCalculator, discountType.weekDiscountCheck());
        outputView.printTotalBenefitAmountMessage(discountCalculator);
        outputView.printExpectedPaymentAfterDiscount(order.calculateTotalPrice(), discountCalculator.totalDiscount());
    }

    private void assignBadge() {
        eventBadge = new EventBadge();
        eventBadge.eventBadge(discountCalculator.totalDiscount());
    }

    private void printBadge() {
        outputView.printEventBadgeMessage(eventBadge.getBadge());
    }
}
