package christmas.domain;

public class EventBadge {
    private String badge;

    public void eventBadge(int totalDiscount) {
        if (totalDiscount >= 20000) {
            badge = "산타";
        }
        if (totalDiscount >= 10000) {
            badge = "트리";
        }
        if (totalDiscount >= 5000) {
            badge = "별";
        }
        if (totalDiscount < 5000) {
            badge = "없음";
        }
    }

    public String getBadge() {
        return badge;
    }
}
