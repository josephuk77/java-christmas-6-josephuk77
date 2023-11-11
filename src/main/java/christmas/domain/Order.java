package christmas.domain;

import christmas.Message.ExceptionMessage;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Order {
    private static final String ORDER_PATTERN = "([가-힣]+)-(1[0-9]|20|[1-9])(,\\s*[가-힣]+-(1[0-9]|20|[1-9]))*";
    private final Map<MenuItem, Integer> items;

    public Order(String input) {
        items = new HashMap<>();
        validateInput(input);
        addItemsFromString(input);
        validateMenuCount();
        validateOrder();
    }

    public int getTotalPrice() {
        int total = 0;
        for (Map.Entry<MenuItem, Integer> entry : items.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }

    public Map<MenuItem, Integer> getItems() {
        return new HashMap<>(items);
    }

    private void validateInput(String input) {
        if (!Pattern.matches(ORDER_PATTERN, input)) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_ORDER.getMessage());
        }
    }

    private void addItemsFromString(String input) {
        String[] orderItems = splitOrderItems(input);
        for (String orderItem : orderItems) {
            processOrderItem(orderItem);
        }
    }

    private void validateMenuCount() {
        int total = 0;
        for (Map.Entry<MenuItem, Integer> entry : items.entrySet()) {
            total += entry.getValue();
        }
        if (total > 20) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_ORDER.getMessage());
        }
    }

    private void validateOrder() {
        if (isOnlyBeverages()) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_ORDER.getMessage());
        }
    }

    private String[] splitOrderItems(String input) {
        return input.split(",");
    }

    private void processOrderItem(String orderItem) {
        String[] parts = orderItem.trim().split("-");
        String itemName = parts[0].trim();
        int quantity = Integer.parseInt(parts[1].trim());

        MenuItem.fromDisplayName(itemName);
        addMenuItem(itemName, quantity);
    }

    private void addMenuItem(String itemName, int quantity) {
        MenuItem menuItem = MenuItem.fromDisplayName(itemName);
        if (items.containsKey(menuItem)) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_ORDER.getMessage());
        }
        items.put(menuItem, items.getOrDefault(menuItem, 0) + quantity);
    }

    private boolean isOnlyBeverages() {
        for (MenuItem item : items.keySet()) {
            if (!"음료".equals(item.getCategory())) {
                return false;
            }
        }
        return true;
    }
}
