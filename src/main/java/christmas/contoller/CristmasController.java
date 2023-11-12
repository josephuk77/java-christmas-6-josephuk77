package christmas.contoller;

import christmas.domain.DiscountCalculator;
import christmas.domain.DiscountType;
import christmas.domain.EventBadge;
import christmas.domain.Order;
import christmas.view.InputView;
import christmas.view.OutputView;


public class CristmasController {
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
        discountType.updateGiftEventStatus(order.getTotalPrice());
        outputView.printDayMessage(discountType.getDay());
        outputView.printOrderMenuMessage(order);
    }

    private void printTotalPriceAndGiftMenu() {
        outputView.printTotalPriceBeforeDiscountMessage(order);
        outputView.printGiftMenuMessage(discountType.getGiftEventStatus());
    }

    private void calculateDiscounts() {
        discountCalculator = new DiscountCalculator();
        discountCalculator.calculateChristmasDiscount(discountType.getDay(), discountType.getChristmasDiscountStatus(), order.getTotalPrice());
        discountCalculator.calculateStarDiscount(discountType.getStarDiscountStatus(), order.getTotalPrice());
        discountCalculator.calculateWeekendDiscount(order, discountType.getWeekDiscount(), order.getTotalPrice());
        discountCalculator.calculateGiftEvent(discountType.getGiftEventStatus());
    }

    private void printDiscountDetails() {
        outputView.printBenefitDetailsMessage(discountCalculator, discountType.getWeekDiscount());
        outputView.printTotalBenefitAmountMessage(discountCalculator);
        outputView.printExpectedPaymentAfterDiscount(order.getTotalPrice(), discountCalculator.totalDiscount());
    }

    private void assignBadge() {
        eventBadge = new EventBadge();
        eventBadge.eventBadge(discountCalculator.totalDiscount());
    }

    private void printBadge() {
        outputView.printEventBadgeMessage(eventBadge.getBadge());
    }
}
