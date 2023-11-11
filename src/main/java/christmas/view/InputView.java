package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.DiscountType;
import christmas.domain.Order;

public class InputView {
    public DiscountType inputDay() {
        DiscountType discountType = null;
        while (discountType == null){
            try {
                String input = Console.readLine();
                discountType = new DiscountType(input);
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }

        return discountType;
    }

    public Order inputOrder() {
        Order order = null;
        while (order == null){
            try {
                String input = Console.readLine();
                order = new Order(input);
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }

        return order;
    }
}
