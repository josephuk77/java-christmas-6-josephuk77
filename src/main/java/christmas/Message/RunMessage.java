package christmas.Message;

public enum RunMessage {
    START("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.\n"),
    INPUT_DAY("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)\n"),
    INPUT_ORDER("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)\n"),
    VISIT_DAY("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n"),
    ORDER("\n<주문 메뉴>\n"),
    ORDER_MENU("%s %d개\n"),
    BEFORE_DISCOUNT("\n<할인 전 총주문 금액>\n"),
    AMOUNT("%s원\n"),
    MINUS_AMOUNT("-%s원\n"),
    GIFT("\n<증정 메뉴>\n"),
    GIFT_MENU("샴페인 1개\n"),
    BENEFIT_DETAILS("\n<혜택 내역>\n"),
    CHRISTMAS_DISCOUNT("크리스마스 디데이 할인: -%s원\n"),
    WEEKDAY_DISCOUNT("평일 할인: -%s원\n"),
    WEEKEND_DISCOUNT("주말 할인: -%s원\n"),
    SPECIAL_DISCOUNT("특별 할인: -%s원\n"),
    GIFT_EVENT("증정 이벤트: -25,000원\n"),
    TOTLA_BENEFIT("\n<총혜택 금액>\n"),
    AFTER_DISCOUNT("\n<할인 후 예상 결제 금액>\n"),
    EVENT_BADGE("\n<12월 이벤트 배지>\n%s\n"),
    NONE("없음\n");

    private final String message;


    RunMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

}
