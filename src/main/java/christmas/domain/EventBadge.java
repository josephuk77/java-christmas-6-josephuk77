package christmas.domain;

public class EventBadge {
    public static String  eventBadge(int totalDiscount) {
        if (totalDiscount >= 20000) {
            return  "산타";
        }
        if (totalDiscount >= 10000) {
            return "트리";
        }
        if (totalDiscount >= 5000) {
            return "별";
        }
            return  "없음";
    }
}
