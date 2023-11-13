package christmas;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.DiscountType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DiscountTypeTest {
    @Test
    @DisplayName("날짜입력에 문자가 들어오면 예외가 발생한다.")
    void createByInvalidType() {
        String day = "a";

        assertThatThrownBy(() -> new DiscountType(day))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("날짜가 1~31사이의 수가 아닌게 들어오면 예외가 발생한다.")
    void createByInvalidRange() {
        String day = "0";


        assertThatThrownBy(() -> new DiscountType(day))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("날짜가 공백이 들어오면 예외가 발생한다.")
    void createByInvalidGap() {
        String day = "";


        assertThatThrownBy(() -> new DiscountType(day))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("입력한 날짜가 객체에 잘 저장되는지 테스트")
    void saveDayTest() {
        String day = "10";

        DiscountType discountType = new DiscountType(day);

        assertThat(discountType.getDay()).isEqualTo(10);
    }

    @Test
    @DisplayName("날짜가 26일보다 작을때 true를 반환한다")
    void christmasDiscountTrueTest() {
        String day = "25";

        DiscountType discountType = new DiscountType(day);

        assertThat(discountType.christmasDiscountCheck()).isEqualTo(true);
    }

    @Test
    @DisplayName("날짜가 25일보다 클때 false를 반환한다")
    void christmasDiscountFalseTest() {
        String day = "26";

        DiscountType discountType = new DiscountType(day);

        assertThat(discountType.christmasDiscountCheck()).isEqualTo(false);
    }

    @Test
    @DisplayName("날짜가 달력에 별이 있는 날짜일 때 true를 반환한다")
    void starDiscountTrueTest() {
        String day = "25";

        DiscountType discountType = new DiscountType(day);

        assertThat(discountType.starDiscountCheck()).isEqualTo(true);
    }
    @Test
    @DisplayName("날짜가 달력에 별이 없는 날짜일 때 false를 반환한다")
    void starDiscountFalseTest() {
        String day = "5";

        DiscountType discountType = new DiscountType(day);

        assertThat(discountType.starDiscountCheck()).isEqualTo(false);
    }

    @Test
    @DisplayName("날짜가 주말일 때 메인을 반환한다")
    void weekendDiscountTest() {
        String day = "9";

        DiscountType discountType = new DiscountType(day);

        assertThat(discountType.weekDiscountCheck()).isEqualTo("메인");
    }

    @Test
    @DisplayName("날짜가 평일일 때 디저트을 반환한다")
    void weekdayDiscountTest() {
        String day = "10";

        DiscountType discountType = new DiscountType(day);

        assertThat(discountType.weekDiscountCheck()).isEqualTo("디저트");
    }

    @Test
    @DisplayName("총 금액이 12만원 이상일 경우 true를 반환한다")
    void giftEventTrueTest() {
        String day = "10";
        int totalPrice = 120000;

        DiscountType discountType = new DiscountType(day);

        assertThat(discountType.giftEventCheck(totalPrice)).isEqualTo(true);
    }

    @Test
    @DisplayName("총 금액이 12만원 미만일 경우 false를 반환한다")
    void giftEventFalseTest() {
        String day = "10";
        int totalPrice = 110000;

        DiscountType discountType = new DiscountType(day);

        assertThat(discountType.giftEventCheck(totalPrice)).isEqualTo(false);
    }
}
