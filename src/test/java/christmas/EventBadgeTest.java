package christmas;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.EventBadge;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EventBadgeTest {
    @Test
    @DisplayName("총 혜택금액이 2만원 이상일 경우 산타를 반환한다")
    void eventBadgeSantaTest() {
        int totalDiscount = 20000;

        assertThat(EventBadge.eventBadge(totalDiscount)).isEqualTo("산타");
    }

    @Test
    @DisplayName("총 혜택금액이 1만원 이상일 경우 트리를 반환한다")
    void eventBadgeTreeTest() {
        int totalDiscount = 10000;

        assertThat(EventBadge.eventBadge(totalDiscount)).isEqualTo("트리");
    }

    @Test
    @DisplayName("총 혜택금액이 5천원 이상일 경우 별를 반환한다")
    void eventBadgeStarTest() {
        int totalDiscount = 5000;

        assertThat(EventBadge.eventBadge(totalDiscount)).isEqualTo("별");
    }

    @Test
    @DisplayName("총 혜택금액이 5천원 미만일 경우 없음를 반환한다")
    void eventBadgeNoneTest() {
        int totalDiscount = 10;

        assertThat(EventBadge.eventBadge(totalDiscount)).isEqualTo("없음");
    }
}
