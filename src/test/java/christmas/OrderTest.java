package christmas;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.MenuItem;
import christmas.domain.Order;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OrderTest {
    @Test
    @DisplayName("메뉴에 없는 음식이 들어오면 예외가 발생한다.")
    void createByInvalidMenu() {
        String order = "치킨-1,제로콜라-4";

        assertThatThrownBy(() -> new Order(order))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("같은 메뉴가 두번 들어오면 예외가 발생한다.")
    void createByInvalidOverlap() {
        String order = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1,티본스테이크-5";

        assertThatThrownBy(() -> new Order(order))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("메뉴의 개수가 20개가 넘으면 예외가 발생한다.")
    void createByInvalidExceed() {
        String order = "티본스테이크-20,바비큐립-1";

        assertThatThrownBy(() -> new Order(order))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("주문에 음료만 있으면 예외가 발생한다.")
    void createByInvalidDrink() {
        String order = "제로콜라-4,레드와인-4,샴페인-1";

        assertThatThrownBy(() -> new Order(order))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("주문개수에 숫자가 1~20이 아니면 예외가 발생한다.")
    void createByInvalidRange() {
        String orderZero = "티본스테이크-0,제로콜라-4";
        String orderExceed = "티본스테이크-21";

        assertThatThrownBy(() -> new Order(orderZero))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Order(orderExceed))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("주문개수에 문자가 들어오면 예외가 발생한다.")
    void createByInvalidType() {
        String order = "티본스테이크-a";

        assertThatThrownBy(() -> new Order(order))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("주문받은 내역이 객체에 잘 저장되는지 테스트")
    void orderDetailsTest() {
        String order = "티본스테이크-3,제로콜라-2";

        Order orderDetails = new Order(order);

        assertThat(orderDetails.getItems()).isEqualTo(Map.of(MenuItem.T_BONE_STEAK, 3, MenuItem.ZERO_COLA, 2));
    }
}
