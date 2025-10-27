package racingcar;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {
    private static final int MOVING_FORWARD = 4;
    private static final int STOP = 3;

    @DisplayName("전진하는 경우와 멈추는 경우를 포함하는 기능 테스트 및 우승자 확인")
    @Test
    void 기능_테스트() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("pobi,woni", "1");
                    // pobi (4)는 전진, woni (3)는 멈춤 -> pobi 우승
                    assertThat(output()).contains("pobi : -", "woni : ", "최종 우승자 : pobi");
                },
                MOVING_FORWARD, STOP
        );
    }

    @DisplayName("모두 전진하여 공동 우승하는 경우 테스트")
    @Test
    void 공동_우승_테스트() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("a,b,c", "2");
                    // 1회차: 모두 전진 (4, 4, 4) -> a:-, b:-, c:-
                    // 2회차: 모두 전진 (5, 5, 5) -> a:--, b:--, c:--
                    assertThat(output()).contains("a : --", "최종 우승자 : a, b, c");
                },
                MOVING_FORWARD, MOVING_FORWARD, MOVING_FORWARD, MOVING_FORWARD, MOVING_FORWARD, MOVING_FORWARD
        );
    }

    @DisplayName("자동차 이름이 5자를 초과하는 경우 예외 발생")
    @Test
    void 예외_테스트_자동차_이름_길이_초과() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("pobi,javaji", "1"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining("자동차 이름은 5자 이하만 가능합니다")
        );
    }

    @DisplayName("시도 횟수가 1~100 범위를 벗어나는 경우 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"0", "101", "-1", "a"})
    void 예외_테스트_시도_횟수_범위_초과(String number) {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("pobi", number))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining("1~100 사이의 숫자를 입력해주세요")
        );
    }

    @DisplayName("콤마로 분리 시 비어있는 자동차 이름이 포함된 경우 예외 발생")
    @Test
    void 예외_테스트_이름_비어있음() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("pobi,,woni", "1"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining("자동차 이름은 비어 있을 수 없습니다")
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}