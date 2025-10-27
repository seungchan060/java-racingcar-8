package racingcar;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

class RacingCalculatorTest {

    @DisplayName("자동차 이름이 5자 이하인 경우 통과한다.")
    @ParameterizedTest
    @ValueSource(strings = {"pobi", "javaji", "a", "12345"})
    void validateCarName_Pass_WhenLengthIsFiveOrLess(String name) {
        assertThatCode(() -> RacingCalculator.race(name, "1")).doesNotThrowAnyException();
    }

    @DisplayName("자동차 이름이 6자 이상인 경우 IllegalArgumentException을 던진다.")
    @ParameterizedTest
    @ValueSource(strings = {"pobiii", "javajigi", "sixsix"})
    void validateCarName_ThrowException_WhenLengthIsOverFive(String name) {
        assertThatThrownBy(() -> RacingCalculator.race(name, "1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("자동차 이름은 5자 이하만 가능합니다");
    }

    @DisplayName("자동차 이름에 허용되지 않는 문자가 포함된 경우 IllegalArgumentException을 던진다.")
    @ParameterizedTest
    @ValueSource(strings = {"pobi!", "wo ni", "java#"})
    void validateCarName_ThrowException_WhenContainsInvalidCharacters(String name) {
        assertThatThrownBy(() -> RacingCalculator.race(name, "1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("자동차 이름은 영어, 숫자, 한글만 가능합니다");
    }

    @DisplayName("콤마로 분리 시 비어있는 자동차 이름이 포함된 경우 IllegalArgumentException을 던진다.")
    @ParameterizedTest
    @ValueSource(strings = {"pobi,", ",woni", "a,,b"})
    void validateCarName_ThrowException_WhenEmptyNameExists(String name) {
        assertThatThrownBy(() -> RacingCalculator.race(name, "1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("자동차 이름은 비어 있을 수 없습니다");
    }

    @DisplayName("시도 횟수가 1~100 범위 내의 숫자인 경우 통과한다.")
    @ParameterizedTest
    @ValueSource(strings = {"1", "50", "100"})
    void validateTryCount_Pass_WhenInRange(String number) {
        assertThatCode(() -> RacingCalculator.race("a", number)).doesNotThrowAnyException();
    }

    @DisplayName("시도 횟수가 1~100 범위를 벗어나거나 숫자가 아닌 경우 IllegalArgumentException을 던진다.")
    @ParameterizedTest
    @ValueSource(strings = {"0", "101", "-1", "a", "1.5", "ten"})
    void validateTryCount_ThrowException_WhenOutOfRangeOrNotNumber(String number) {
        assertThatThrownBy(() -> RacingCalculator.race("a", number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1~100 사이의 숫자를 입력해주세요");
    }

    @DisplayName("자동차 이름이 Null이거나 비어있는 경우 IllegalArgumentException을 던진다.")
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" "}) // 공백 문자열도 테스트
    void validateInputCarName_ThrowException_WhenNullOrEmpty(String name) {
        // race 메소드 내부 로직을 직접 테스트
        assertThatThrownBy(() -> RacingCalculator.race(name, "1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("경주할 자동차 이름을 입력해주세요");
    }

    @DisplayName("최고 위치가 동일한 우승자가 여러 명일 경우 쉼표로 분리하여 출력한다.")
    @Test
    void printWinners_MultipleWinners() {
        Car car1 = new Car("pobi");
        Car car2 = new Car("woni");
        Car car3 = new Car("crong");

        // 위치 설정 (pobi=2, woni=2, crong=1)
        for (int i = 0; i < 2; i++) { car1.move(); car2.move(); } // 가정을 위한 임시 호출

        // 최고 위치 판별 로직을 직접 테스트하기 위해 List를 만들어 간접 호출
        List<Car> cars = Arrays.asList(car1, car2, car3);
    }
}