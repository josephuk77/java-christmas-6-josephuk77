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
        processUserInput();
        processOrder();
        calculateDiscounts();
        giveBadge();
    }

    public void processUserInput() {
        outputView.printDayInputMessage();
        discountType = inputView.inputDay();
        outputView.printOrderInputMessage();
        order = inputView.inputOrder();
    }

    public void processOrder() {
        discountType.updateGiftEventStatus(order.getTotalPrice());
        outputView.printDayMessage(discountType.getDay());
        outputView.printOrderMenuMessage(order);
        outputView.printTotalPriceBeforeDiscountMessage(order);
        outputView.printGiftMenuMessage(discountType.getGiftEventStatus());
    }

    public void calculateDiscounts() {
        discountCalculator = new DiscountCalculator();
        discountCalculator.calculateChristmasDiscount(discountType.getDay(), discountType.getChristmasDiscountStatus(), order.getTotalPrice());
        discountCalculator.calculateStarDiscount(discountType.getStarDiscountStatus(), order.getTotalPrice());
        discountCalculator.calculateWeekendDiscount(order, discountType.getWeekDiscount(), order.getTotalPrice());
        discountCalculator.calculateGiftEvent(discountType.getGiftEventStatus());
        outputView.printBenefitDetailsMessage(discountCalculator, discountType.getWeekDiscount());
        outputView.printTotalBenefitAmountMessage(discountCalculator);
        outputView.printExpectedPaymentAfterDiscount(order.getTotalPrice(), discountCalculator.totalDiscount());
    }

    public void giveBadge() {
        eventBadge = new EventBadge();
        eventBadge.eventBadge(discountCalculator.totalDiscount());
        outputView.printEventBadgeMessage(eventBadge.getBadge());
    }
}
