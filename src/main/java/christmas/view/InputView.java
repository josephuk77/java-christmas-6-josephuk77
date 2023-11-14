package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.DiscountType;
import christmas.domain.Order;

public class InputView {
    public String inputDay() {
        return Console.readLine();
    }

    public String inputOrder() {
        return Console.readLine();
    }
}
