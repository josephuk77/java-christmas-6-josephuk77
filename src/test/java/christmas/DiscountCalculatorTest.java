package christmas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.domain.DiscountCalculator;
import christmas.domain.DiscountType;
import christmas.domain.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DiscountCalculatorTest {

    private Order order;
    private DiscountType dicountType;

    @BeforeEach
    void setUp() {
        order = new Order("티본스테이크-2,아이스크림-1");
        dicountType = new DiscountType("25");
    }

    @Test
    @DisplayName("크리스마스 할인 계산 테스트")
    void testCalculateChristmasDiscount() {
        int day = dicountType.getDay();
        boolean isChristmasDiscount = dicountType.christmasDiscountCheck();
        int totalPrice = order.calculateTotalPrice();
        int discount = DiscountCalculator.calculateChristmasDiscount(day, isChristmasDiscount, totalPrice);

        assertEquals(3400, discount);
    }

    @Test
    @DisplayName("별 표시된 날짜 할인 계산 테스트")
    void testCalculateStarDiscount() {
        boolean isStarDiscount = dicountType.starDiscountCheck();
        int totalPrice = order.calculateTotalPrice();
        int discount = DiscountCalculator.calculateStarDiscount(isStarDiscount, totalPrice);

        assertEquals(1000, discount);
    }

    @Test
    @DisplayName("평일 할인 계산 테스트")
    void testCalculateWeekendDiscount() {
        String weekDiscountType = dicountType.weekDiscountCheck();
        int totalPrice = order.calculateTotalPrice();
        int discount = DiscountCalculator.calculateWeekendDiscount(order, weekDiscountType, totalPrice);

        assertEquals(2023, discount);
    }

    @Test
    @DisplayName("증정 이벤트 계산 테스트")
    void testCalculateGiftEvent() {
        int totalPrice = order.calculateTotalPrice();
        boolean isGiftEvent = dicountType.giftEventCheck(totalPrice);
        int discount = DiscountCalculator.calculateGiftEvent(isGiftEvent);

        assertEquals(0, discount);
    }
}
